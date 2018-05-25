/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.code.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.CodeActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class CodeActivityPresenter
        extends BasePresenter<CodeActivityContract.View, CodeActivityContract.Model>
        implements CodeActivityContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(CodeActivityPresenter.class);

    public CodeActivityPresenter(CodeActivityContract.Model model) {
        super(model);
    }

    @Override
    public void saveResetCode(String code) {
        model.saveResetCode(code);
    }
}
