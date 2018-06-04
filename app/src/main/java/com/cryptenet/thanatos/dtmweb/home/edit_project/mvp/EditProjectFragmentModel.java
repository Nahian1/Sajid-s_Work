/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.edit_project.mvp;

import android.content.Context;
import android.support.annotation.Nullable;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.EditProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRq;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class EditProjectFragmentModel extends BaseFragModel<EditProjectFragmentContract.Repository>
        implements EditProjectFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(EditProjectFragmentModel.class);

    public EditProjectFragmentModel(EditProjectFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void getAllCategories(Context context) {
        repository.getAllCategories(context);
    }

    @Override
    public void saveUpdatePlan(ProjectsRq plan, Context context, int id) {
        repository.saveUpdatePlan(plan, context, id);
    }
}
