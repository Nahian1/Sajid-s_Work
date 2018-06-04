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
