/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.forgot_password.ForgotPasswordActivity;

public interface ForgotActivityContract {
    interface Presenter extends BaseContract.Presenter<ForgotActivityContract.View> {
        void sendIdentifier(String identifier);
        void saveIdentifier(String identifier, Context context);
    }

    interface View extends BaseContract.View<ForgotPasswordActivity> {
    }

    interface Model extends BaseContract.Model<ForgotPasswordActivity> {
        void sendIdentifier(String identifier);
        void saveIdentifier(String identifier, Context context);
    }

    interface Repository extends BaseContract.Repository {
        void sendIdentifier(String identifier);
        void saveIdentifierSP(String identifier, Context context);
    }
}
