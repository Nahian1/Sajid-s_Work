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

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.CodeActivityContract;

@PerActivity
public class CodeActivityPresenter
        extends BasePresenter<CodeActivityContract.View, CodeActivityContract.Model>
        implements CodeActivityContract.Presenter {
//    private static final String TAG = TagProvider.getDebugTag(CodeActivityPresenter.class);

    public CodeActivityPresenter(CodeActivityContract.Model model) {
        super(model);
    }

    @Override
    public void saveResetCode(String code, Context context) {
        model.saveResetCode(code, context);
    }
}
