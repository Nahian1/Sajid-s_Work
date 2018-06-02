package com.cryptenet.thanatos.dtmweb.events;

public class RequestDetailFragmentEvent {
    public final int transactionId;

    public RequestDetailFragmentEvent(int transactionId) {
        this.transactionId = transactionId;
    }
}
