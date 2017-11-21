package com.wke.gql.base;

import android.support.multidex.MultiDexApplication;

import com.wke.gql.dagger2.User;
import com.wke.gql.dagger2.component.AppComponent;
import com.wke.gql.dagger2.component.DaggerAppComponent;
import com.wke.gql.dagger2.component.UserComponent;
import com.wke.gql.dagger2.component.UtilComponent;
import com.wke.gql.dagger2.component.UtilComponent2;
import com.wke.gql.dagger2.module.AppModule;
import com.wke.gql.dagger2.module.UserModule;
import com.wke.gql.dagger2.module.UtilModule;


public class BaseApplication extends MultiDexApplication {
    private static BaseApplication application;
    private AppComponent appComponent;
    private UserComponent userComponent;
    private UtilComponent utilComponent;
    private UtilComponent2 utilComponent2;
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

    public UtilComponent createUtilComponent() {
        return utilComponent = appComponent.init(new UtilModule());
    }

    public UtilComponent2 createUtilComponent2() {
        return utilComponent2 = appComponent.initUtilComponent2(new UtilModule());
    }

    public void releaseUserComponent() {
        userComponent = null;
    }

    public void releaseUtilComponent() {
        utilComponent = null;
    }
}
