package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.IssueParent;

import java.util.List;

public class IssueListReceiveEvent {
    public final List<IssueParent> issueParents;

    public IssueListReceiveEvent(List<IssueParent> issueParents) {
        this.issueParents = issueParents;
    }
}
