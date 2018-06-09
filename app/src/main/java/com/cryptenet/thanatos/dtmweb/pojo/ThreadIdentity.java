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

public class ThreadIdentity implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("plan")
    @Expose
    private Integer plan;
    @SerializedName("investor")
    @Expose
    private Integer investor;
    @SerializedName("initiator")
    @Expose
    private Integer initiator;
    @SerializedName("activated")
    @Expose
    private Boolean activated;
    @SerializedName("last_active")
    @Expose
    private String lastActive;
    @SerializedName("last_text")
    @Expose
    private String lastText;
    @SerializedName("plan_title")
    @Expose
    private String planTitle;
    @SerializedName("plan_cover")
    @Expose
    private String planCover;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("investor_name")
    @Expose
    private String investorName;
    @SerializedName("investor_picture")
    @Expose
    private String investorPicture;
    @SerializedName("initiator_name")
    @Expose
    private String initiatorName;
    @SerializedName("initiator_picture")
    @Expose
    private String initiatorPicture;
    public final static Creator<ThreadIdentity> CREATOR = new Creator<ThreadIdentity>() {
        @SuppressWarnings({
            "unchecked"
        })
        public ThreadIdentity createFromParcel(Parcel in) {
            return new ThreadIdentity(in);
        }

        public ThreadIdentity[] newArray(int size) {
            return (new ThreadIdentity[size]);
        }
    };

    protected ThreadIdentity(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.plan = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.investor = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.initiator = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.activated = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.lastActive = ((String) in.readValue((String.class.getClassLoader())));
        this.lastText = ((String) in.readValue((String.class.getClassLoader())));
        this.planTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.planCover = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.investorName = ((String) in.readValue((String.class.getClassLoader())));
        this.investorPicture = ((String) in.readValue((String.class.getClassLoader())));
        this.initiatorName = ((String) in.readValue((String.class.getClassLoader())));
        this.initiatorPicture = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ThreadIdentity() {
    }

    /**
     * 
     * @param initiatorPicture
     * @param lastActive
     * @param investor
     * @param planTitle
     * @param id
     * @param lastText
     * @param plan
     * @param initiator
     * @param investorName
     * @param createdAt
     * @param initiatorName
     * @param activated
     * @param planCover
     * @param investorPicture
     */
    public ThreadIdentity(Integer id, Integer plan, Integer investor, Integer initiator, Boolean activated, String lastActive, String lastText, String planTitle, String planCover, String createdAt, String investorName, String investorPicture, String initiatorName, String initiatorPicture) {
        super();
        this.id = id;
        this.plan = plan;
        this.investor = investor;
        this.initiator = initiator;
        this.activated = activated;
        this.lastActive = lastActive;
        this.lastText = lastText;
        this.planTitle = planTitle;
        this.planCover = planCover;
        this.createdAt = createdAt;
        this.investorName = investorName;
        this.investorPicture = investorPicture;
        this.initiatorName = initiatorName;
        this.initiatorPicture = initiatorPicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    public Integer getInvestor() {
        return investor;
    }

    public void setInvestor(Integer investor) {
        this.investor = investor;
    }

    public Integer getInitiator() {
        return initiator;
    }

    public void setInitiator(Integer initiator) {
        this.initiator = initiator;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getLastActive() {
        return lastActive;
    }

    public void setLastActive(String lastActive) {
        this.lastActive = lastActive;
    }

    public String getLastText() {
        return lastText;
    }

    public void setLastText(String lastText) {
        this.lastText = lastText;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String getPlanCover() {
        return planCover;
    }

    public void setPlanCover(String planCover) {
        this.planCover = planCover;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getInvestorPicture() {
        return investorPicture;
    }

    public void setInvestorPicture(String investorPicture) {
        this.investorPicture = investorPicture;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getInitiatorPicture() {
        return initiatorPicture;
    }

    public void setInitiatorPicture(String initiatorPicture) {
        this.initiatorPicture = initiatorPicture;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(plan);
        dest.writeValue(investor);
        dest.writeValue(initiator);
        dest.writeValue(activated);
        dest.writeValue(lastActive);
        dest.writeValue(lastText);
        dest.writeValue(planTitle);
        dest.writeValue(planCover);
        dest.writeValue(createdAt);
        dest.writeValue(investorName);
        dest.writeValue(investorPicture);
        dest.writeValue(initiatorName);
        dest.writeValue(initiatorPicture);
    }

    @Override
    public String toString() {
        return "ThreadIdentity{" +
                "id=" + id +
                ", plan=" + plan +
                ", investor=" + investor +
                ", initiator=" + initiator +
                ", activated=" + activated +
                ", lastActive='" + lastActive + '\'' +
                ", lastText='" + lastText + '\'' +
                ", planTitle='" + planTitle + '\'' +
                ", planCover='" + planCover + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", investorName='" + investorName + '\'' +
                ", investorPicture='" + investorPicture + '\'' +
                ", initiatorName='" + initiatorName + '\'' +
                ", initiatorPicture='" + initiatorPicture + '\'' +
                '}';
    }

    public int describeContents() {
        return  0;
    }
}
