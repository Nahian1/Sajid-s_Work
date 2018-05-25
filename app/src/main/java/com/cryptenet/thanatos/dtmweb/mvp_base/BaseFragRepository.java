/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_base;

import android.content.SharedPreferences;

import com.cryptenet.thanatos.dtmweb.mvp_contracts.BaseFragContract;

import javax.inject.Inject;

public class BaseFragRepository implements BaseFragContract.Repository {
    @Inject
    protected SharedPreferences preferences;

    public BaseFragRepository() {
    }
}
