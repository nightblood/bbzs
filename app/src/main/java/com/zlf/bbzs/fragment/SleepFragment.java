package com.zlf.bbzs.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.zlf.bbzs.MainApplication;
import com.zlf.bbzs.R;
import com.zlf.bbzs.bean.BabyDiaperBean;
import com.zlf.bbzs.bean.BabyDiaperBeanDao;
import com.zlf.bbzs.bean.SleepBean;
import com.zlf.bbzs.bean.SleepBeanDao;
import com.zlf.bbzs.constant.TimeConstants;
import com.zlf.bbzs.util.TimeUtils;
import com.zlf.bbzs.util.ToastUtils;
import com.zlf.bbzs.util.Utils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhu on 2019/6/19.
 */

public class SleepFragment extends BaseFragment {
    private TextView mTvSleepStartTime;
    private TextView mTvSleepEndTime;
    private TextView mTvDesc;
    private EditText mEtMemo;
    private Button mBtnCommit;
    private Button mBtnStart;
    private long mSleepStartTime;
    private long mSleepEndTime;
    private TimePickerView mPvStartTime;
    private TimePickerView mPvEndTime;
    private Disposable mTimer;
    private String TAG = SleepFragment.class.getSimpleName();
    private long mSleepTime = 0;


    @Override
    protected void initView(View view) {
        mTvSleepStartTime = view.findViewById(R.id.tv_sleep_time_start);
        mTvSleepEndTime = view.findViewById(R.id.tv_sleep_time_end);
        mTvDesc = view.findViewById(R.id.tv_desc);
        mEtMemo = view.findViewById(R.id.et_memo);
        mBtnCommit = view.findViewById(R.id.btn_commit);
        mBtnStart = view.findViewById(R.id.btn_start);
        mPvStartTime = new TimePickerBuilder(getContext() , (date, v) -> {
            mSleepStartTime = TimeUtils.getMillis(date, 0, TimeConstants.SEC);
            mTvSleepStartTime.setText(TimeUtils.date2String(date, new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒")));

        })
                .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
                .setTitleSize(18)//标题文字大小
                .setTitleText("")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(getContext().getColor(R.color.colorAccent))//标题文字颜色
                .setSubmitColor(getContext().getColor(R.color.colorPrimary))//确定按钮文字颜色
                .setCancelColor(getContext().getColor(R.color.colorPrimary))//取消按钮文字颜色
                .setTitleBgColor(getContext().getColor(R.color.gray1))//标题背景颜色 Night mode
                .setBgColor(getContext().getColor(R.color.gray))//滚轮背景颜色 Night mode
//                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        mPvEndTime = new TimePickerBuilder(getContext() , (date, v) -> {
            mSleepEndTime = TimeUtils.getMillis(date, 0, TimeConstants.SEC);
            mTvSleepEndTime.setText(TimeUtils.date2String(date, new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒")));
        })
                .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
                .setTitleSize(18)//标题文字大小
                .setTitleText("")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(getContext().getColor(R.color.colorAccent))//标题文字颜色
                .setSubmitColor(getContext().getColor(R.color.colorPrimary))//确定按钮文字颜色
                .setCancelColor(getContext().getColor(R.color.colorPrimary))//取消按钮文字颜色
                .setTitleBgColor(getContext().getColor(R.color.gray1))//标题背景颜色 Night mode
                .setBgColor(getContext().getColor(R.color.gray))//滚轮背景颜色 Night mode
//                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();

        mTvSleepStartTime.setOnClickListener(view1 -> {
            mPvStartTime.show();
        });
        mTvSleepEndTime.setOnClickListener(view1 -> {
            mPvEndTime.show();
        });
        mBtnCommit.setOnClickListener(view1 -> commit());
        mBtnStart.setOnClickListener(view1 -> {

            if (mBtnStart.getText().toString().equals("结束")) {
                mBtnStart.setText("开始");
                if (mTimer != null) {
                    mTimer.dispose();
                    mTimer = null;
                }
                mSleepEndTime += mSleepTime * 1000;
                mSleepTime = 0;
            } else {

                mBtnStart.setText("结束");
                mTimer  = Observable.interval(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            Log.e(TAG, "initView: "+ mSleepEndTime +", " + aLong);
                            mSleepTime = aLong + 1;
                            mTvSleepEndTime.setText(TimeUtils.getString(mSleepEndTime,new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"), mSleepTime, TimeConstants.SEC));
                        });
            }
        });

        List<SleepBean> list = ((MainApplication) Utils.getApp()).getDaoSession().getSleepBeanDao().queryBuilder().limit(1).orderDesc(SleepBeanDao.Properties.DataTime).list();
        if (!list.isEmpty()) {
            mTvDesc.setText(TimeUtils.getFriendlyTimeSpanByNow(list.get(0).getSleepStartTime()));
        } else {
            mTvDesc.setText("还没有记录过呦！！！");
        }
        mSleepStartTime = TimeUtils.getNowMills();
        mSleepEndTime = mSleepStartTime;
        String timeStr = TimeUtils.date2String(TimeUtils.getNowDate(), new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"));
        mTvSleepStartTime.setText(timeStr);
        mTvSleepEndTime.setText(timeStr);
    }

    private void commit() {
        if (mTimer != null) {
            ToastUtils.showShort("请先停止定时器！！！");
            return;
        }
        try {
            if (mSleepStartTime == 0) {
                mSleepStartTime = TimeUtils.getNowMills();
            }
            if (mSleepEndTime == 0) {
                mSleepEndTime = TimeUtils.getNowMills();
            }

            SleepBean bean = new SleepBean(TimeUtils.getNowMills(), mSleepStartTime, mSleepEndTime, mEtMemo.getText().toString());
            ((MainApplication) Utils.getApp()).getDaoSession().getSleepBeanDao().insert(bean);

            ToastUtils.showShort("保存成功");
        } catch (Exception e) {
            ToastUtils.showShort("保存失败");
            Log.e("BabyDiaperFragment", e.toString());
        }
    }

    @Override
    public boolean isCounting() {
        return mTimer != null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sleep;
    }

    public static BaseFragment newInstance() {
        return new SleepFragment();
    }
}
