/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_long_desc.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanLongDescFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class PlanLongDescFragmentPresenter extends BaseFragPresenter<PlanLongDescFragmentContract.View, PlanLongDescFragmentContract.Model>
        implements PlanLongDescFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(PlanLongDescFragmentPresenter.class);

    public PlanLongDescFragmentPresenter(PlanLongDescFragmentContract.Model model) {
        super(model);
    }
}
