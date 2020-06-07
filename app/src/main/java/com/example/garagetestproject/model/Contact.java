package com.example.garagetestproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact implements Parcelable {
    public Contact() {
    }

    public Contact(String firstName, String lastName, String email) {
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
    }

    @PrimaryKey(autoGenerate = true)
    public int mUid;

    @ColumnInfo(name = "first_name")
    private String mFirstName;

    @ColumnInfo(name = "last_name")
    private String mLastName;

    @ColumnInfo(name = "email")
    private String mEmail;

    public Contact(Parcel in) {
        mUid = in.readInt();
        mFirstName = in.readString();
        mLastName = in.readString();
        mEmail = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public int getUid() {
        return mUid;
    }

    public void setUid(int uid) {
        mUid = uid;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mUid);
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeString(mEmail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return mUid == contact.mUid &&
                Objects.equals(mFirstName, contact.mFirstName) &&
                Objects.equals(mLastName, contact.mLastName) &&
                Objects.equals(mEmail, contact.mEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mUid, mFirstName, mLastName, mEmail);
    }
}
