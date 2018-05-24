/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.forgot_password.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.forgot_password.ForgotPasswordActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ForgotActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class ForgotPasswordActivityModel extends BaseModel<ForgotActivityContract.Repository, ForgotPasswordActivity>
        implements ForgotActivityContract.Model {
        private static final String TAG = TagProvider.getDebugTag(ForgotPasswordActivityModel.class);

        public ForgotPasswordActivityModel(ForgotActivityContract.Repository repository) {
                super(repository);
                }

        @Override
        public void attachContext(ForgotPasswordActivity context) {
                this.context = context;
                }

        @Override
        public void saveIdentifier(String identifier) {
            repository.saveIdentifier(identifier);
        }
}
