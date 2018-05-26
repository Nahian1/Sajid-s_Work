/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.RegistrationResponse;

public class RegistrationSuccessEvent {
    public final RegistrationResponse registrationResponse;

    public RegistrationSuccessEvent(RegistrationResponse registrationResponse) {
        this.registrationResponse = registrationResponse;
    }
}
