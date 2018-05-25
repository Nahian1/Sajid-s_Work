/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;

public class CodeEnteredEvent {
    public final boolean isSuccess;

    public CodeEnteredEvent(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
