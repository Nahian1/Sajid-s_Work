/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
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
