/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.pojo;

import java.util.List;

public class PojoCountry {

   private int countryId;
   private String countryName;

    public PojoCountry(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public PojoCountry() {
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
