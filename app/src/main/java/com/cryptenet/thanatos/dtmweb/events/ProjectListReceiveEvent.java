package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

import java.util.List;

public class ProjectListReceiveEvent {
    public final List<ProjectsRsp> projectsRspList;

    public ProjectListReceiveEvent(List<ProjectsRsp> projectsRspList) {
        this.projectsRspList = projectsRspList;
    }
}
