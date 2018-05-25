/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.thread_project;

/**
 * Created by Mobile App on 2/9/2018.
 */

public class Project {
    private String title;
    private String name;
    private String date;
    private int image;



    public Project(String title, String name, String date, int image) {
        this.title = title;
        this.name = name;
        this.date = date;
        this.image = image;
    }

    public Project() {
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }



}
