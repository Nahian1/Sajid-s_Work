/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

public interface PlanListFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<PlanListFragmentContract.View> {
        void getProjectList(Context context, String token);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getProjectList(Context context, String token);
    }

    interface Repository extends BaseFragContract.Repository {
        void getAllProjects(Context context, String token);
    }
}
