package com.wke.gql.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Window;

import com.wke.gql.R;
import com.wke.gql.base.BaseDialogFragment;

/**
 * Created by gql on 17-11-22.
 */

public class LoadingDialog extends BaseDialogFragment {
    public Runnable rCancel;
    @Override
    protected Dialog dialog() {
        Dialog dialog = new Dialog(getActivity(), R.style.dialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_loading);
/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = dialog.getWindow();
            if (window != null) window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/
        return dialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (rCancel != null) rCancel.run();
    }
}
