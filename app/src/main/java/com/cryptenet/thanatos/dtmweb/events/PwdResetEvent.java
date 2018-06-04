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

public class PwdResetEvent {
    public final boolean isSuccess;

    public PwdResetEvent(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
