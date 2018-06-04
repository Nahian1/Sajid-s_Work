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
