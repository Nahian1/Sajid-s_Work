/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

public interface TransactionFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<TransactionFragmentContract.View> {
        void getTransactionDetails(Context context, int transactionId);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getTransactionDetails(Context context, int transactionId);
    }

    interface Repository extends BaseFragContract.Repository {
        void getTransactionDetails(Context context, int transactionId);
    }
}
