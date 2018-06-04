package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.ThreadInitResponse;

public class ThreadIdReceiveEvent {
    public final ThreadInitResponse threadInitResponse;

    public ThreadIdReceiveEvent(ThreadInitResponse threadInitResponse) {
        this.threadInitResponse = threadInitResponse;
    }
}
