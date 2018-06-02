package com.cryptenet.thanatos.dtmweb.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Asif on 5/30/2018.
 */

public class ProgressDialogHelper {

    private static ProgressDialogHelper progressDialogHelper = null;
    private static ProgressDialog mProgressDialog = null;

    private ProgressDialogHelper() {
    }

    public static ProgressDialogHelper init(Activity context) {

        if (progressDialogHelper == null) {

            if (mProgressDialog == null)
                mProgressDialog = new ProgressDialog(context);

            return new ProgressDialogHelper();
        }

        return progressDialogHelper;

    }

    public void showProgress() {

        if (mProgressDialog != null && !mProgressDialog.isShowing()) {

            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.show();
        }
    }

    public static void hideProgress() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();

            mProgressDialog = null;
            progressDialogHelper = null;
        }
    }
}
