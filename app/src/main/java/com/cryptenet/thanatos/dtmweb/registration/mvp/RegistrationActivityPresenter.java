/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.registration.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import java.io.File;

@PerActivity
public class RegistrationActivityPresenter extends BasePresenter<RegistrationActivityContract.View, RegistrationActivityContract.Model>
        implements RegistrationActivityContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(RegistrationActivityPresenter.class);

    public RegistrationActivityPresenter(RegistrationActivityContract.Model model) {
        super(model);
    }

    @Override
    public void carryRegData(File imageFile, String accType, String name, String email, String pwd,
                             String address, int countryCode, int cityCode,
                             String bankName, String bankAccName, String bankAccNumber) {
        model.attachContext(view.getActivity());
        model.attemptReg(imageFile, accType, name, email, pwd, address, countryCode, cityCode,
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
    public void checkLoginState(Context context) {
        if (model.checkLoginState(context))
            view.moveToSignIn();
    }
}
