/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.edit_project.mvp;

import android.content.Context;
import android.support.annotation.Nullable;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.EditProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRq;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class EditProjectFragmentPresenter extends BaseFragPresenter<EditProjectFragmentContract.View, EditProjectFragmentContract.Model>
        implements EditProjectFragmentContract.Presenter {
        private static final String TAG = TagProvider.getDebugTag(EditProjectFragmentPresenter.class);

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
