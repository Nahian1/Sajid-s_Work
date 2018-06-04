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

public class RequestDetailFragmentEvent {
    public final int transactionId;

    public RequestDetailFragmentEvent(int transactionId) {
        this.transactionId = transactionId;
    }
}
