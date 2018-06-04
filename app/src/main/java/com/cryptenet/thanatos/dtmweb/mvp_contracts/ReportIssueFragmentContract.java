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
