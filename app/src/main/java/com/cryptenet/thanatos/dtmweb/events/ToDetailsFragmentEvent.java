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

import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

public class ToDetailsFragmentEvent {
    public final int projectId;
    public final int layoutType;

    public ToDetailsFragmentEvent(int projectId, int layoutType) {
        this.projectId = projectId;
        this.layoutType = layoutType;
    }
}
