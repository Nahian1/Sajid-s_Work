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

public interface PlanDescFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<PlanDescFragmentContract.View> {
        void getLongDetails(Context context, int id);
        void getShortDetails(Context context, int id);
        void getThreadId(Context context, int planId);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getLongDetails(Context context, int id);
        void getShortDetails(Context context, int id);
        void getThreadId(Context context, int planId);
    }

    interface Repository extends BaseFragContract.Repository {
        void getLongDetails(Context context, int id);
        void getShortDetails(Context context, int id);
        void getThreadId(Context context, int planId);
    }
}
