package com.wke.gql.net;

import android.util.Log;

import com.google.gson.Gson;
import com.wke.gql.BaseView;
import com.wke.gql.dagger2.component.DaggerGsonComponent2;
import com.wke.gql.dagger2.component.DaggerRxNetWorkComponent;
import com.wke.gql.dagger2.module.RxNetWorkModule;
import com.wke.gql.view.LoadingDialog;

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
    public Retrofit retrofit;
    @Inject
    public Gson gson;

    private BaseView baseView;
    private CompositeDisposable mCompositeDisposable;
    private RxCallBack rxCallBack;
    //是否显示加载框
    private boolean isLoadingvisiable = true;
    //加载框
    private LoadingDialog loadingDialog;
    @Inject
    public RxNetWorkUtil() {
        gson = DaggerGsonComponent2.builder().build().gson();
        retrofit = DaggerRxNetWorkComponent.builder().rxNetWorkModule(new RxNetWorkModule(gson)).build().retrofit();
    }

    public RxNetWorkUtil detchToView(BaseView baseView) {
        this.baseView = baseView;
        return this;
    }

    public RxNetWorkUtil loadingVisiable(boolean isLoadingvisiable) {
        this.isLoadingvisiable = isLoadingvisiable;
        return this;
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
        showLoadingDialog();
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
        unDisposable();
        dismissLoadingDialog();
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
        unDisposable();
        dismissLoadingDialog();
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

    private void showLoadingDialog() {
        if (isLoadingvisiable && baseView != null) {
            baseView.showLoading();
        }
    }

    private void dismissLoadingDialog() {
        if (baseView != null) {
            baseView.dismissLoading();
        }
    }


    public interface RxCallBack<T> {
        void onPrepare();

        void onSuccess(T t);

        void onFail(Throwable t);
    }

}
