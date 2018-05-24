/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.request_detail;

/**
 * Created by Mobile App on 2/9/2018.
 */

public class Project {
    private String title;
    private String price;
    private String date;
    private int status;



    public Project(String title, String price, String date, int status) {
        this.title = title;
        this.price = price;
        this.date = date;
        this.status = status;
    }

    public Project() {
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

}
