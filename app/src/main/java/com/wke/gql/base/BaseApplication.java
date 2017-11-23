package com.wke.gql.base;

import android.support.multidex.MultiDexApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wke.gql.dagger2.component.AppComponent;
import com.wke.gql.dagger2.component.DaggerAppComponent;
import com.wke.gql.dagger2.component.RxNetWorkUtilComponent;
import com.wke.gql.dagger2.module.AppMoudle;
import com.wke.gql.dagger2.module.RxNetWorkUtilModule;
import com.wke.gql.utils.Contants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseApplication extends MultiDexApplication {
    private static BaseApplication application;
    private AppComponent appComponent;
    private RxNetWorkUtilComponent rxNetWorkUtilComponent;
    private Retrofit retrofit;
    private Gson gson;
    public BaseApplication() {
        application = this;
    }
    public static BaseApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initGson();
        initRetrofit();
        appComponent = DaggerAppComponent.builder().appMoudle(new AppMoudle(this, retrofit, gson)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    //注入 RxNetWorkUtilComponent ,ActivityScope生效
    public RxNetWorkUtilComponent injectRxNetWorkUtilComponent() {
        return rxNetWorkUtilComponent = appComponent.addSub(new RxNetWorkUtilModule(retrofit));
    }

    private void initRetrofit() {
        if (gson == null) {
            throw new NullPointerException("gson need to be inited");
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(Contants.HTTP_QUERY_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ;
    }

    private void initGson() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
}
