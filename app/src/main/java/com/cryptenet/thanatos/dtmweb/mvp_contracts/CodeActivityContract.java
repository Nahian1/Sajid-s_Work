/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import com.cryptenet.thanatos.dtmweb.code.CodeActivity;

public interface CodeActivityContract {
    interface Presenter extends BaseContract.Presenter<CodeActivityContract.View> {
    }

    interface View extends BaseContract.View<CodeActivity> {
    }

    interface Model extends BaseContract.Model<CodeActivity> {
    }

    interface Repository extends BaseContract.Repository {
    }
}
