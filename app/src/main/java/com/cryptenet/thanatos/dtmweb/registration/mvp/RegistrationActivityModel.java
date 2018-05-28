/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.registration.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.RegistrationInput;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import java.io.File;

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

        @Override
        public void getAllCountries() {
                repository.getAllCountries();
        }

        @Override
        public void getLimitedCities(int countryCode) {
                repository.getLimitedCities(countryCode);
        }

        @Override
        public boolean attemptReg(File imageFile, String accType, String name, String email,
                                  String pwd, String address, int countryCode, int cityCode,
                                  String bankName, String bankAccName, String bankAccNumber) {
                RegistrationInput input = new RegistrationInput(
                        accType, name, email, imageFile, pwd, address, countryCode, cityCode,
                        bankName, bankAccName, bankAccNumber
                );

                return repository.attemptReg(input);
        }

        @Override
        public boolean checkLoginState(Context context) {
                return repository.checkLoginState(context);
        }
}
