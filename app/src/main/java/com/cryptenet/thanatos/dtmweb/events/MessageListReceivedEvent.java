package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.message_model.Results;

import java.util.List;

public class MessageListReceivedEvent {
    public final List<Results> messageThreadModels;

    public MessageListReceivedEvent(List<Results> messageThreadModels) {
        this.messageThreadModels = messageThreadModels;
    }
}
