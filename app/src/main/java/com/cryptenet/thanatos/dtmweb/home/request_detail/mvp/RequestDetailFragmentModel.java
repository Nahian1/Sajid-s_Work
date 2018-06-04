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
    public void getTransactionDetails(Context context, int transactionId, int userType) {
        repository.getTransactionDetails(context, transactionId, userType);
    }

    @Override
    public void confirmRequest(Context context, int transactionId) {
        repository.confirmRequest(context, transactionId);
    }
}
