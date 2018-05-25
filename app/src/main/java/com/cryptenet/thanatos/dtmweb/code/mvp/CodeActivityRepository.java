/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.code.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.events.CodeEnteredEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.CodeActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

@PerActivity
public class CodeActivityRepository extends BaseRepository
        implements CodeActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(CodeActivityRepository.class);

    @Override
    public void saveResetCode(String code) {
        preferences
                .edit()
                .putInt(ConstantProvider.SP_FORGOT_PASSWORD_CODE, Integer.parseInt(code))
                .apply();

        EventBus.getDefault().post(new CodeEnteredEvent(true));
    }
}
