/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.plan_desc.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanDescFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class PlanDescFragmentPresenter extends BaseFragPresenter<PlanDescFragmentContract.View, PlanDescFragmentContract.Model>
        implements PlanDescFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(PlanDescFragmentPresenter.class);

    public PlanDescFragmentPresenter(PlanDescFragmentContract.Model model) {
        super(model);
    }

    @Override
    public void getLongDetails(Context context, int id) {
        model.getLongDetails(context, id);
    }

    @Override
    public void getShortDetails(Context context, int id) {
        model.getShortDetails(context, id);
    }

    @Override
    public void getThreadId(Context context, int planId) {
        model.getThreadId(context, planId);
    }
}
