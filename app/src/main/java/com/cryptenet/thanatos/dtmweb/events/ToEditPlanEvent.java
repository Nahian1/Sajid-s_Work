/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

public class ToEditPlanEvent {
    public ProjectsRsp project;

    public ToEditPlanEvent(ProjectsRsp project) {
        this.project = project;
    }
}
