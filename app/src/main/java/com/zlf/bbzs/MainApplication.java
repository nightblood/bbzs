package com.zlf.bbzs;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;

import com.zlf.bbzs.bean.DaoMaster;
import com.zlf.bbzs.bean.DaoSession;
import com.zlf.bbzs.util.Utils;

/**
 * Created by zhu on 2019/6/17.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initGreenDao();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "aserbao.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }

}
