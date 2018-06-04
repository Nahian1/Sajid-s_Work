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
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class PlanListFragmentModel extends BaseFragModel<PlanListFragmentContract.Repository>
        implements PlanListFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(PlanListFragmentModel.class);

    public PlanListFragmentModel(PlanListFragmentContract.Repository repository) {
        super(repository);
    }

    public void getProjectList(Context context, String token) {
        repository.getAllProjects(context, token);
    }

    @Override
    public int checkUserType(Context context) {
        return repository.checkUserType(context);
    }

    @Override
    public void searchMyPlans(Context context, String token, String searchTerm) {
        repository.searchMyPlans(context, token, searchTerm);
    }
}
