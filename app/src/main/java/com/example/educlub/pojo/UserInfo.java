package com.example.educlub.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserInfo implements Parcelable {

    @SerializedName("userId")
    private int userId;
    @SerializedName("userType")
    private int userType;
    @SerializedName("userTypeName")
    private String userTypeName;
    @SerializedName("userFIO")
    private String userFIO;
    @SerializedName("userPhone")
    private String userPhone;
    @SerializedName("access_token")
    private String token;
    @SerializedName("expiret_at")
    private long expiret_at;
    @SerializedName("language_id")
    private String lanId;
    @SerializedName("firebase_token")
    private String firebaseToken;


    private UserInfo(Parcel parcel) {
        this.userId = parcel.readInt();
        this.userType = parcel.readInt();
        this.userTypeName = parcel.readString();
        this.userFIO = parcel.readString();
        this.userPhone = parcel.readString();
        this.token = parcel.readString();
        this.expiret_at = parcel.readLong();
        this.lanId = parcel.readString();
        this.firebaseToken=parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.userId);
        parcel.writeInt(this.userType);
        parcel.writeString(this.userTypeName);
        parcel.writeString(this.userFIO);
        parcel.writeString(this.userPhone);
        parcel.writeString(this.token);
        parcel.writeLong(this.expiret_at);
        parcel.writeString(this.lanId);
        parcel.writeString(this.firebaseToken);
    }

    private static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public static Creator<UserInfo> getCREATOR() {
        return CREATOR;
    }

    //<editor-fold desc="Getters and Setter">

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getUserFIO() {
        return userFIO;
    }

    public void setUserFIO(String userFIO) {
        this.userFIO = userFIO;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiret_at() {
        return expiret_at;
    }

    public void setExpiret_at(long expiret_at) {
        this.expiret_at = expiret_at;
    }

    public String getLanId() {
        return lanId;
    }

    public void setLanId(String lanId) {
        this.lanId = lanId;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    //</editor-fold>


    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userType=" + userType +
                ", userTypeName='" + userTypeName + '\'' +
                ", userFIO='" + userFIO + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", token='" + token + '\'' +
                ", expiret_at=" + expiret_at +
                ", lanId='" + lanId + '\'' +
                ", firebaseToken='" + firebaseToken + '\'' +
                '}';
    }
}
