/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.registration.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class RegistrationActivityModel extends BaseModel<RegistrationActivityContract.Repository, RegistrationActivity>
        implements RegistrationActivityContract.Model {
        private static final String TAG = TagProvider.getDebugTag(RegistrationActivityModel.class);

        public RegistrationActivityModel(RegistrationActivityContract.Repository repository) {
                super(repository);
        }

        @Override
        public void attachContext(RegistrationActivity context) {
                this.context = context;
        }
}
