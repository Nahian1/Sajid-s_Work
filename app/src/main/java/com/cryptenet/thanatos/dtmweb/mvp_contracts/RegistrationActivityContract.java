/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;
import android.content.res.Configuration;

import com.cryptenet.thanatos.dtmweb.pojo.RegistrationInput;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;

import java.io.File;

public interface RegistrationActivityContract {
    interface Presenter extends BaseContract.Presenter<RegistrationActivityContract.View> {
        void carryRegData(File imageFile, String accType, String name, String email, String pwd,
                          String cPwd, String address, int countryCode, int cityCode,
                          String bankName, String bankAccName, String bankAccNumber);
        void getAllCountries();
        void getLimitedCities(int countryCode);
        void checkLoginState(Context context);
    }

    interface View extends BaseContract.View<RegistrationActivity> {
        void moveToSignIn();
    }

    interface Model extends BaseContract.Model<RegistrationActivity> {
        void getAllCountries();
        void getLimitedCities(int countryCode);

        boolean attemptReg(File imageFile, String accType, String name, String email, String pwd,
                           String address, int countryCode, int cityCode, String bankName,
                           String bankAccName, String bankAccNumber);

        boolean checkLoginState(Context context);
    }

    interface Repository extends BaseContract.Repository {
        void getAllCountries();
        void getLimitedCities(int countryCode);

        boolean attemptReg(RegistrationInput regData);

        boolean checkLoginState(Context context);
    }
}
