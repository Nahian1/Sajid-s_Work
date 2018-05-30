/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

public interface OtherReportFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<OtherReportFragmentContract.View> {
        void sendIssue(Context context, int issueCode, String issue);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void sendIssue(Context context, int issueCode, String issue);
    }

    interface Repository extends BaseFragContract.Repository {
        void sendIssue(Context context, int issueCode, String issue);
    }
}
