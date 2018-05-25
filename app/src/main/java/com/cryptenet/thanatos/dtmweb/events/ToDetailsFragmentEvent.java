/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.Projects;

public class ToDetailsFragmentEvent {
    public Projects project;

    public ToDetailsFragmentEvent(Projects project) {
        this.project = project;
    }
}
