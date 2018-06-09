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

import com.cryptenet.thanatos.dtmweb.pojo.ThreadInv;

import java.util.List;

public class ThreadProjectListReceiveEvent {
    public final List<ThreadInv> threadInvs;

    public ThreadProjectListReceiveEvent(List<ThreadInv> threadInvs) {
        this.threadInvs = threadInvs;
    }
}
