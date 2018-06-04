/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.request_detail.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RequestDetailFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class RequestDetailFragmentPresenter extends BaseFragPresenter<RequestDetailFragmentContract.View, RequestDetailFragmentContract.Model>
        implements RequestDetailFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(RequestDetailFragmentPresenter.class);

    public RequestDetailFragmentPresenter(RequestDetailFragmentContract.Model model) {
        super(model);
    }

    @Override
    public void getTransactionDetails(Context context, int transactionId, int userType) {
        model.getTransactionDetails(context, transactionId, userType);
    }

    @Override
    public void confirmRequest(Context context, int transactionId) {
        model.confirmRequest(context, transactionId);
    }
}
