/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.transaction.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.TransactionFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class TransactionFragmentModel extends BaseFragModel<TransactionFragmentContract.Repository>
        implements TransactionFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(TransactionFragmentModel.class);

    public TransactionFragmentModel(TransactionFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void getTransactionDetails(Context context, int transactionId) {
        repository.getTransactionDetails(context, transactionId);
    }
}
