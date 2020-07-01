package com.app.motion.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeModel extends BaseModel implements Parcelable {

    private String one;
    private String two;

    public HomeModel() {
    }

    public HomeModel(String one, String two) {
        this.one = one;
        this.two = two;
    }

    protected HomeModel(Parcel in) {
        one = in.readString();
        two = in.readString();
    }

    public static final Creator<HomeModel> CREATOR = new Creator<HomeModel>() {
        @Override
        public HomeModel createFromParcel(Parcel in) {
            return new HomeModel(in);
        }

        @Override
        public HomeModel[] newArray(int size) {
            return new HomeModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(one);
        dest.writeString(two);
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }
}
