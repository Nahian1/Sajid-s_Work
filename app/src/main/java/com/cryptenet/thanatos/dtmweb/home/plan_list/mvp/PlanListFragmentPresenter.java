/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

@PerFragment
public class PlanListFragmentPresenter extends BaseFragPresenter<PlanListFragmentContract.View, PlanListFragmentContract.Model>
        implements PlanListFragmentContract.Presenter {
//    private static final String TAG = TagProvider.getDebugTag(PlanListFragmentPresenter.class);

    public PlanListFragmentPresenter(PlanListFragmentContract.Model model) {
        super(model);
    }

    public void getProjectList(Context context, int offset) {
        model.getProjectList(context, offset);
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
