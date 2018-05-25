/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.pojo;

public class Login {
    private String username;
    private String password;
    private String granttype;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        this.granttype = "password";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGranttype(String granttype) {
        this.granttype = granttype;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGranttype() {
        return granttype;
    }
}
