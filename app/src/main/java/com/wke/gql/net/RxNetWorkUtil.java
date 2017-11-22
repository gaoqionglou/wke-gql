package com.wke.gql.net;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.wke.gql.BaseView;
import com.wke.gql.dagger2.component.DaggerGsonComponent2;
import com.wke.gql.dagger2.component.DaggerRxNetWorkComponent;
import com.wke.gql.dagger2.module.RxNetWorkModule;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @author gql
 */
public class RxNetWorkUtil {
    private static final String TAG = "RxNetWorkUtil";
    @Inject
    Retrofit retrofit;
    @Inject
    Gson gson;

    private BaseView baseView;
    private CompositeDisposable mCompositeDisposable;
    private RxCallBack rxCallBack;
    //是否使用默认网络请求加载框
    private boolean useDefaultloading = true;
    //是否显示加载框
    private boolean isLoadingvisiable = true;
    private WeakReference<Context> context;
    @Inject
    public RxNetWorkUtil(Context context) {
        this.context = new WeakReference<>(context);
        gson = DaggerGsonComponent2.builder().build().gson();
        retrofit = DaggerRxNetWorkComponent.builder().rxNetWorkModule(new RxNetWorkModule(gson)).build().retrofit();
    }


    public RxNetWorkUtil callBack(RxCallBack callBack) {
        this.rxCallBack = callBack;
        return this;
    }

    public <T> T initRetrofitService(Class<T> service) {
        Log.i(TAG, "initRetrofitService: " + retrofit.toString());
        return retrofit.create(service);
    }

    public <T> void doWork(Observable<T> observable) {
        observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(this::doOnSubscribe)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError);
    }

    /**
     * 请求之前的准备工作
     *
     * @param disposable
     */
    private void doOnSubscribe(Disposable disposable) {
        baseView.showLoading();
        addDisposable(disposable);
        if (rxCallBack != null) {
            rxCallBack.onPrepare();
        }
    }

    /**
     * 请求成功
     *
     * @param t
     * @param <T>
     */
    private <T> void onNext(T t) {
        baseView.dismissLoading();
        if (rxCallBack != null) {
            rxCallBack.onSuccess(t);
        }
    }

    /**
     * 请求失败的逻辑处理
     *
     * @param t
     */
    private void onError(Throwable t) {
        baseView.dismissLoading();
        if (rxCallBack != null) {
            rxCallBack.onFail(t);
        }
    }


    /**
     * 将Disposable添加
     *
     * @param subscription
     */
    public void addDisposable(Disposable subscription) {
        //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }


    public interface RxCallBack<T> {
        void onPrepare();

        void onSuccess(T t);

        void onFail(Throwable t);
    }

}
