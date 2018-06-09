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

public class CityResponse implements Parcelable
{

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("next")
    @Expose
    private Object next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<City> results = null;
    public final static Parcelable.Creator<CityResponse> CREATOR = new Creator<CityResponse>() {
        @SuppressWarnings({
                "unchecked"
        })
        public CityResponse createFromParcel(Parcel in) {
            return new CityResponse(in);
        }

        public CityResponse[] newArray(int size) {
            return (new CityResponse[size]);
        }
    };

    protected CityResponse(Parcel in) {
        this.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.next = ((Object) in.readValue((Object.class.getClassLoader())));
        this.previous = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.results, (City.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public CityResponse() {
    }

    /**
     *
     * @param results
     * @param previous
     * @param count
     * @param next
     */
    public CityResponse(Integer count, Object next, Object previous, List<City> results) {
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

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<City> getResults() {
        return results;
    }

    public void setResults(List<City> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "CityResponse{" +
                "count=" + count +
                ", next=" + next +
                ", previous=" + previous +
                ", results=" + results +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(count);
        dest.writeValue(next);
        dest.writeValue(previous);
        dest.writeList(results);
    }

    public int describeContents() {
        return  0;
    }
}
