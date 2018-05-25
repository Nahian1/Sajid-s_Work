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
        void sendPwdResetRequest(String newPwd);
    }

    interface View extends BaseContract.View<SetPasswordActivity> {
    }

    interface Model extends BaseContract.Model<SetPasswordActivity> {
        void sendPwdResetRequest(String newPwd);
    }

    interface Repository extends BaseContract.Repository {
        void sendPwdResetRequest(String newPwd);
    }
}
