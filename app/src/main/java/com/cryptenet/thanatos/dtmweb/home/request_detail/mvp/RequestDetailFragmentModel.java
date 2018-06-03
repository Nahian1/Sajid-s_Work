/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.request_detail.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RequestDetailFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class RequestDetailFragmentModel extends BaseFragModel<RequestDetailFragmentContract.Repository>
        implements RequestDetailFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(RequestDetailFragmentModel.class);

    public RequestDetailFragmentModel(RequestDetailFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void getTransactionDetails(Context context, int transactionId) {
        repository.getTransactionDetails(context, transactionId);
    }

    @Override
    public void confirmRequest(Context context, int transactionId) {
        repository.confirmRequest(context, transactionId);
    }
}
