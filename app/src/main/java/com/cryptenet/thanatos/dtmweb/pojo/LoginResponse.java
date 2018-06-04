/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("bank_account_name")
    @Expose
    public String bankAccountName;
    @SerializedName("bank_account_number")
    @Expose
    public String bankAccountNumber;
    @SerializedName("bank_name")
    @Expose
    public String bankName;
    @SerializedName("city")
    @Expose
    public int city;
    @SerializedName("country")
    @Expose
    public int country;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("expires_in")
    @Expose
    public int expiresIn;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("picture")
    @Expose
    public String picture;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;
    @SerializedName("scope")
    @Expose
    public String scope;
    @SerializedName("token_type")
    @Expose
    public String tokenType;
    @SerializedName("user_type")
    @Expose
    public String userType;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", address='" + address + '\'' +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", city=" + city +
                ", country=" + country +
                ", email='" + email + '\'' +
                ", expiresIn=" + expiresIn +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", scope='" + scope + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
