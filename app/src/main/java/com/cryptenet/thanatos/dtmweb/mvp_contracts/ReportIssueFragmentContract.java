/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

public interface ReportIssueFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<ReportIssueFragmentContract.View> {
        void getAllIssues();
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getAllIssues();
    }

    interface Repository extends BaseFragContract.Repository {
        void getAllIssues();
    }
}
