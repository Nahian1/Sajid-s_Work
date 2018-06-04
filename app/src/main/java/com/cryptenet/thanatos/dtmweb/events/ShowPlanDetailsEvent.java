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

import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDetailed;

public class ShowPlanDetailsEvent {
    public final ProjectsDetailed detailed;

    public ShowPlanDetailsEvent(ProjectsDetailed detailed) {
        this.detailed = detailed;
    }
}
