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

public class EditPlanSuccessEvent {
    public ProjectsRsp project;

    public EditPlanSuccessEvent(ProjectsRsp project) {
        this.project = project;
    }
}
