/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;


public class LogInSuccessEvent {
    public final String string;
    public final boolean isSuccess;

    public LogInSuccessEvent(String string, boolean isSuccess) {
        this.string = string;
        this.isSuccess = isSuccess;
    }
}
