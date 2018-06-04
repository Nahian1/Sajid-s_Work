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

public class IssueTopicChosenEvent {
    public final int issueCode;

    public IssueTopicChosenEvent(int issueCode) {
        this.issueCode = issueCode;
    }
}
