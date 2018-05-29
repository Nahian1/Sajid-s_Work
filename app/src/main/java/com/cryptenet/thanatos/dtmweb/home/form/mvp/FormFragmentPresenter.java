/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.form.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.FormFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Transaction;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class FormFragmentPresenter extends BaseFragPresenter<FormFragmentContract.View, FormFragmentContract.Model>
        implements FormFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(FormFragmentPresenter.class);

    public FormFragmentPresenter(FormFragmentContract.Model model) {
        super(model);
    }

    @Override
    public void submitTransactionData(Transaction transaction) {

        model.submitTransactionData(transaction);
    }
}
