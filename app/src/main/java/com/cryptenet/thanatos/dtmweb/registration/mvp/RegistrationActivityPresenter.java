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
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.UpdateProfileResponse;

import java.io.File;

@PerActivity
public class RegistrationActivityPresenter extends BasePresenter<RegistrationActivityContract.View, RegistrationActivityContract.Model>
        implements RegistrationActivityContract.Presenter {
//    private static final String TAG = TagProvider.getDebugTag(RegistrationActivityPresenter.class);

    public RegistrationActivityPresenter(RegistrationActivityContract.Model model) {
        super(model);
    }

    @Override
    public void carryRegData(String reqType, File imageFile, String accType, String name, String email, String pwd,
                             String address, int countryCode, int cityCode,
                             String bankName, String bankAccName, String bankAccNumber) {
        model.attachContext(view.getActivity());
        model.attemptReg(reqType, imageFile, accType, name, email, pwd, address, countryCode, cityCode,
                bankName, bankAccName, bankAccNumber);
    }

    @Override
    public void carryUpdateProfileData(Context context, String reqType, File imageFile, String accType, String name, String email, String pwd,
                                       String address, int countryCode, int cityCode, String bankName, String bankAccName,
                                       String bankAccNumber) {
        model.attachContext(view.getActivity());
        model.attemptUpdateProfile(view.getActivity(),reqType, imageFile, accType, name, email, pwd, address, countryCode, cityCode,
                bankName, bankAccName, bankAccNumber);
    }

    @Override
    public void getAllCountries() {
        model.getAllCountries();
    }

    @Override
    public void getLimitedCities(int countryCode) {
        model.getLimitedCities(countryCode);
    }


    @Override
    public boolean saveUpdatedUserData(UpdateProfileResponse user) {
        model.attachContext(view.getActivity());
        return model.saveUpdatedUserData(user);
    }
}
