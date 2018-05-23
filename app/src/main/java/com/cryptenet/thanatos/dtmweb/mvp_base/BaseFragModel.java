/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_base;

import android.os.Bundle;

import com.cryptenet.thanatos.dtmweb.mvp_contracts.BaseFragContract;

public abstract class BaseFragModel<R extends BaseFragContract.Repository>
    implements BaseFragContract.Model {

    protected R repository;

    public BaseFragModel(R repository) {
            this.repository = repository;
        }

    @Override
    public void saveState(Bundle outState) {
    }

    @Override
    public Bundle restoreState(Bundle savedState) {
        return savedState;
    }
}
