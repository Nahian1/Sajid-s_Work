/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;


public class LogInFailureEvent {
    public final boolean isFailure;

    public LogInFailureEvent(boolean isFailure) {
        this.isFailure = isFailure;
    }
}
