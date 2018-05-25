/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.code.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.CodeActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class CodeActivityRepository extends BaseRepository
        implements CodeActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(CodeActivityRepository.class);

    @Override
    public void makeResetReq(String code) {
//        String identifier = settingPreference.getString(ConstantProvider.SP_FORGOT_ID, "null");

        // make request
        //broadcast if ok
//        EventBus.getDefault().post(new PwdResetEvent());
    }
}
