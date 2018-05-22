/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import com.cryptenet.thanatos.dtmweb.set_password.SetPasswordActivity;

public interface SetPasswordActivityContract {
    interface Presenter extends BaseContract.Presenter<SetPasswordActivityContract.View> {
    }

    interface View extends BaseContract.View<SetPasswordActivity> {
    }

    interface Model extends BaseContract.Model<SetPasswordActivity> {
    }

    interface Repository extends BaseContract.Repository {
    }
}
