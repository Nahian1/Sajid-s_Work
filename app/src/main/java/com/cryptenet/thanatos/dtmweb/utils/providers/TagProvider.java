/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.utils.providers;

public class TagProvider {
    public static final String TAG = TagProvider.getDebugTag(TagProvider.class);

    public static String getDebugTag(Class aClass) {
        return "crypto" + aClass.getSimpleName();
    }
}
