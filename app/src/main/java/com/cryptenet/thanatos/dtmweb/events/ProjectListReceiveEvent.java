package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.Projects;

import java.util.List;

public class ProjectListReceiveEvent {
    public List<Projects> projectsList;

    public ProjectListReceiveEvent(List<Projects> projectsList) {
        this.projectsList = projectsList;
    }
}
