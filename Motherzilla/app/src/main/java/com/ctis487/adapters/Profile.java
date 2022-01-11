package com.ctis487.adapters;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {

    private int id;
    private String name;
    private int age;
    private String speciality;
    private String description;
    private byte[] image;

    public Profile(int id, String name, int age, String speciality, String description, byte[] image) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.speciality = speciality;
        this.description = description;
        this.image = image;
    }


    protected Profile(Parcel in) {
        id = in.readInt();
        name = in.readString();
        age = in.readInt();
        speciality = in.readString();
        description = in.readString();
        image = in.createByteArray();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(speciality);
        parcel.writeString(description);
        parcel.writeByteArray(image);
    }
}
