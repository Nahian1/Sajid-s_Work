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

import java.util.List;

public class IssueParent implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("topics")
    @Expose
    private List<Topic> topics = null;
    public final static Creator<IssueParent> CREATOR = new Creator<IssueParent>() {


        @SuppressWarnings({
            "unchecked"
        })
        public IssueParent createFromParcel(Parcel in) {
            return new IssueParent(in);
        }

        public IssueParent[] newArray(int size) {
            return (new IssueParent[size]);
        }

    };

    protected IssueParent(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.topics, (Topic.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public IssueParent() {
    }

    /**
     * 
     * @param id
     * @param topics
     * @param name
     */
    public IssueParent(Integer id, String name, List<Topic> topics) {
        super();
        this.id = id;
        this.name = name;
        this.topics = topics;
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(topics);
    }

    @Override
    public String toString() {
        return "IssueParent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", topics=" + topics +
                '}';
    }

    public int describeContents() {
        return  0;
    }

}
