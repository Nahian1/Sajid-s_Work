
package com.cryptenet.thanatos.dtmweb.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IssueResponse implements Parcelable
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
    @SerializedName("issueChildren")
    @Expose
    private List<IssueChild> issueChildren = null;
    public final static Creator<IssueResponse> CREATOR = new Creator<IssueResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public IssueResponse createFromParcel(Parcel in) {
            return new IssueResponse(in);
        }

        public IssueResponse[] newArray(int size) {
            return (new IssueResponse[size]);
        }

    }
    ;

    protected IssueResponse(Parcel in) {
        this.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.next = ((Object) in.readValue((Object.class.getClassLoader())));
        this.previous = ((Object) in.readValue((Object.class.getClassLoader())));
        in.readList(this.issueChildren, (IssueChild.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public IssueResponse() {
    }

    /**
     * 
     * @param issueChildren
     * @param previous
     * @param count
     * @param next
     */
    public IssueResponse(Integer count, Object next, Object previous, List<IssueChild> issueChildren) {
        super();
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.issueChildren = issueChildren;
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

    public List<IssueChild> getIssueChildren() {
        return issueChildren;
    }

    public void setIssueChildren(List<IssueChild> issueChildren) {
        this.issueChildren = issueChildren;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(count);
        dest.writeValue(next);
        dest.writeValue(previous);
        dest.writeList(issueChildren);
    }

    public int describeContents() {
        return  0;
    }

}
