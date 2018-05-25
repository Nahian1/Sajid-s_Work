/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_long_desc.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanLongDescFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class PlanLongDescFragmentModel extends BaseFragModel<PlanLongDescFragmentContract.Repository>
        implements PlanLongDescFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(PlanLongDescFragmentModel.class);

    public PlanLongDescFragmentModel(PlanLongDescFragmentContract.Repository repository) {
        super(repository);
    }
}
