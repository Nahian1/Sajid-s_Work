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

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.EditProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRq;

@PerFragment
public class EditProjectFragmentPresenter extends BaseFragPresenter<EditProjectFragmentContract.View, EditProjectFragmentContract.Model>
        implements EditProjectFragmentContract.Presenter {
//        private static final String TAG = TagProvider.getDebugTag(EditProjectFragmentPresenter.class);

        public EditProjectFragmentPresenter(EditProjectFragmentContract.Model model) {
        super(model);
        }

        @Override
        public void getAllCategories(Context context) {
                model.getAllCategories(context);
        }

        @Override
        public void saveUpdatePlan(ProjectsRq plan, Context context, int id) {
                model.saveUpdatePlan(plan, context, id);
        }
}
