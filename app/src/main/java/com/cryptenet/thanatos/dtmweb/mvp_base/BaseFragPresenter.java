/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_base;

import android.os.Bundle;

import com.cryptenet.thanatos.dtmweb.mvp_contracts.BaseFragContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

public abstract class BaseFragPresenter <V extends BaseFragContract.View, M extends BaseFragContract.Model>
        implements BaseFragContract.Presenter<V> {
    private static final String TAG = TagProvider.getDebugTag(BaseFragPresenter.class);
    protected V view;
    protected M model;

    public BaseFragPresenter(M model) {
        this.model = model;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void saveState(Bundle outState) {
        model.saveState(outState);
    }

    @Override
    public void restoreState(Bundle savedState) {
        view.restoreState(model.restoreState(savedState));
    }
}
