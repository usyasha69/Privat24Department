package com.example.pk.privat24department.models;


import android.os.Parcel;
import android.os.Parcelable;

public class DepartmentModel implements Parcelable{
    public String name;
    public String state;
    public String id;
    public String country;
    public String city;
    public String index;
    public String phone;
    public String address;

    public DepartmentModel(String name, String state, String id, String country, String city
            , String index, String phone, String address) {
        this.name = name;
        this.state = state;
        this.id = id;
        this.country = country;
        this.city = city;
        this.index = index;
        this.phone = phone;
        this.address = address;
    }

    protected DepartmentModel(Parcel in) {
        name = in.readString();
        state = in.readString();
        id = in.readString();
        country = in.readString();
        city = in.readString();
        index = in.readString();
        phone = in.readString();
        address = in.readString();
    }

    public static final Creator<DepartmentModel> CREATOR = new Creator<DepartmentModel>() {
        @Override
        public DepartmentModel createFromParcel(Parcel in) {
            return new DepartmentModel(in);
        }

        @Override
        public DepartmentModel[] newArray(int size) {
            return new DepartmentModel[size];
        }
    };

    @Override
    public String toString() {
        return "DepartmentModel{" +
                "name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", index='" + index + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(state);
        parcel.writeString(id);
        parcel.writeString(country);
        parcel.writeString(city);
        parcel.writeString(index);
        parcel.writeString(phone);
        parcel.writeString(address);
    }
}
