/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.NavHeader;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class HomeActivityRepository extends BaseRepository
        implements HomeActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(HomeActivityRepository.class);

    @Override
    public NavHeader getNavHeaderData() {
        return new NavHeader(
                settingPreference.getString(ConstantProvider.NAV_PP_URL, null),
                settingPreference.getString(ConstantProvider.NAV_NAME, null),
                settingPreference.getString(ConstantProvider.NAV_TYPE, null),
                settingPreference.getString(ConstantProvider.NAV_ADDRESS, null),
                settingPreference.getString(ConstantProvider.NAV_DETAILS, null)
        );
    }
}
