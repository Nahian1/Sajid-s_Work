package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.message_model.Results;

public class onMessageSentEvent {
    public final Results results;
    public onMessageSentEvent(Results results) {
        this.results = results;
    }
}
