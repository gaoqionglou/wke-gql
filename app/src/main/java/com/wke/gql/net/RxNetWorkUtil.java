package com.wke.gql.net;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.wke.gql.BaseView;
import com.wke.gql.utils.DialogUtil;
import com.wke.gql.view.LoadingDialog;

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
    public Retrofit retrofit;

    private BaseView baseView;
    private CompositeDisposable mCompositeDisposable;
    private RxCallBack rxCallBack;
    //是否可取消
    private boolean cancelable = true;
    //是否使用默认加载框
    private boolean useDefaultLoading = true;
    //是否显示加载框
    private boolean isLoadingvisiable = true;
    //加载框
    private LoadingDialog loadingDialog;
    private WeakReference<Context> context;
    @Inject
    public RxNetWorkUtil(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public RxNetWorkUtil detchToView(BaseView baseView) {
        Context ctx = null;
        this.baseView = baseView;
        if (baseView instanceof Activity) {
            ctx = (Activity) baseView;
        } else if (baseView instanceof Fragment) {
            ctx = ((Fragment) baseView).getActivity();

        } else if (baseView instanceof android.support.v4.app.Fragment) {
            ctx = ((android.support.v4.app.Fragment) baseView).getActivity();
        }
        if (ctx != null) context = new WeakReference<Context>(ctx);
        return this;
    }

    public RxNetWorkUtil cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public RxNetWorkUtil useDefaultLoading(boolean useDefaultLoading) {
        this.useDefaultLoading = useDefaultLoading;
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
    private void addDisposable(Disposable subscription) {
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
        if (isLoadingvisiable && useDefaultLoading) {
            loadingDialog = DialogUtil.showLoadingDialog(context.get(), cancelable, this::unDisposable);
        } else if (isLoadingvisiable && baseView != null) {
            baseView.showLoading();
        }
    }

    private void dismissLoadingDialog() {
        if (useDefaultLoading) {
            if (loadingDialog != null) loadingDialog.dismissAllowingStateLoss();
        } else if (baseView != null) {
            baseView.dismissLoading();
        }
    }


    public interface RxCallBack<T> {
        void onPrepare();

        void onSuccess(T t);

        void onFail(Throwable t);
    }

}
