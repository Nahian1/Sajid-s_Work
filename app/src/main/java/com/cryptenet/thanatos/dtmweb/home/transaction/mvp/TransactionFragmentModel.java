/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.transaction.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.TransactionFragmentContract;

@PerFragment
public class TransactionFragmentModel extends BaseFragModel<TransactionFragmentContract.Repository>
        implements TransactionFragmentContract.Model {
//    private static final String TAG = TagProvider.getDebugTag(TransactionFragmentModel.class);

    public TransactionFragmentModel(TransactionFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void getTransactionDetails(Context context, int transactionId) {
        repository.getTransactionDetails(context, transactionId);
    }
}
