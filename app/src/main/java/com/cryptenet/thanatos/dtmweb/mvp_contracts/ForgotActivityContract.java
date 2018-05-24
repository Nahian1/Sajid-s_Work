/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import com.cryptenet.thanatos.dtmweb.forgot_password.ForgotPasswordActivity;

public interface ForgotActivityContract {
    interface Presenter extends BaseContract.Presenter<ForgotActivityContract.View> {
        void saveIdentifier(String identifier);
    }

    interface View extends BaseContract.View<ForgotPasswordActivity> {
    }

    interface Model extends BaseContract.Model<ForgotPasswordActivity> {
        void saveIdentifier(String identifier);
    }

    interface Repository extends BaseContract.Repository {
        void saveIdentifier(String identifier);
    }
}
