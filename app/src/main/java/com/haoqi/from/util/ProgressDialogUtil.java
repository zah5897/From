package com.haoqi.from.util;

import android.app.ProgressDialog;
import android.content.Context;


/**
 * Created by youxifuhuaqi on 2015/7/24.
 */
public class ProgressDialogUtil {
    public static ProgressDialog show(Context context, String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

    public static void dismiss(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
