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
    public NavHeader getNavHeaderData(Context context) {
        return repository.getNavHeaderData(context);
    }

    @Override
    public boolean clearUserData(Context context) {
        return repository.clearUserData(context);
    }

    @Override
    public void sendFCMData(Context context) {
        repository.sendFCMData(context);
    }
}
