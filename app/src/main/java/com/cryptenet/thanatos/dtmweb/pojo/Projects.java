package com.cryptenet.thanatos.dtmweb.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Projects implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("initiator")
    @Expose
    private Integer initiator;
    @SerializedName("initiators_name")
    @Expose
    private String initiatorsName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("minimum_investment_cost")
    @Expose
    private String minimumInvestmentCost;
    @SerializedName("maximum_investment_cost")
    @Expose
    private String maximumInvestmentCost;
    @SerializedName("access_price")
    @Expose
    private String accessPrice;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("cover_thumbnail")
    @Expose
    private String coverThumbnail;
    @SerializedName("uploaded_file")
    @Expose
    private Object uploadedFile;
    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    public final static Parcelable.Creator<Projects> CREATOR = new Creator<Projects>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Projects createFromParcel(Parcel in) {
            return new Projects(in);
        }

        public Projects[] newArray(int size) {
            return (new Projects[size]);
        }

    }
            ;

    protected Projects(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.initiator = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.initiatorsName = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.category = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.shortDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.minimumInvestmentCost = ((String) in.readValue((String.class.getClassLoader())));
        this.maximumInvestmentCost = ((String) in.readValue((String.class.getClassLoader())));
        this.accessPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.cover = ((String) in.readValue((String.class.getClassLoader())));
        this.coverThumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.uploadedFile = ((Object) in.readValue((Object.class.getClassLoader())));
        this.isApproved = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Projects() {
    }

    /**
     *
     * @param uploadedFile
     * @param accessPrice
     * @param id
     * @param title
     * @param category
     * @param cover
     * @param maximumInvestmentCost
     * @param coverThumbnail
     * @param initiator
     * @param shortDescription
     * @param createdAt
     * @param isApproved
     * @param initiatorsName
     * @param minimumInvestmentCost
     */
    public Projects(Integer id, Integer initiator, String initiatorsName, String title, Integer category, String shortDescription, String minimumInvestmentCost, String maximumInvestmentCost, String accessPrice, String cover, String coverThumbnail, Object uploadedFile, Boolean isApproved, String createdAt) {
        super();
        this.id = id;
        this.initiator = initiator;
        this.initiatorsName = initiatorsName;
        this.title = title;
        this.category = category;
        this.shortDescription = shortDescription;
        this.minimumInvestmentCost = minimumInvestmentCost;
        this.maximumInvestmentCost = maximumInvestmentCost;
        this.accessPrice = accessPrice;
        this.cover = cover;
        this.coverThumbnail = coverThumbnail;
        this.uploadedFile = uploadedFile;
        this.isApproved = isApproved;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInitiator() {
        return initiator;
    }

    public void setInitiator(Integer initiator) {
        this.initiator = initiator;
    }

    public String getInitiatorsName() {
        return initiatorsName;
    }

    public void setInitiatorsName(String initiatorsName) {
        this.initiatorsName = initiatorsName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getMinimumInvestmentCost() {
        return minimumInvestmentCost;
    }

    public void setMinimumInvestmentCost(String minimumInvestmentCost) {
        this.minimumInvestmentCost = minimumInvestmentCost;
    }

    public String getMaximumInvestmentCost() {
        return maximumInvestmentCost;
    }

    public void setMaximumInvestmentCost(String maximumInvestmentCost) {
        this.maximumInvestmentCost = maximumInvestmentCost;
    }

    public String getAccessPrice() {
        return accessPrice;
    }

    public void setAccessPrice(String accessPrice) {
        this.accessPrice = accessPrice;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoverThumbnail() {
        return coverThumbnail;
    }

    public void setCoverThumbnail(String coverThumbnail) {
        this.coverThumbnail = coverThumbnail;
    }

    public Object getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Object uploadedFile) {
        this.uploadedFile = uploadedFile;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(initiator);
        dest.writeValue(initiatorsName);
        dest.writeValue(title);
        dest.writeValue(category);
        dest.writeValue(shortDescription);
        dest.writeValue(minimumInvestmentCost);
        dest.writeValue(maximumInvestmentCost);
        dest.writeValue(accessPrice);
        dest.writeValue(cover);
        dest.writeValue(coverThumbnail);
        dest.writeValue(uploadedFile);
        dest.writeValue(isApproved);
        dest.writeValue(createdAt);
    }

    public int describeContents() {
        return  0;
    }
}
