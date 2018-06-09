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

public class FCMRsp implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("registration_id")
    @Expose
    private String registrationId;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("type")
    @Expose
    private String type;
    public final static Creator<FCMRsp> CREATOR = new Creator<FCMRsp>() {
        @SuppressWarnings({
            "unchecked"
        })
        public FCMRsp createFromParcel(Parcel in) {
            return new FCMRsp(in);
        }

        public FCMRsp[] newArray(int size) {
            return (new FCMRsp[size]);
        }
    };

    protected FCMRsp(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.registrationId = ((String) in.readValue((String.class.getClassLoader())));
        this.deviceId = ((String) in.readValue((String.class.getClassLoader())));
        this.active = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public FCMRsp() {
    }

    /**
     * 
     * @param id
     * @param registrationId
     * @param name
     * @param active
     * @param type
     * @param deviceId
     */
    public FCMRsp(Integer id, String name, String registrationId, String deviceId, Boolean active, String type) {
        super();
        this.id = id;
        this.name = name;
        this.registrationId = registrationId;
        this.deviceId = deviceId;
        this.active = active;
        this.type = type;
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

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(registrationId);
        dest.writeValue(deviceId);
        dest.writeValue(active);
        dest.writeValue(type);
    }

    @Override
    public String toString() {
        return "FCMRsp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationId='" + registrationId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", active=" + active +
                ", type='" + type + '\'' +
                '}';
    }

    public int describeContents() {
        return  0;
    }

}
