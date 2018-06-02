/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.mvp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
    public NavHeader getNavHeaderData(Context context) {
        return new NavHeader(
                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_PICTURE_URL, null),
                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_NAME, null),
                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_USER_TYPE, null),
                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ADDRESS, null)
        );
    }

    @Override
    public boolean clearUserData(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = sharedPreferences.getString(ConstantProvider.LOCALE, "en");
        return PreferenceManager.getDefaultSharedPreferences(context).edit().clear().putString(ConstantProvider.LOCALE, lang).commit();
    }
}
