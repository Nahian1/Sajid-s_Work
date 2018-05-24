/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.registration.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.mvp_base.BasePresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerActivity
public class RegistrationActivityPresenter extends BasePresenter<RegistrationActivityContract.View, RegistrationActivityContract.Model>
        implements RegistrationActivityContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(RegistrationActivityPresenter.class);

    public RegistrationActivityPresenter(RegistrationActivityContract.Model model) {
        super(model);
    }

    @Override
    public void getAllCountries() {
        view.updateCountries(
                model.getAllCountries()
        );
    }

    @Override
    public void getLimitedCities(int countryCode) {
        view.updateCities(
                model.getLimitedCities(countryCode)
        );
    }
}
