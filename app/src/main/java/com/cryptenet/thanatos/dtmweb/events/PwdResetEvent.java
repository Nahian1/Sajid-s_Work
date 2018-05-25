package com.cryptenet.thanatos.dtmweb.events;

public class PwdResetEvent {
    public final boolean isSuccess;

    public PwdResetEvent(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
