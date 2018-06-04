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
