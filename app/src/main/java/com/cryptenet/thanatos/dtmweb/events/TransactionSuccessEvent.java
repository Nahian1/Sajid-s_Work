/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;


import com.cryptenet.thanatos.dtmweb.pojo.Transaction;

public class TransactionSuccessEvent {
    public final Transaction transaction;
//    public final boolean isSuccess;

    public TransactionSuccessEvent(Transaction transaction) {
        this.transaction = transaction;
    }
}
