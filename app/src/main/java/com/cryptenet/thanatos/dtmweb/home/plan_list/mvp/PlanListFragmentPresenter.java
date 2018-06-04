/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class PlanListFragmentPresenter extends BaseFragPresenter<PlanListFragmentContract.View, PlanListFragmentContract.Model>
        implements PlanListFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(PlanListFragmentPresenter.class);

    public PlanListFragmentPresenter(PlanListFragmentContract.Model model) {
        super(model);
    }

    public void getProjectList(Context context, String token) {
        model.getProjectList(context, token);
    }

    @Override
    public void checkUserType(ProjectsRsp projectsRsp, Context context) {

        view.toDetailsView(projectsRsp, model.checkUserType(context));
    }

    @Override
    public void searchMyPlans(Context context, String token, String searchTerm) {
        model.searchMyPlans(context, token, searchTerm);
    }
}
