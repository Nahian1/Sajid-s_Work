package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.Plans;

import java.util.List;

public class ManageProjectReceiveEvent {
    public final List<Plans> projectsRspList;

    public ManageProjectReceiveEvent(List<Plans> projectsRspList) {
        this.projectsRspList = projectsRspList;
    }
}
