/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;

public class UpdateProfileFailureEvent {
    public final boolean isFailure;

    public UpdateProfileFailureEvent(boolean isFailure) {
        this.isFailure = isFailure;
    }
}
