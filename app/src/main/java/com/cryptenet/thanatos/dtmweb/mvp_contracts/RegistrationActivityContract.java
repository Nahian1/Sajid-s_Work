/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.pojo.RegistrationInput;
import com.cryptenet.thanatos.dtmweb.pojo.UpdateProfileInput;
import com.cryptenet.thanatos.dtmweb.pojo.UpdateProfileResponse;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;

import java.io.File;

public interface RegistrationActivityContract {
    interface Presenter extends BaseContract.Presenter<RegistrationActivityContract.View> {
        void carryRegData(String reqType,File imageFile, String accType, String name, String email, String pwd,
                          String address, int countryCode, int cityCode, String bankName,
                          String bankAccName, String bankAccNumber);

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

        boolean attemptUpdateProfile(Context context, String reqType,File imageFile, String accType, String name, String email, String pwd,
                           String address, int countryCode, int cityCode, String bankName,
                           String bankAccName, String bankAccNumber);

        boolean saveUpdatedUserData(UpdateProfileResponse user);
    }

    interface Repository extends BaseContract.Repository {
        void getAllCountries();
        void getLimitedCities(int countryCode);

        boolean attemptReg(String reqType, RegistrationInput regData);

        boolean attemptUpdateProfile(Context context, String reqType, UpdateProfileInput regData);

        boolean saveUpdatedUserData(UpdateProfileResponse user, Context context);
    }
}
