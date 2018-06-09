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

public interface ThreadListFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<ThreadListFragmentContract.View> {
        void getThreadList(Context context, int offset);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getThreadList(Context context, int offset);
    }

    interface Repository extends BaseFragContract.Repository {
        void getThreadList(Context context, int offset);
    }
}
