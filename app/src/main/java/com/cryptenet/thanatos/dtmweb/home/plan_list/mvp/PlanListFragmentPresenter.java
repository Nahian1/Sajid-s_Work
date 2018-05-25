/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class PlanListFragmentPresenter extends BaseFragPresenter<PlanListFragmentContract.View, PlanListFragmentContract.Model>
        implements PlanListFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(PlanListFragmentPresenter.class);

    public PlanListFragmentPresenter(PlanListFragmentContract.Model model) {
        super(model);
    }

    public void getProjectList() {
        model.getProjectList();
    }
}
