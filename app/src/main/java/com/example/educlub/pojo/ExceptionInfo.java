package com.example.educlub.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ExceptionInfo implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private int code;
    @SerializedName("status")
    private int status;
    @SerializedName("type")
    private String type;


    private ExceptionInfo(Parcel in) {
        this.name = in.readString();
        this.message = in.readString();
        this.code = in.readInt();
        this.status = in.readInt();
        this.type = in.readString();
    }

    public static final Creator<ExceptionInfo> CREATOR = new Creator<ExceptionInfo>() {
        @Override
        public ExceptionInfo createFromParcel(Parcel in) {
            return new ExceptionInfo(in);
        }

        @Override
        public ExceptionInfo[] newArray(int size) {
            return new ExceptionInfo[size];
        }
    };

    public static Creator<ExceptionInfo> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(message);
        parcel.writeInt(code);
        parcel.writeInt(status);
        parcel.writeString(type);
    }

    //<editor-fold desc="Getters and Setters">

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //</editor-fold>

    @Override
    public String toString() {
        return "ExceptionInfo{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                ", status=" + status +
                ", type='" + type + '\'' +
                '}';
    }
}