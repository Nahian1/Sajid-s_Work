/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.City;

import java.util.List;

public class CityFetchEvent {
    public List<City> cities;

    public CityFetchEvent(List<City> cities) {
        this.cities = cities;
    }
}
