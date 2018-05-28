/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.investor_project.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InvestorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class InvestorProjectFragmentModel extends BaseFragModel<InvestorProjectFragmentContract.Repository>
        implements InvestorProjectFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(InvestorProjectFragmentModel.class);

    public InvestorProjectFragmentModel(InvestorProjectFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void getMyProjectList(int reqType, Context context) {
        repository.getMyProjectList(reqType,context);
    }
}
