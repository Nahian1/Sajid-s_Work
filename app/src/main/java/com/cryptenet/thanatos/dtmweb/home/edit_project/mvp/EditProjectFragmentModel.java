/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.edit_project.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.EditProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class EditProjectFragmentModel extends BaseFragModel<EditProjectFragmentContract.Repository>
        implements EditProjectFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(EditProjectFragmentModel.class);

    public EditProjectFragmentModel(EditProjectFragmentContract.Repository repository) {
        super(repository);
    }
}
