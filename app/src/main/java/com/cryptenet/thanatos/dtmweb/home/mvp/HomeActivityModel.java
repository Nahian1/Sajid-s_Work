/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.NavHeader;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class HomeActivityModel extends BaseModel<HomeActivityContract.Repository, HomeActivity>
        implements HomeActivityContract.Model {
    private static final String TAG = TagProvider.getDebugTag(HomeActivityModel.class);

    public HomeActivityModel(HomeActivityContract.Repository repository) {
        super(repository);
    }

    @Override
    public void attachContext(HomeActivity context) {
        this.context = context;
    }

    @Override
    public NavHeader getNavHeaderData() {
        return repository.getNavHeaderData();
    }
}
