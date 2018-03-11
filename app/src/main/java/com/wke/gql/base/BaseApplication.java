package com.wke.gql.base;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.wke.gql.citywidget.CityDataModel;
import com.wke.gql.dagger2.component.AppComponent;
import com.wke.gql.dagger2.component.DaggerAppComponent;
import com.wke.gql.dagger2.component.NetWorkUtilComponent;
import com.wke.gql.dagger2.component.RxNetWorkUtilComponent;
import com.wke.gql.dagger2.module.AppMoudle;
import com.wke.gql.dagger2.module.NetWorkUtilModule;
import com.wke.gql.dagger2.module.RxNetWorkUtilModule;
import com.wke.gql.greendao.DaoManager;
import com.wke.gql.greendao.bean.CityDataBaseVersion;
import com.wke.gql.greendao.bean.CityItem;
import com.wke.gql.utils.Contants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseApplication extends MultiDexApplication {
    private static final String TAG = "BaseApplication";
    private static final TypeAdapter<Boolean> booleanAsIntAdapter = new TypeAdapter<Boolean>() {
        @Override
        public void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value);
            }
        }

        @Override
        public Boolean read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            switch (peek) {
                case BOOLEAN:
                    return in.nextBoolean();
                case NULL:
                    in.nextNull();
                    return null;
                case NUMBER:
                    return in.nextInt() != 0;
                case STRING:
                    return Boolean.parseBoolean(in.nextString());
                default:
                    throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek);
            }
        }
    };
    private static BaseApplication application;
    private AppComponent appComponent;
    private RxNetWorkUtilComponent rxNetWorkUtilComponent;
    private NetWorkUtilComponent netWorkUtilComponent;
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
        initARouter();
        appComponent = DaggerAppComponent.builder().appMoudle(new AppMoudle(this, retrofit, gson)).build();

        DaoManager.getInstance();
        loadCityJsonIntoDB("city.json");
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    //注入 RxNetWorkUtilComponent ,ActivityScope生效
    public RxNetWorkUtilComponent injectRxNetWorkUtilComponent() {
        return rxNetWorkUtilComponent = appComponent.addSub(new RxNetWorkUtilModule(retrofit));
    }

    public NetWorkUtilComponent injectNetWorkUtilComponent() {
        return netWorkUtilComponent = appComponent.addSub(new NetWorkUtilModule(retrofit));
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
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(Boolean.class, booleanAsIntAdapter)
                .registerTypeAdapter(boolean.class, booleanAsIntAdapter)
                .create();
    }

    //初始化ARouter
    private void initARouter() {
//        if (BuildConfig.DEBUG) {
        Log.i(TAG, "initARouter:openLog openDebug");
        ARouter.openLog();
        ARouter.openDebug();
//        }
        ARouter.init(this);
    }

    private void loadCityJsonIntoDB(String fileName) {
        String json = readFromAssets("city.json");
        CityDataModel cityDataModel = gson.fromJson(json, CityDataModel.class);
        CityDataBaseVersion version = CityDataBaseVersion.getFirst(CityDataBaseVersion.class);
        List<CityItem> items = cityDataModel.result;
        if (version == null) {
            Log.i(TAG, "loadCityJsonIntoDB: " + "第一次加入数据");
            CityDataBaseVersion version1 = new CityDataBaseVersion();
            version1.version = cityDataModel.version;
            CityDataBaseVersion.insert(version1);
            for (CityItem item : items) {
                CityItem.insertOrReplace(item);
            }
        } else if (!version.version.equalsIgnoreCase(cityDataModel.version)) {
            Log.i(TAG, "loadCityJsonIntoDB: " + "更新数据");
            version.version = cityDataModel.version;
            CityDataBaseVersion.insertOrReplace(version);
            Log.i(TAG, "loadCityJsonIntoDB-1: " + CityItem.count(CityItem.class));
            CityItem.deleteAll(CityItem.class);
            Log.i(TAG, "loadCityJsonIntoDB-2: " + CityItem.count(CityItem.class));
            for (CityItem item : items) {
                CityItem.insertOrReplace(item);
            }
            Log.i(TAG, "loadCityJsonIntoDB-3: " + CityItem.count(CityItem.class));
        } else if (version.version.equalsIgnoreCase(cityDataModel.version)) {
            Log.i(TAG, "loadCityJsonIntoDB: " + "版本号一致");
        }
    }

    /**
     * 从assets中读取txt
     */
    private String readFromAssets(String fileName) {
        try {
            InputStream is = getAssets().open(fileName);
            String text = read(is);
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 按行读取txt
     *
     * @param is
     * @return
     * @throws Exception
     */
    private String read(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
