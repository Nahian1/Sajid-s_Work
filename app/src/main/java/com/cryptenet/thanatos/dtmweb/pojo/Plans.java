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

public class Plans implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("plan")
    @Expose
    private Integer plan;
    @SerializedName("investor")
    @Expose
    private Integer investor;
    @SerializedName("plan_title")
    @Expose
    private String planTitle;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("bank_account_name")
    @Expose
    private String bankAccountName;
    @SerializedName("bank_account_number")
    @Expose
    private String bankAccountNumber;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("plan_access_price")
    @Expose
    private Double planAccessPrice;
    public final static Parcelable.Creator<Plans> CREATOR = new Creator<Plans>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Plans createFromParcel(Parcel in) {
            return new Plans(in);
        }

        public Plans[] newArray(int size) {
            return (new Plans[size]);
        }

    }
            ;

    protected Plans(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.plan = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.investor = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.planTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.bankName = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountName = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.note = ((String) in.readValue((String.class.getClassLoader())));
        this.isApproved = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.planAccessPrice = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Plans() {
    }

    /**
     *
     * @param id
     * @param transactionId
     * @param plan
     * @param createdAt
     * @param investor
     * @param bankAccountNumber
     * @param bankName
     * @param planAccessPrice
     * @param isApproved
     * @param planTitle
     * @param note
     * @param bankAccountName
     */
    public Plans(Integer id, Integer plan, Integer investor, String planTitle, String bankName, String bankAccountName, String bankAccountNumber, String transactionId, String note, Boolean isApproved, String createdAt, Double planAccessPrice) {
        super();
        this.id = id;
        this.plan = plan;
        this.investor = investor;
        this.planTitle = planTitle;
        this.bankName = bankName;
        this.bankAccountName = bankAccountName;
        this.bankAccountNumber = bankAccountNumber;
        this.transactionId = transactionId;
        this.note = note;
        this.isApproved = isApproved;
        this.createdAt = createdAt;
        this.planAccessPrice = planAccessPrice;
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

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Double getPlanAccessPrice() {
        return planAccessPrice;
    }

    public void setPlanAccessPrice(Double planAccessPrice) {
        this.planAccessPrice = planAccessPrice;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(plan);
        dest.writeValue(investor);
        dest.writeValue(planTitle);
        dest.writeValue(bankName);
        dest.writeValue(bankAccountName);
        dest.writeValue(bankAccountNumber);
        dest.writeValue(transactionId);
        dest.writeValue(note);
        dest.writeValue(isApproved);
        dest.writeValue(createdAt);
        dest.writeValue(planAccessPrice);
    }

    @Override
    public String toString() {
        return "Plans{" +
                "id=" + id +
                ", plan=" + plan +
                ", investor=" + investor +
                ", planTitle='" + planTitle + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", note='" + note + '\'' +
                ", isApproved=" + isApproved +
                ", createdAt='" + createdAt + '\'' +
                ", planAccessPrice=" + planAccessPrice +
                '}';
    }

    public int describeContents() {
        return  0;
    }

}