/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.mvp_base;

import android.app.Activity;
import android.os.Bundle;

import com.cryptenet.thanatos.dtmweb.mvp_contracts.BaseContract;

public abstract class BaseModel<R extends BaseContract.Repository, A extends Activity>
        implements BaseContract.Model<A> {
    protected R repository;
    protected A context;

    public BaseModel(R repository) {
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
