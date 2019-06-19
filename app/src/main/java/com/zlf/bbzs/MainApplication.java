package com.zlf.bbzs;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.zlf.bbzs.util.BarUtils;
import com.zlf.bbzs.util.Utils;

/**
 * Created by zhu on 2019/6/17.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

    }
}
