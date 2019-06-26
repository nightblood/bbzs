package com.zlf.bbzs.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.liang.jtab.utils.ColorUtils;
import com.zlf.bbzs.MainActivity;
import com.zlf.bbzs.MainApplication;
import com.zlf.bbzs.R;
import com.zlf.bbzs.bean.BabyDiaperBean;
import com.zlf.bbzs.bean.BabyDiaperBeanDao;
import com.zlf.bbzs.bean.BreastMilkBean;
import com.zlf.bbzs.bean.BreastMilkBeanDao;
import com.zlf.bbzs.constant.TimeConstants;
import com.zlf.bbzs.util.TimeUtils;
import com.zlf.bbzs.util.ToastUtils;
import com.zlf.bbzs.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhu on 2019/6/25.
 */

public class BabyDiaperFragment extends BaseFragment {
    private TextView mTvDiperTime;
    private TextView mTvDesc;
    private EditText mEtMemo;
    private Button mBtnCommit;
    private long mDiperTime;
    private TextView mTvProperty;
    private TimePickerView mPvTime;

    @Override
    protected void initView(View view) {
        mTvDiperTime = view.findViewById(R.id.tv_diper_time);
        mTvDesc = view.findViewById(R.id.tv_desc);
        mEtMemo = view.findViewById(R.id.et_memo);
        mBtnCommit = view.findViewById(R.id.btn_commit);
        mTvProperty = view.findViewById(R.id.tv_property);
        mPvTime = new TimePickerBuilder(getContext() , (date, v) -> {
            mDiperTime = TimeUtils.getMillis(date, 0, TimeConstants.SEC);
            ToastUtils.showShort(mDiperTime + "");
            mTvDiperTime.setText(TimeUtils.date2String(date, new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒")));
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

        mTvDiperTime.setOnClickListener(view1 -> {
            mPvTime.show();
        });
        mBtnCommit.setOnClickListener(view1 -> commit());

        List<BabyDiaperBean> list = ((MainApplication) Utils.getApp()).getDaoSession().getBabyDiaperBeanDao().queryBuilder().limit(1).orderDesc(BabyDiaperBeanDao.Properties.DiaperTime).list();
        if (!list.isEmpty()) {
            mTvDesc.setText(TimeUtils.getFriendlyTimeSpanByNow(list.get(0).getDiaperTime()));
        } else {
            mTvDesc.setText("还没有记录过呦！！！");
        }
        mTvDiperTime.setText(TimeUtils.date2String(TimeUtils.getNowDate(), new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒")));
    }

    private void commit() {
        try {
            if (mDiperTime == 0) {
                mDiperTime = TimeUtils.getNowMills();
            }
            BabyDiaperBean bean = new BabyDiaperBean(TimeUtils.getNowMills(), mDiperTime, mTvProperty.getText().toString(), mEtMemo.getText().toString());
            ((MainApplication) Utils.getApp()).getDaoSession().getBabyDiaperBeanDao().insert(bean);

            ToastUtils.showShort("保存成功");
        } catch (Exception e) {
            ToastUtils.showShort("保存失败");
            Log.e("BabyDiaperFragment", e.toString());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_baby_diaper;
    }

    public static BaseFragment newInstance() {
        return new BabyDiaperFragment();
    }
}
