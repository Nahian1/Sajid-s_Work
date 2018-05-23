/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_desc.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanDescFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class PlanDescFragmentModel extends BaseFragModel<PlanDescFragmentContract.Repository>
        implements PlanDescFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(PlanDescFragmentModel.class);

    public PlanDescFragmentModel(PlanDescFragmentContract.Repository repository) {
        super(repository);
    }
}