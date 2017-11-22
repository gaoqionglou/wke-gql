package com.wke.gql.utils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.wke.gql.view.LoadingDialog;

public class DialogUtil {
    /**
     * 展示加载框
     */
    public static LoadingDialog showLoadingDialog(Context context) {
        if (!(context instanceof FragmentActivity)) return null;
        FragmentActivity activity = (FragmentActivity) context;
        LoadingDialog dialogFragment = new LoadingDialog();
        dialogFragment.setCancelable(false);
        dialogFragment.show(activity.getSupportFragmentManager(), "LoadingDialog");
        return dialogFragment;
    }

    public static LoadingDialog showLoadingDialog(Context context, boolean cancelable, Runnable rCancelLoading) {
        if (!(context instanceof FragmentActivity)) return null;
        FragmentActivity activity = (FragmentActivity) context;
        LoadingDialog dialogFragment = new LoadingDialog();
        dialogFragment.setCancelable(cancelable);
        dialogFragment.rCancel = rCancelLoading;
        dialogFragment.show(activity.getSupportFragmentManager(), "LoadingDialog");
        return dialogFragment;
    }
}
