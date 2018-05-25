/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

public interface PlanListFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<PlanListFragmentContract.View> {
        void getProjectList();
    }

    interface View extends BaseFragContract.View {
        void setProjectList();
    }

    interface Model extends BaseFragContract.Model {
        void getProjectList();
    }

    interface Repository extends BaseFragContract.Repository {
        void getAllProjects();
    }
}
