/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
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
