/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.Country;

import java.util.List;

public class CountryFetchEvent {
    public List<Country> countries;

    public CountryFetchEvent(List<Country> countries) {
        this.countries = countries;
    }
}
