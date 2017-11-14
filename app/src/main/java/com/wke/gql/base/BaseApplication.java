package com.wke.gql.base;

import android.support.multidex.MultiDexApplication;



public class BaseApplication extends MultiDexApplication {
    private static BaseApplication application;

    public BaseApplication() {
        application = this;
    }
    public static BaseApplication getApplication() {
        return application;
    }

}
