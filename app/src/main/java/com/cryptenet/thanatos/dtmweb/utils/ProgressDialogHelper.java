package com.cryptenet.thanatos.dtmweb.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Asif on 5/30/2018.
 */

public class ProgressDialogHelper {

    private static volatile ProgressDialog mProgressDialog = null;

    private ProgressDialogHelper() {
    }

    public static ProgressDialog init(Activity context) {

        if (mProgressDialog == null) mProgressDialog = new ProgressDialog(context);

        return mProgressDialog;

    }

    public void showProgress(String message) {

        if (mProgressDialog != null && !mProgressDialog.isShowing()) {

            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    public static void hideProgress() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
