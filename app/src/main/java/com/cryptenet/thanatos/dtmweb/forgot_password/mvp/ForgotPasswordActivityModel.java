/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.forgot_password.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.forgot_password.ForgotPasswordActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;

@PerActivity
public class ForgotPasswordActivityModel extends BaseModel<ForgotActivityContract.Repository, ForgotPasswordActivity>
        implements ForgotActivityContract.Model {
//        private static final String TAG = TagProvider.getDebugTag(ForgotPasswordActivityModel.class);

        public ForgotPasswordActivityModel(ForgotActivityContract.Repository repository) {
                super(repository);
                }

        @Override
        public void attachContext(ForgotPasswordActivity context) {
                this.context = context;
                }

        @Override
        public void sendIdentifier(String identifier) {
                repository.sendIdentifier(identifier);
        }

        @Override
        public void saveIdentifier(String identifier, Context context) {
            repository.saveIdentifierSP(identifier, context);
        }
}
