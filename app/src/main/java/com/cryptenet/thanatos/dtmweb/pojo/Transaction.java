/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction implements Parcelable {

    @SerializedName("plan")
    @Expose
    private Integer plan;
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

    ProjectsDetailed projectsDetailed;

    public final static Parcelable.Creator<Transaction> CREATOR = new Creator<Transaction>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        public Transaction[] newArray(int size) {
            return (new Transaction[size]);
        }

    };

    protected Transaction(Parcel in) {
        this.plan = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.bankName = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountName = ((String) in.readValue((String.class.getClassLoader())));
        this.bankAccountNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.transactionId = ((String) in.readValue((String.class.getClassLoader())));
        this.note = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Transaction() {
    }


    public ProjectsDetailed getProjectsDetailed() {
        return projectsDetailed;
    }

    public void setProjectsDetailed(ProjectsDetailed projectsDetailed) {
        this.projectsDetailed = projectsDetailed;
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(plan);
        dest.writeValue(bankName);
        dest.writeValue(bankAccountName);
        dest.writeValue(bankAccountNumber);
        dest.writeValue(transactionId);
        dest.writeValue(note);
    }

    public int describeContents() {
        return 0;
    }

}
