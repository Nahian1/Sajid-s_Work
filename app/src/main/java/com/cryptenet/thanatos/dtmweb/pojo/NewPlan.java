package com.cryptenet.thanatos.dtmweb.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class NewPlan {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category")
    @Expose
    private int category;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("long_description")
    @Expose
    private String longDescription;
    @SerializedName("minimum_investment_cost")
    @Expose
    private int minimumInvestmentCost;
    @SerializedName("maximum_investment_cost")
    @Expose
    private int maximumInvestmentCost;
    @SerializedName("access_price")
    @Expose
    private int accessPrice;
    @SerializedName("cover")
    @Expose
    private File cover;
    @SerializedName("uploaded_file")
    @Expose
    private File uploadedFile;

    public NewPlan() {
    }

    /**
     *
     * @param cover
     * @param category
     * @param title
     * @param maximumInvestmentCost
     * @param shortDescription
     * @param longDescription
     * @param uploadedFile
     * @param accessPrice
     * @param minimumInvestmentCost
     */
    public NewPlan(String title, int category, String shortDescription, String longDescription, int minimumInvestmentCost, int maximumInvestmentCost, int accessPrice, File cover, File uploadedFile) {
        super();
        this.title = title;
        this.category = category;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.minimumInvestmentCost = minimumInvestmentCost;
        this.maximumInvestmentCost = maximumInvestmentCost;
        this.accessPrice = accessPrice;
        this.cover = cover;
        this.uploadedFile = uploadedFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getMinimumInvestmentCost() {
        return minimumInvestmentCost;
    }

    public void setMinimumInvestmentCost(int minimumInvestmentCost) {
        this.minimumInvestmentCost = minimumInvestmentCost;
    }

    public int getMaximumInvestmentCost() {
        return maximumInvestmentCost;
    }

    public void setMaximumInvestmentCost(int maximumInvestmentCost) {
        this.maximumInvestmentCost = maximumInvestmentCost;
    }

    public int getAccessPrice() {
        return accessPrice;
    }

    public void setAccessPrice(int accessPrice) {
        this.accessPrice = accessPrice;
    }

    public File getCover() {
        return cover;
    }

    public void setCover(File cover) {
        this.cover = cover;
    }

    public File getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(File uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(category);
        dest.writeValue(shortDescription);
        dest.writeValue(longDescription);
        dest.writeValue(minimumInvestmentCost);
        dest.writeValue(maximumInvestmentCost);
        dest.writeValue(accessPrice);
        dest.writeValue(cover);
        dest.writeValue(uploadedFile);
    }

    public int describeContents() {
        return  0;
    }

}