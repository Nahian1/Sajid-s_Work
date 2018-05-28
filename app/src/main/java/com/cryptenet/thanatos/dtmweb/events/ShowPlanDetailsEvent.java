package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDetailed;

public class ShowPlanDetailsEvent {
    public final ProjectsDetailed detailed;

    public ShowPlanDetailsEvent(ProjectsDetailed detailed) {
        this.detailed = detailed;
    }
}
