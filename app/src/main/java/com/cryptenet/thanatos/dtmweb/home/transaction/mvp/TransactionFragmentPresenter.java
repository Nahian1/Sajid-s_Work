/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.transaction.mvp;

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
}
