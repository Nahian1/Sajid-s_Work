/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.events;

public class CodeEnteredEvent {
    public final boolean isSuccess;

    public CodeEnteredEvent(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
