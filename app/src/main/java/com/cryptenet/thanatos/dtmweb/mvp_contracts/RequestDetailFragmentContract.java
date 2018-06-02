/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

public interface RequestDetailFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<RequestDetailFragmentContract.View> {
        void getTransactionDetails(Context context, int transactionId);
        void confirmRequest(Context context, int transactionId);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getTransactionDetails(Context context, int transactionId);
        void confirmRequest(Context context, int transactionId);
    }

    interface Repository extends BaseFragContract.Repository {
        void getTransactionDetails(Context context, int transactionId);
        void confirmRequest(Context context, int transactionId);
    }
}
