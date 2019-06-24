package com.zlf.bbzs.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.zlf.bbzs.R;

/**
 * Created by zhu on 2019/6/19.
 */

public class SleepFragment extends BaseFragment {

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sleep;
    }

    public static Fragment newInstance() {
        return new SleepFragment();
    }
}
