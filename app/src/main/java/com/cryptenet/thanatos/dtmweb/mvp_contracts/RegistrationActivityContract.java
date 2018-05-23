/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;

import java.util.List;

public interface RegistrationActivityContract {
    interface Presenter extends BaseContract.Presenter<RegistrationActivityContract.View> {
        void getAllCountries();
        void getLimitedCities(int countryCode);
    }

    interface View extends BaseContract.View<RegistrationActivity> {
        void updateCountries(List<Country> countries);
        void updateCities(List<City> cities);
    }

    interface Model extends BaseContract.Model<RegistrationActivity> {
        List<Country> getAllCountries();
        List<City> getLimitedCities(int countryCode);
    }

    interface Repository extends BaseContract.Repository {
        List<Country> getAllCountries();
        List<City> getLimitedCities(int countryCode);
    }
}
