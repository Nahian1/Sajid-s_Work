/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import com.cryptenet.thanatos.dtmweb.forgot.ForgotActivity;

public interface ForgotActivityContract {
    interface Presenter extends BaseContract.Presenter<ForgotActivityContract.View> {
    }

    interface View extends BaseContract.View<ForgotActivity> {
    }

    interface Model extends BaseContract.Model<ForgotActivity> {
    }

    interface Repository extends BaseContract.Repository {
    }
}
