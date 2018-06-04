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

import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.pojo.NavHeader;

public interface HomeActivityContract {
    interface Presenter extends BaseContract.Presenter<HomeActivityContract.View> {
        void getNavHeaderData(Context context);
        void clearUserData(Context context);
    }

    interface View extends BaseContract.View<HomeActivity> {
        void getNavHeaderData(NavHeader header);
        void userDataCleaned();
    }

    interface Model extends BaseContract.Model<HomeActivity> {
        NavHeader getNavHeaderData(Context context);
        boolean clearUserData(Context context);
    }

    interface Repository extends BaseContract.Repository {
        NavHeader getNavHeaderData(Context context);
        boolean clearUserData(Context context);
    }
}
