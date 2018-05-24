/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("country")
    @Expose
    private Integer country;
    @SerializedName("city")
    @Expose
    private Integer city;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("bank_account_name")
    @Expose
    private String bankAccountName;
    @SerializedName("bank_account_number")
    @Expose
    private String bankAccountNumber;
    @SerializedName("user_type")
    @Expose
    private String userType;

    /**
     * No args constructor for use in serialization
     *
     */
    public RegistrationResponse() {
    }

    /**
     *
     * @param picture
     * @param id
     * @param address
     * @param email
     * @param name
     * @param bankAccountNumber
     * @param bankName
     * @param userType
     * @param bankAccountName
     * @param city
     * @param country
     */
    public RegistrationResponse(Integer id, String name, String email, String picture, String address, Integer country, Integer city, String bankName, String bankAccountName, String bankAccountNumber, String userType) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.address = address;
        this.country = country;
        this.city = city;
        this.bankName = bankName;
        this.bankAccountName = bankAccountName;
        this.bankAccountNumber = bankAccountNumber;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "RegistrationResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                ", address='" + address + '\'' +
                ", country=" + country +
                ", city=" + city +
                ", bankName='" + bankName + '\'' +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
