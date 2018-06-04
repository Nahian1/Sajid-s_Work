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
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.TransactionFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class TransactionFragmentPresenter extends BaseFragPresenter<TransactionFragmentContract.View, TransactionFragmentContract.Model>
        implements TransactionFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(TransactionFragmentPresenter.class);

    public TransactionFragmentPresenter(TransactionFragmentContract.Model model) {
        super(model);
    }

    @Override
    public void getTransactionDetails(Context context, int transactionId) {
        model.getTransactionDetails(context, transactionId);
    }
}
