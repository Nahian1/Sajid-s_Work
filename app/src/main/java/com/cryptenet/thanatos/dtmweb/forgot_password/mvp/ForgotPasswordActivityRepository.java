/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.forgot_password.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class ForgotPasswordActivityRepository extends BaseRepository
        implements ForgotActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(ForgotPasswordActivityRepository.class);

    @Override
    public void saveIdentifier(String identifier) {
        settingPreference.edit().putString(
                ConstantProvider.SP_FORGOT_ID,
                identifier
        ).apply();
    }
}
