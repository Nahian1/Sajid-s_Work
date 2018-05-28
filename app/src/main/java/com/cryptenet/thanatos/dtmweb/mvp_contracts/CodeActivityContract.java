/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.code.CodeActivity;

public interface CodeActivityContract {
    interface Presenter extends BaseContract.Presenter<CodeActivityContract.View> {
        void saveResetCode(String code, Context context);
    }

    interface View extends BaseContract.View<CodeActivity> {
    }

    interface Model extends BaseContract.Model<CodeActivity> {
        void saveResetCode(String code, Context context);
    }

    interface Repository extends BaseContract.Repository {
        void saveResetCode(String code, Context context);
    }
}
