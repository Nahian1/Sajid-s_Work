/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.pojo.NavHeader;

public interface HomeActivityContract {
    interface Presenter extends BaseContract.Presenter<HomeActivityContract.View> {
        void getNavHeaderData();
    }

    interface View extends BaseContract.View<HomeActivity> {
        void getNavHeaderData(NavHeader header);
    }

    interface Model extends BaseContract.Model<HomeActivity> {
        NavHeader getNavHeaderData();
    }

    interface Repository extends BaseContract.Repository {
        NavHeader getNavHeaderData();
    }
}
