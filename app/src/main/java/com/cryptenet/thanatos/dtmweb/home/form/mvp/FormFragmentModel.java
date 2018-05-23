/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.form.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.FormFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class FormFragmentModel extends BaseFragModel<FormFragmentContract.Repository>
        implements FormFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(FormFragmentModel.class);

    public FormFragmentModel(FormFragmentContract.Repository repository) {
        super(repository);
    }
}
