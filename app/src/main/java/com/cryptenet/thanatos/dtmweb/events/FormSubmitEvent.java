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


import com.cryptenet.thanatos.dtmweb.pojo.Transaction;

public class FormSubmitEvent {
    public Transaction transaction;

    public FormSubmitEvent(Transaction transaction) {
        this.transaction = transaction;
    }
}
