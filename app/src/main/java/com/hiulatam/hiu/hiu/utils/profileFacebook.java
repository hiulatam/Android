package com.hiulatam.hiu.hiu.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import com.facebook.Profile;

/**
 * Created by ltaleron on 10/3/17.
 */

public class profileFacebook implements Parcelable {

    public Profile profile;

    public profileFacebook(Profile profile) {
        this.profile = profile;
    }

    public profileFacebook() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.profile, flags);
    }

    protected profileFacebook(Parcel in) {
        this.profile = in.readParcelable(Profile.class.getClassLoader());
    }

    public static final Creator<profileFacebook> CREATOR = new Creator<profileFacebook>() {
        @Override
        public profileFacebook createFromParcel(Parcel source) {
            return new profileFacebook(source);
        }

        @Override
        public profileFacebook[] newArray(int size) {
            return new profileFacebook[size];
        }
    };
}
