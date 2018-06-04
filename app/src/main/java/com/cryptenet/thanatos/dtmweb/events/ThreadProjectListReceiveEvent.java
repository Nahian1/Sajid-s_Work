package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.ThreadInv;

public class ThreadProjectListReceiveEvent {
    public final ThreadInv[] threadInvs;

    public ThreadProjectListReceiveEvent(ThreadInv[] threadInvs) {
        this.threadInvs = threadInvs;
    }
}
