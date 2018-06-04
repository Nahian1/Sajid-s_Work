package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDetailed;

public class PlanDetailsRequestEvent {
    public final ProjectsDetailed detailed;

    public PlanDetailsRequestEvent(ProjectsDetailed detailed) {
        this.detailed = detailed;
    }
}
