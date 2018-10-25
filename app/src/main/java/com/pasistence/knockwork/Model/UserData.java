package com.pasistence.knockwork.Model;

import android.net.Uri;

import java.io.Serializable;

public class UserData implements Serializable {
    public String DisplayName,Uid,Email,PhoneNumber;
    public Uri PhotoUrl;

    public UserData() {
    }

    public UserData(String displayName, String uid, String email, Uri photoUrl) {
        DisplayName = displayName;
        Uid = uid;
        Email = email;
        //PhoneNumber = phoneNumber;
        PhotoUrl = photoUrl;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Uri getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        PhotoUrl = photoUrl;
    }
}
