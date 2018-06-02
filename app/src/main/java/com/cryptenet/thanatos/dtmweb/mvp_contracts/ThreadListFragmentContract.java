/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

public interface ThreadListFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<ThreadListFragmentContract.View> {
        void getThreadList(Context context);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getThreadList(Context context);
    }

    interface Repository extends BaseFragContract.Repository {
        void getThreadList(Context context);
    }
}
