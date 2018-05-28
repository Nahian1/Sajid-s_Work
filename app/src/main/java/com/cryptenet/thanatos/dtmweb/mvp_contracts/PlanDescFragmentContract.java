/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

public interface PlanDescFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<PlanDescFragmentContract.View> {
        void getLongDetails(Context context, int id);
        void getShortDetails(Context context, int id);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getLongDetails(Context context, int id);
        void getShortDetails(Context context, int id);
    }

    interface Repository extends BaseFragContract.Repository {
        void getLongDetails(Context context, int id);
        void getShortDetails(Context context, int id);
    }
}
