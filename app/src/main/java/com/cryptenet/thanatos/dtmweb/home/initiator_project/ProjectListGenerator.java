/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.initiator_project;

import com.cryptenet.thanatos.dtmweb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobile App on 2/9/2018.
 */

public final class ProjectListGenerator {

    public static List<Project> generateProjects(){
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",1,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",1,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",1,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",1,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));
        projects.add(new Project("Title","Price","Date",0,R.drawable.editproject));





        return projects;
    }


}