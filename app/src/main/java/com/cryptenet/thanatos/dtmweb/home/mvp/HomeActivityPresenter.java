/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
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
}
