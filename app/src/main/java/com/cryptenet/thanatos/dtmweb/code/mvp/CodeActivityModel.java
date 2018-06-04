/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.code.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.code.CodeActivity;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.CodeActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class CodeActivityModel
        extends BaseModel<CodeActivityContract.Repository, CodeActivity>
        implements CodeActivityContract.Model {
    private static final String TAG = TagProvider.getDebugTag(CodeActivityModel.class);


    public CodeActivityModel(CodeActivityContract.Repository repository) {
        super(repository);
    }


    @Override
    public void attachContext(CodeActivity context) {
        this.context = context;
    }

    @Override
    public void saveResetCode(String code, Context context) {
        repository.saveResetCode(code, context);
    }
}
