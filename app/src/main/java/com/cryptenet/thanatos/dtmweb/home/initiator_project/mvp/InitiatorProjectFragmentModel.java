/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.initiator_project.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InitiatorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class InitiatorProjectFragmentModel extends BaseFragModel<InitiatorProjectFragmentContract.Repository>
        implements InitiatorProjectFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(InitiatorProjectFragmentModel.class);

    public InitiatorProjectFragmentModel(InitiatorProjectFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void getMyProjectList(int reqType) {
        repository.getMyProjectList(reqType);
    }
}
