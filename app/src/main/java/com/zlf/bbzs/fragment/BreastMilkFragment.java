package com.zlf.bbzs.fragment;

import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zlf.bbzs.MainApplication;
import com.zlf.bbzs.R;
import com.zlf.bbzs.bean.BreastMilkBean;
import com.zlf.bbzs.bean.BreastMilkBeanDao;
import com.zlf.bbzs.util.TimeUtils;
import com.zlf.bbzs.util.Utils;
import com.zlf.bbzs.widget.ClockViewByPath;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhu on 2019/6/19.
 */

public class BreastMilkFragment extends BaseFragment {

    private ClockViewByPath mClockView;
    private Button mBtnLeft;
    private Button mBtnRight;
    private EditText mEtMemo;
    private int mBtnLeftStat = 0; // 0 准备，1 暂停，2 继续
    private int mBtnRightStat = 0;
    private TextView mTvLeft;
    private TextView mTvRight;
    private Disposable mSubscribeRight;
    private Disposable mSubscribeLeft;
    private long mCurrIntervalLeft = 0;
    private long mInitTimeLeft = 0;
    private long mCurrIntervalRight = 0;
    private long mInitTimeRight = 0;
    private Button mBtnCommit;
    private TextView mTvDesc;

    @Override
    protected void initView(View view) {
        mClockView = view.findViewById(R.id.clock);
        mBtnLeft = view.findViewById(R.id.btn_left);
        mBtnRight = view.findViewById(R.id.btn_right);
        mEtMemo = view.findViewById(R.id.et_memo);
        mTvLeft = view.findViewById(R.id.tv_left);
        mTvRight= view.findViewById(R.id.tv_right);
        mBtnCommit = view.findViewById(R.id.btn_commit);
        mTvDesc = view.findViewById(R.id.tv_desc);

        mBtnLeft.setOnClickListener(view12 -> {
            if (mBtnLeftStat == 0 || mBtnLeftStat == 2) {
                leftStart();
            } else if (mBtnLeftStat == 1) {
                leftPause();
            }
        });
        mBtnRight.setOnClickListener(view1 -> {
            if (mBtnRightStat == 0 || mBtnRightStat == 2) {
                rightStart();
            } else if (mBtnRightStat == 1) {
                rightPause();
            }
        });
        mBtnCommit.setOnClickListener(view1 -> {
            commit();
        });
        List<BreastMilkBean> list = ((MainApplication) Utils.getApp()).getDaoSession().getBreastMilkBeanDao().queryBuilder().limit(1).orderDesc(BreastMilkBeanDao.Properties.DataTime).list();
        if (!list.isEmpty()) {
            mTvDesc.setText(TimeUtils.getFriendlyTimeSpanByNow(list.get(0).getDataTime()));
        } else {
            mTvDesc.setText("还没有记录过呦！！！");
        }
    }

    private void commit() {
        try {
            if (mSubscribeLeft != null) {
                mSubscribeLeft.dispose();
                mSubscribeLeft = null;
                mInitTimeLeft += mCurrIntervalLeft;
            }
            if (mSubscribeRight != null) {
                mSubscribeRight.dispose();
                mSubscribeRight = null;
                mInitTimeRight += mCurrIntervalRight;
            }

            BreastMilkBean bean = new BreastMilkBean(TimeUtils.getNowMills(), mInitTimeLeft, mInitTimeRight, mEtMemo.getText().toString());
            ((MainApplication) Utils.getApp()).getDaoSession().getBreastMilkBeanDao().insert(bean);

        } catch (Exception e) {
            Log.e("BmFragment", e.toString());
        }
    }

    private void rightPause() {
        mBtnRight.setText("继续");
        mBtnLeft.setText("右侧");
        mBtnRightStat = 2;
        mBtnLeftStat = 0;
        mClockView.performAnimation();
        if (mSubscribeLeft != null) {
            mSubscribeLeft.dispose();
            mSubscribeLeft = null;
            mInitTimeLeft += mCurrIntervalLeft;
        }
        if (mSubscribeRight != null) {
            mSubscribeRight.dispose();
            mSubscribeRight = null;
            mInitTimeRight += mCurrIntervalRight;
        }
    }

    private void rightStart() {
        mBtnRight.setText("暂停");
        mBtnLeft.setText("右侧");
        mBtnRightStat = 1;
        mBtnLeftStat = 0;
        mClockView.cancelAnimation();
        if (mSubscribeRight == null) {
            mSubscribeRight = Observable.interval(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        mCurrIntervalRight = aLong;
                        mTvRight.setText(timeFormat(mInitTimeRight, mCurrIntervalRight));
                    });
        }
        if (mSubscribeLeft != null) {
            mSubscribeLeft.dispose();
            mInitTimeLeft += mCurrIntervalLeft;
            mSubscribeLeft = null;
        }
    }

    private void leftPause() {
        mBtnLeft.setText("继续");
        mBtnRight.setText("右侧");
        mBtnLeftStat = 2;
        mBtnRightStat = 0;
        mClockView.start();
        if (mSubscribeLeft != null) {
            mSubscribeLeft.dispose();
            mSubscribeLeft = null;
            mInitTimeLeft += mCurrIntervalLeft;
        }
        if (mSubscribeRight != null) {
            mSubscribeRight.dispose();
            mSubscribeRight = null;
            mInitTimeRight += mCurrIntervalRight;
        }
    }

    private void leftStart() {
        mBtnLeft.setText("暂停");
        mBtnRight.setText("右侧");
        mBtnLeftStat = 1;
        mBtnRightStat = 0;
        mClockView.stop();
        if (mSubscribeLeft == null) {
            mSubscribeLeft = Observable.interval(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        mCurrIntervalLeft = aLong;
                        mTvLeft.setText(timeFormat(mInitTimeLeft, mCurrIntervalLeft));
                    });
        }
        if (mSubscribeRight != null) {
            mSubscribeRight.dispose();
            mSubscribeRight = null;
            mInitTimeRight += mCurrIntervalRight;
        }
    }

    private String timeFormat(long initTime, long intervalSec) {
        long totalTime = initTime + intervalSec;
        return String.format("%02d : %02d", totalTime / 60, totalTime % 60);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_breast_milk;
    }

    public static Fragment newInstance() {
        return new BreastMilkFragment();
    }


}
