package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.TransactionDetails;

public class RequestDataReceiveEvent {
    public final TransactionDetails details;

    public RequestDataReceiveEvent(TransactionDetails details) {
        this.details = details;
    }
}
