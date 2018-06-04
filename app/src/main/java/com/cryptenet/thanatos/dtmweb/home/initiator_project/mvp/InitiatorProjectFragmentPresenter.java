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
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InitiatorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class InitiatorProjectFragmentPresenter extends BaseFragPresenter<InitiatorProjectFragmentContract.View, InitiatorProjectFragmentContract.Model>
        implements InitiatorProjectFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(InitiatorProjectFragmentPresenter.class);

    public InitiatorProjectFragmentPresenter(InitiatorProjectFragmentContract.Model model) {
        super(model);
    }

    @Override
    public void getMyProjectList(int reqType, Context context) {
        model.getMyProjectList(reqType,context);
    }
}
