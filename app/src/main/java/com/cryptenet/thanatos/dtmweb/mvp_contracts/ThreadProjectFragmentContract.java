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

public interface ThreadProjectFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<ThreadProjectFragmentContract.View> {
        void getInvestorThreads(int threadId, Context context);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getInvestorThreads(int threadId, Context context);
    }

    interface Repository extends BaseFragContract.Repository {
        void getInvestorThreads(int threadId, Context context);
    }
}
