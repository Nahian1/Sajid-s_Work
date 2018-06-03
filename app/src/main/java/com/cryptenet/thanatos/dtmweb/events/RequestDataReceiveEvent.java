package com.cryptenet.thanatos.dtmweb.events;

import android.support.annotation.Nullable;

import com.cryptenet.thanatos.dtmweb.pojo.TransactionDetails;
import com.cryptenet.thanatos.dtmweb.pojo.User;

public class RequestDataReceiveEvent {
    public final TransactionDetails details;
    public final User user;

    public RequestDataReceiveEvent(TransactionDetails details, @Nullable User user) {
        this.details = details;
        this.user = user;
    }
}
