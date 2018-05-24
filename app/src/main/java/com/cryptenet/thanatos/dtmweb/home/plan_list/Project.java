/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list;

/**
 * Created by Mobile App on 2/9/2018.
 */

public class Project {
    private String name;
    private String date;
    private String title;
    private String price;
    private int image1;
    private int image2;


    public Project(String name, String date, String title, String price, int image1, int image2) {
        this.name = name;
        this.date = date;
        this.title = title;
        this.price = price;
        this.image1 = image1;
        this.image2 = image2;
    }

    public Project() {
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public int getImage1() {
        return image1;
    }

    public int getImage2() {
        return image2;
    }
}
