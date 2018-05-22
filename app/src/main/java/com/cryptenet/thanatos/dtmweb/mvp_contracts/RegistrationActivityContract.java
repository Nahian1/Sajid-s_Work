/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;

public interface RegistrationActivityContract {
    interface Presenter extends BaseContract.Presenter<RegistrationActivityContract.View> {
    }

    interface View extends BaseContract.View<RegistrationActivity> {
    }

    interface Model extends BaseContract.Model<RegistrationActivity> {
    }

    interface Repository extends BaseContract.Repository {
    }
}
