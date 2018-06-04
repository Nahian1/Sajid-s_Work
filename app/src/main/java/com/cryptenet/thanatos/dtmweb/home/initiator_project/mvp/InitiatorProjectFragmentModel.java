/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.initiator_project.mvp;

import android.content.Context;

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
    public void getMyProjectList(int reqType, Context context) {
        repository.getMyProjectList(reqType,context);
    }
}
