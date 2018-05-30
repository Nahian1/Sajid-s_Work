/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.pojo.Transaction;

public interface FormFragmentContract {

    interface Presenter extends BaseFragContract.Presenter<FormFragmentContract.View> {

        void submitTransactionData(Transaction transaction, Context context);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {

        void submitTransactionData(Transaction transaction, Context context);
    }

    interface Repository extends BaseFragContract.Repository {

        void submitTransactionData(Transaction transaction, Context context);
    }
}
