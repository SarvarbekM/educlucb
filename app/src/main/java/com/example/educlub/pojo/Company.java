package com.example.educlub.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Company implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("logo_link")
    private String logoLink;
    @SerializedName("desc")
    private String desc;
    @SerializedName("phone")
    private String phone;
    @SerializedName("link")
    private String link;
    @SerializedName("address")
    private String address;
    @SerializedName("cordinte_x")
    private double latitude;
    @SerializedName("cordinate_y")
    private double longitude;

    private Company(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.logoLink = parcel.readString();
        this.desc = parcel.readString();
        this.phone = parcel.readString();
        this.link = parcel.readString();
        this.address = parcel.readString();
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.logoLink);
        parcel.writeString(this.desc);
        parcel.writeString(this.phone);
        parcel.writeString(this.link);
        parcel.writeString(this.address);
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
    }

    private static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    public static Creator<Company> getCREATOR() {
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

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    //</editor-fold>


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logoLink='" + logoLink + '\'' +
                ", desc='" + desc + '\'' +
                ", phone='" + phone + '\'' +
                ", link='" + link + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
