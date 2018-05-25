/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_base;

import android.content.SharedPreferences;

import com.cryptenet.thanatos.dtmweb.mvp_contracts.BaseContract;

import javax.inject.Inject;

public abstract class BaseRepository implements BaseContract.Repository {
    @Inject
    protected SharedPreferences preferences;

    public BaseRepository() {
    }
}
