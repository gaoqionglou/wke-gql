package com.wke.gql.greendao;

/**
 * @author gql
 * @since 18-3-9.
 */

import android.util.Log;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.greendao.gen.DaoMaster;
import com.wke.gql.greendao.gen.DaoSession;

/**
 * dao管理类
 *
 * @author gql
 * @since 20171018
 */

public class DaoManager {
    public static final String DEFUALT_DB_NAME = "WKE-GQL.db";
    private static final String TAG = "DaoManager";
    private static DaoManager mDaoManager;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DaoManager() {
        try {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getApplication(), DEFUALT_DB_NAME, null);
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
            Log.i(TAG, "DaoManager --name : " + DEFUALT_DB_NAME + " -- 初始化");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化默认数据库
     *
     * @return
     */
    public static DaoManager getInstance() {
        if (mDaoManager == null) {
            mDaoManager = new DaoManager();
        }
        return mDaoManager;
    }

    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }


    public DaoSession getDaoSession() {
        return getNewDaoSession();
    }

    public DaoSession getNewDaoSession() {
        return mDaoMaster.newSession();
    }
}

