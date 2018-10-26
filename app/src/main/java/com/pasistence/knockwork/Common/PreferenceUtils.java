package com.pasistence.knockwork.Common;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    public static final String PREFERENCE_KEY = "KNOCKWORK";
    public static final String displayName ="displayname";
    public static final String Uid = "uid";
    public static final String email = "email";
    public static final String phoneNumber = "phoneno";
    public static final String provider = "provider";
    public static final String isLogin = "IsLogin";
    public static final String ProfileStatus = "ProfileStatus";
    public static final String userType = "islancer";
    public static final String isClient = "isclient";
    public static final String PREFERENCE_KEY_SIGN_IN ="signIn";
    public static final String photourl = "photourl";
    public static final String userId = "UserId";


    //prevent instantiation
    private PreferenceUtils(){}

    public static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(PREFERENCE_KEY,Context.MODE_PRIVATE);
    }

    public static void setSignIn(Context context,boolean signIn){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(PREFERENCE_KEY_SIGN_IN,signIn).apply();
    }

    public static boolean getSignIn(Context context){
        return getSharedPreferences(context).getBoolean(PREFERENCE_KEY_SIGN_IN,false);
    }

    public static int getProfileStatus(Context context) {
        return getSharedPreferences(context).getInt(ProfileStatus,0);
    }

    public static void setProfileStatus(Context context, int Details){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(ProfileStatus,Details).apply();
    }


    public static String getDisplayName(Context context) {
        return getSharedPreferences(context).getString(displayName,"");
    }

    public static void setDisplayName(Context context, String Details){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(displayName,Details).apply();
    }

    public static String getUid(Context context) {
        return getSharedPreferences(context).getString(Uid,"");
    }

    public static void setUid(Context context, String Details){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(Uid,Details).apply();
    }

    public static String getEmail(Context context) {
        return getSharedPreferences(context).getString(email,"");
    }

    public static void setEmail(Context context, String Details){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(email,Details).apply();
    }

    public static String getPhoneNumber(Context context) {
        return getSharedPreferences(context).getString(phoneNumber,"");
    }

    public static void setPhoneNumber(Context context, String Details){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(phoneNumber,Details).apply();
    }

    public static String getProvider(Context context) {
        return getSharedPreferences(context).getString(provider,"");
    }

    public static void setProvider(Context context, String Details){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(provider,Details).apply();
    }

    public static void setUserType(Context context, String Details){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(userType,Details).apply();
    }
    public static String getUserType(Context context) {
        return getSharedPreferences(context).getString(userType,"");
    }

    public static void setPhotoUrl(Context context, String Details){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(photourl,Details).apply();
    }
    public static String getPhotoUrl(Context context) {
        return getSharedPreferences(context).getString(photourl,"");
    }





}
