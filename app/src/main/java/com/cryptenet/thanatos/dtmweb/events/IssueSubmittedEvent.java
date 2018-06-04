package com.cryptenet.thanatos.dtmweb.events;

public class IssueSubmittedEvent {
    public final boolean isSubmitted;

    public IssueSubmittedEvent(boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }
}
