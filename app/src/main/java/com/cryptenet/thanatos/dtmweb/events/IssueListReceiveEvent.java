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

import com.cryptenet.thanatos.dtmweb.pojo.IssueParent;

import java.util.List;

public class IssueListReceiveEvent {
    public final List<IssueParent> issueParents;

    public IssueListReceiveEvent(List<IssueParent> issueParents) {
        this.issueParents = issueParents;
    }
}
