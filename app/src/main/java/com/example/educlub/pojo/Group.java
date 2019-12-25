package com.example.educlub.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Group implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private int type;
    @SerializedName("typeName")
    private String typaName;
    @SerializedName("address")
    private String address;
    @SerializedName("category")
    private String category;
    @SerializedName("subcategory")
    private String subcategory;
    @SerializedName("tags")
    private String tag;//split char ','
    @SerializedName("desc")
    private String desc;
    @SerializedName("startTime")
    private Date startTime;
    @SerializedName("endTime")
    private Date endTime;
    @SerializedName("week_days")
    private String weekDays;

    private Group(Parcel parcel){
        this.id=parcel.readInt();
        this.name=parcel.readString();
        this.type=parcel.readInt();
        this.typaName=parcel.readString();
        this.address=parcel.readString();
        this.category=parcel.readString();
        this.subcategory=parcel.readString();
        this.tag=parcel.readString();
        this.desc=parcel.readString();
        this.startTime=new Date(parcel.readLong());
        this.endTime=new Date(parcel.readLong());
        this.weekDays=parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeInt(this.type);
        parcel.writeString(this.typaName);
        parcel.writeString(this.address);
        parcel.writeString(this.category);
        parcel.writeString(this.subcategory);
        parcel.writeString(this.tag);
        parcel.writeString(this.desc);
        parcel.writeString(this.desc);
        parcel.writeLong(this.startTime.getTime());
        parcel.writeLong(this.endTime.getTime());
        parcel.writeString(this.weekDays);
    }

    private static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public static Creator<Group> getCREATOR() {
        return CREATOR;
    }

    //<editor-fold desc="Getters and Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypaName() {
        return typaName;
    }

    public void setTypaName(String typaName) {
        this.typaName = typaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(String weekDays) {
        this.weekDays = weekDays;
    }
    //</editor-fold>


    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", typaName='" + typaName + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", tag='" + tag + '\'' +
                ", desc='" + desc + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", weekDays='" + weekDays + '\'' +
                '}';
    }
}