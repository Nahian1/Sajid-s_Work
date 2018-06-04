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

public interface RequestDetailFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<RequestDetailFragmentContract.View> {
        void getTransactionDetails(Context context, int transactionId, int userType);
        void confirmRequest(Context context, int transactionId);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getTransactionDetails(Context context, int transactionId, int userType);
        void confirmRequest(Context context, int transactionId);
    }

    interface Repository extends BaseFragContract.Repository {
        void getTransactionDetails(Context context, int transactionId, int userType);
        void confirmRequest(Context context, int transactionId);
    }
}
