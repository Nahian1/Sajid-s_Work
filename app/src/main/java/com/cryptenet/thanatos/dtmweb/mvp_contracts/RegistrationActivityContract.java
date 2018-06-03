/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.pojo.RegistrationInput;
import com.cryptenet.thanatos.dtmweb.pojo.UpdateProfileInput;
import com.cryptenet.thanatos.dtmweb.pojo.UpdateProfileResponse;
import com.cryptenet.thanatos.dtmweb.pojo.User;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;

import java.io.File;

public interface RegistrationActivityContract {
    interface Presenter extends BaseContract.Presenter<RegistrationActivityContract.View> {
        void carryRegData(String reqType,File imageFile, String accType, String name, String email, String pwd,
                          String address, int countryCode, int cityCode, String bankName,
                          String bankAccName, String bankAccNumber);

        //update profile -- ashif
        void carryUpdateProfileData(Context context,String reqType,File imageFile, String accType, String name, String email, String pwd,
                          String address, int countryCode, int cityCode, String bankName,
                          String bankAccName, String bankAccNumber);

        void getAllCountries();
        void getLimitedCities(int countryCode);

        boolean saveUpdatedUserData(UpdateProfileResponse user);
    }

    interface View extends BaseContract.View<RegistrationActivity> {
    }

    interface Model extends BaseContract.Model<RegistrationActivity> {
        void getAllCountries();
        void getLimitedCities(int countryCode);

        boolean attemptReg(String reqType,File imageFile, String accType, String name, String email, String pwd,
                           String address, int countryCode, int cityCode, String bankName,
                           String bankAccName, String bankAccNumber);

        //update profile -- ashif
        boolean attemptUpdateProfile(Context context, String reqType,File imageFile, String accType, String name, String email, String pwd,
                           String address, int countryCode, int cityCode, String bankName,
                           String bankAccName, String bankAccNumber);

        boolean saveUpdatedUserData(UpdateProfileResponse user);
    }

    interface Repository extends BaseContract.Repository {
        void getAllCountries();
        void getLimitedCities(int countryCode);

        boolean attemptReg(String reqType, RegistrationInput regData);

        //update profile -- ashif
        boolean attemptUpdateProfile(Context context, String reqType, UpdateProfileInput regData);

        boolean saveUpdatedUserData(UpdateProfileResponse user, Context context);
    }
}
