package com.cryptenet.thanatos.dtmweb.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by Asif on 6/2/2018.
 */

public class ViewUtils {

    public static void hideKeyboard(Activity context) {

        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

//    public static ProgressDialog showProgress(Context context) {
//
//        ProgressDialog mProgressDialog = new ProgressDialog(context);
//        mProgressDialog.setMessage("Please wait...");
//
//        return mProgressDialog;
//    }

}
