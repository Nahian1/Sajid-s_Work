package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.ThreadIdentity;

import java.util.List;

public class DistinctThreadsReceived {
    public final List<ThreadIdentity> threadIdentities;

    public DistinctThreadsReceived(List<ThreadIdentity> threadIdentities) {
        this.threadIdentities = threadIdentities;
    }
}
