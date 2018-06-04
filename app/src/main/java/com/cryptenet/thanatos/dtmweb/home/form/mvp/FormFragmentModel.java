/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.form.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.FormFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Transaction;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class FormFragmentModel extends BaseFragModel<FormFragmentContract.Repository>
        implements FormFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(FormFragmentModel.class);

    public FormFragmentModel(FormFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void submitTransactionData(Transaction transaction, Context context) {

        repository.submitTransactionData(transaction, context);
    }
}
