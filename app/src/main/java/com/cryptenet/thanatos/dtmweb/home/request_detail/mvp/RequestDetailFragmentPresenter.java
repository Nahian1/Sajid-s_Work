/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.request_detail.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RequestDetailFragmentContract;

@PerFragment
public class RequestDetailFragmentPresenter extends BaseFragPresenter<RequestDetailFragmentContract.View, RequestDetailFragmentContract.Model>
        implements RequestDetailFragmentContract.Presenter {
//    private static final String TAG = TagProvider.getDebugTag(RequestDetailFragmentPresenter.class);

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
