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

public class TransactionDataReceiveEvent {
    public final Transaction transaction;
//    public final boolean isSuccess;

    public TransactionDataReceiveEvent(Transaction transaction) {
        this.transaction = transaction;
    }
}
