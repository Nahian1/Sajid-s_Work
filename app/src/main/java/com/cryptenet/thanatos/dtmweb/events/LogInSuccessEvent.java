/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.User;

public class LogInSuccessEvent {
    public final User user;
    public final boolean isSuccess;

    public LogInSuccessEvent(User user, boolean isSuccess) {
        this.user = user;
        this.isSuccess = isSuccess;
    }
}
