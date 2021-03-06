/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.registration.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.RegistrationInput;
import com.cryptenet.thanatos.dtmweb.pojo.UpdateProfileInput;
import com.cryptenet.thanatos.dtmweb.pojo.UpdateProfileResponse;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;

import java.io.File;

@PerActivity
public class RegistrationActivityModel extends BaseModel<RegistrationActivityContract.Repository, RegistrationActivity>
        implements RegistrationActivityContract.Model {
//    private static final String TAG = TagProvider.getDebugTag(RegistrationActivityModel.class);

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
    public boolean attemptReg(String reqType, File imageFile, String accType, String name, String email,
                              String pwd, String address, int countryCode, int cityCode,
                              String bankName, String bankAccName, String bankAccNumber) {
        RegistrationInput input = new RegistrationInput(
                accType, name, email, imageFile, pwd, address, countryCode, cityCode,
                bankName, bankAccName, bankAccNumber
        );

        return repository.attemptReg(reqType, input);
    }


    @Override
    public boolean attemptUpdateProfile(Context context, String reqType, File imageFile, String accType, String name, String email,
                                        String pwd, String address, int countryCode, int cityCode,
                                        String bankName, String bankAccName, String bankAccNumber) {
        UpdateProfileInput input = new UpdateProfileInput(
                accType, name, email, imageFile, pwd, address, countryCode, cityCode,
                bankName, bankAccName, bankAccNumber
        );

        return repository.attemptUpdateProfile(context, reqType, input);
    }

    @Override
    public boolean saveUpdatedUserData(UpdateProfileResponse user) {
        return repository.saveUpdatedUserData(user, context);
    }
}
