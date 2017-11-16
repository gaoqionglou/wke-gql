package com.wke.gql.base;

import android.support.multidex.MultiDexApplication;

import com.wke.gql.dagger2.User;
import com.wke.gql.dagger2.component.AppComponent;
import com.wke.gql.dagger2.component.DaggerAppComponent;
import com.wke.gql.dagger2.component.UserComponent;
import com.wke.gql.dagger2.module.AppModule;
import com.wke.gql.dagger2.module.UserModule;


public class BaseApplication extends MultiDexApplication {
    private static BaseApplication application;
    private AppComponent appComponent;
    private UserComponent userComponent;
    public BaseApplication() {
        application = this;
    }
    public static BaseApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

    //注入UserComponent ,userscope生效
    public UserComponent createUserComponent(User user) {
        return userComponent = appComponent.plus(new UserModule(user));
    }

    public void releaseUserComponent() {
        userComponent = null;
    }
}
