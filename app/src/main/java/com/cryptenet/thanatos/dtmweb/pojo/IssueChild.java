
package com.cryptenet.thanatos.dtmweb.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IssueChild implements Parcelable
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
    public final static Creator<IssueChild> CREATOR = new Creator<IssueChild>() {


        @SuppressWarnings({
            "unchecked"
        })
        public IssueChild createFromParcel(Parcel in) {
            return new IssueChild(in);
        }

        public IssueChild[] newArray(int size) {
            return (new IssueChild[size]);
        }

    }
    ;

    protected IssueChild(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.topics, (com.cryptenet.thanatos.dtmweb.pojo.Topic.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public IssueChild() {
    }

    /**
     * 
     * @param id
     * @param topics
     * @param name
     */
    public IssueChild(Integer id, String name, List<Topic> topics) {
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

    public int describeContents() {
        return  0;
    }

}
