/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.code.mvp;

import android.content.Context;
import android.preference.PreferenceManager;

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
    public void saveResetCode(String code, Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(ConstantProvider.SP_FORGOT_PASSWORD_CODE, Integer.parseInt(code))
                .apply();

        EventBus.getDefault().post(new CodeEnteredEvent(true));
    }
}
