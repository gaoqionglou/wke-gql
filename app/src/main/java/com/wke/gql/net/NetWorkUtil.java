package com.wke.gql.net;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.wke.gql.BaseView;
import com.wke.gql.utils.DialogUtil;
import com.wke.gql.view.LoadingDialog;

import java.lang.ref.WeakReference;
import java.util.Stack;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author gql
 */

public class NetWorkUtil {
    private static final String TAG = "NetWorkUtil";
    public Retrofit retrofit;

    private BaseView baseView;
    private Stack<Call> callStack = new Stack<>();
    private NetWorkUtil.CallBack callBack;
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
    public NetWorkUtil(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public NetWorkUtil detchToView(BaseView baseView) {
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

    public NetWorkUtil cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public NetWorkUtil useDefaultLoading(boolean useDefaultLoading) {
        this.useDefaultLoading = useDefaultLoading;
        return this;
    }

    public NetWorkUtil loadingVisiable(boolean isLoadingvisiable) {
        this.isLoadingvisiable = isLoadingvisiable;
        return this;
    }

    public NetWorkUtil callBack(NetWorkUtil.CallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    public <T> T initRetrofitService(Class<T> service) {
        Log.i(TAG, "initRetrofitService: " + retrofit.toString());
        return retrofit.create(service);
    }

    @SuppressWarnings("unchecked")
    public <T> void enqueue(Call<T> call) {
        if (call == null) return;
        callStack.push(call);
        if (callBack != null) callBack.onPrepare();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (callBack != null) callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                if (callBack != null) callBack.onFail(call, t);
            }
        });
    }

    @SuppressWarnings("unchecked")
    public <T> void excute(Call<T> call) {
        if (call == null) return;
        callStack.push(call);
        if (callBack != null) callBack.onPrepare();
        try {
            Response<T> response = call.execute();
            if (callBack != null)
                callBack.onSuccess(response.body());
        } catch (Exception e) {
            Log.e(TAG, "error " + e);
            e.printStackTrace();
            if (callBack != null) callBack.onFail(call, e);
        }
    }

    /**
     * 取消当前栈内所有请求，并清空
     */
    public void cancel() {
        for (Call call : callStack) {
            call.cancel();
        }
        callStack.clear();
    }


    private void showLoadingDialog() {
        if (isLoadingvisiable && useDefaultLoading) {
            loadingDialog = DialogUtil.showLoadingDialog(context.get(), cancelable, this::cancelLoading);
        } else if (isLoadingvisiable && baseView != null) {
            baseView.showLoading();
        }
    }

    private void cancelLoading() {
        Call call = callStack.peek();//查看栈顶的call
        if (call.isExecuted()) {
            call.cancel();
        }
    }

    private void dismissLoadingDialog() {
        if (useDefaultLoading) {
            if (loadingDialog != null) loadingDialog.dismissAllowingStateLoss();
        } else if (baseView != null) {
            baseView.dismissLoading();
        }
    }


    public interface CallBack<T> {
        void onPrepare();

        void onSuccess(T t);

        void onFail(Call<T> call, Throwable t);
    }

    /**
     * 一个回调抽象类，可以简单地实现成功回调和失败回调方法，而不需要理会loading的加载框处理
     */
    public abstract class CommonCallBack<T> implements NetWorkUtil.CallBack<T> {


        @Override
        public void onPrepare() {
            showLoadingDialog();
        }

        @Override
        public void onSuccess(T o) {
            dismissLoadingDialog();
            dealWithSuccess(o);
        }

        @Override
        public void onFail(Call call, Throwable t) {
            dismissLoadingDialog();
            dealWithFail(call, t);
        }

        protected abstract void dealWithSuccess(T o);

        protected abstract void dealWithFail(Call call, Throwable t);
    }
}
