/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import com.cryptenet.thanatos.dtmweb.home.HomeActivity;

public interface HomeActivityContract {
    interface Presenter extends BaseContract.Presenter<HomeActivityContract.View> {
    }

    interface View extends BaseContract.View<HomeActivity> {
    }

    interface Model extends BaseContract.Model<HomeActivity> {
    }

    interface Repository extends BaseContract.Repository {
    }
}
