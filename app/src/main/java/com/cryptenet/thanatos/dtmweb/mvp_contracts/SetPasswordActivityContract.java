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

import com.cryptenet.thanatos.dtmweb.set_password.SetPasswordActivity;

public interface SetPasswordActivityContract {
    interface Presenter extends BaseContract.Presenter<SetPasswordActivityContract.View> {
        void sendPwdResetRequest(String newPwd, Context context);
    }

    interface View extends BaseContract.View<SetPasswordActivity> {
    }

    interface Model extends BaseContract.Model<SetPasswordActivity> {
        void sendPwdResetRequest(String newPwd, Context context);
    }

    interface Repository extends BaseContract.Repository {
        void sendPwdResetRequest(String newPwd, Context context);
    }
}
