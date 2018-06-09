/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.utils;

import android.app.Activity;
import android.app.ProgressDialog;

import com.cryptenet.thanatos.dtmweb.R;

public class ProgressDialogHelper {

    private static ProgressDialogHelper progressDialogHelper = null;
    private static ProgressDialog mProgressDialog = null;
    private static Activity mContext;

    private ProgressDialogHelper() {
    }

    public static ProgressDialogHelper init(Activity context) {

        mContext = context;

        mProgressDialog = new ProgressDialog(context);

        if (progressDialogHelper == null) {

            progressDialogHelper = new ProgressDialogHelper();

        }

        return progressDialogHelper;

    }

    public void showProgress() {

        if (mProgressDialog != null && !mProgressDialog.isShowing()) {

            mProgressDialog.setMessage(mContext.getString(R.string.please_wait));
            mProgressDialog.show();
        }
    }

    public static void hideProgress() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
