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

public class AllCategoriesResponse implements Parcelable {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<Categories> results = null;
    public final static Parcelable.Creator<AllCategoriesResponse> CREATOR = new Creator<AllCategoriesResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AllCategoriesResponse createFromParcel(Parcel in) {
            return new AllCategoriesResponse(in);
        }

        public AllCategoriesResponse[] newArray(int size) {
            return (new AllCategoriesResponse[size]);
        }

    }
            ;

    protected AllCategoriesResponse(Parcel in) {
        this.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.next = ((String) in.readValue((String.class.getClassLoader())));
        this.previous = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.results, (com.cryptenet.thanatos.dtmweb.pojo.Categories.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public AllCategoriesResponse() {
    }

    /**
     *
     * @param results
     * @param previous
     * @param count
     * @param next
     */
    public AllCategoriesResponse(Integer count, String next, Object previous, List<Categories> results) {
        super();
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<Categories> getResults() {
        return results;
    }

    public void setResults(List<Categories> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(count);
        dest.writeValue(next);
        dest.writeValue(previous);
        dest.writeList(results);
    }

    @Override
    public String toString() {
        return "AllCategoriesResponse{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous=" + previous +
                ", results=" + results +
                '}';
    }

    public int describeContents() {
        return  0;
    }

}