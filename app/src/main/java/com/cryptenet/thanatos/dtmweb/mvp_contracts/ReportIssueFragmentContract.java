/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

public interface ReportIssueFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<ReportIssueFragmentContract.View> {
        void getAllIssues(Context context);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getAllIssues(Context context);
    }

    interface Repository extends BaseFragContract.Repository {
        void getAllIssues(Context context);
    }
}
