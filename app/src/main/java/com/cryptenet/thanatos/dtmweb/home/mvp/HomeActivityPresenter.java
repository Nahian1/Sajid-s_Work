/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class HomeActivityPresenter
        extends BasePresenter<HomeActivityContract.View, HomeActivityContract.Model>
        implements HomeActivityContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(HomeActivityPresenter.class);

    public HomeActivityPresenter(HomeActivityContract.Model model) {
        super(model);
    }

    @Override
    public void getNavHeaderData(Context context) {
        view.getNavHeaderData(model.getNavHeaderData(context));
    }

    @Override
    public void clearUserData(Context context) {
        if (model.clearUserData(context))
            view.userDataCleaned();
    }
}
