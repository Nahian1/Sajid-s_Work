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

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRsp implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_type")
    @Expose
    private String userType;
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
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    public final static Creator<UserRsp> CREATOR = new Creator<UserRsp>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UserRsp createFromParcel(Parcel in) {
            return new UserRsp(in);
        }

        public UserRsp[] newArray(int size) {
            return (new UserRsp[size]);
        }

    }
    ;

    protected UserRsp(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userType = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.picture = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.city = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.bankName = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountName = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.cityName = ((String) in.readValue((String.class.getClassLoader())));
        this.countryName = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRsp() {
    }

    /**
     * 
     * @param picture
     * @param id
     * @param countryName
     * @param address
     * @param email
     * @param cityName
     * @param name
     * @param bankAccountNumber
     * @param bankName
     * @param bankAccountName
     * @param city
     * @param country
     * @param userType
     */
    public UserRsp(Integer id, String userType, String name, String email, String picture, String address, Integer country, Integer city, String bankName, String bankAccountName, String bankAccountNumber, String cityName, String countryName) {
        super();
        this.id = id;
        this.userType = userType;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.address = address;
        this.country = country;
        this.city = city;
        this.bankName = bankName;
        this.bankAccountName = bankAccountName;
        this.bankAccountNumber = bankAccountNumber;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(userType);
        dest.writeValue(name);
        dest.writeValue(email);
        dest.writeValue(picture);
        dest.writeValue(address);
        dest.writeValue(country);
        dest.writeValue(city);
        dest.writeValue(bankName);
        dest.writeValue(bankAccountName);
        dest.writeValue(bankAccountNumber);
        dest.writeValue(cityName);
        dest.writeValue(countryName);
    }

    @Override
    public String toString() {
        return "UserRsp{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                ", address='" + address + '\'' +
                ", country=" + country +
                ", city=" + city +
                ", bankName='" + bankName + '\'' +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }

    public int describeContents() {
        return  0;
    }

}
