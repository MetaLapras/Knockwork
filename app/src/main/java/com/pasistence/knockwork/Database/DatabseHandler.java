package com.pasistence.knockwork.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pasistence.knockwork.Model.ApiResponse.ProfileCount;
import com.pasistence.knockwork.Model.UserProfileModel;

import java.util.ArrayList;
import java.util.List;

public class DatabseHandler  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database.db";

    //User Table
    public static final String MEMBERS_TABLE = "member_table";
    public static final String MEMBER_COl_1 = "member_id";
    public static final String MEMBER_COL_2 = "server_id";
    public static final String MEMBER_COL_3 = "userid";
    public static final String MEMBER_COL_4 = "displayname";
    public static final String MEMBER_COL_5 = "professionaltitle";
    public static final String MEMBER_COL_6 = "minHourRate";
    public static final String MEMBER_COL_7 = "availability";
    public static final String MEMBER_COL_8 = "selfintro";
    public static final String MEMBER_COL_9 = "skills";
    public static final String MEMBER_COL_10 = "mobileno";
    public static final String MEMBER_COL_11 = "dob";
    public static final String MEMBER_COL_12 = "gender";
    public static final String CREATE_TABLE_MEMBER =
            "CREATE TABLE " + MEMBERS_TABLE + "("
                    + MEMBER_COl_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + MEMBER_COL_2 + " TEXT,"
                    + MEMBER_COL_3 + " TEXT,"
                    + MEMBER_COL_4 + " TEXT,"
                    + MEMBER_COL_5 + " TEXT,"
                    + MEMBER_COL_6 + " TEXT,"
                    + MEMBER_COL_7 + " TEXT,"
                    + MEMBER_COL_8 + " TEXT,"
                    + MEMBER_COL_9 + " TEXT,"
                    + MEMBER_COL_10 + " TEXT,"
                    + MEMBER_COL_11 + " TEXT,"
                    + MEMBER_COL_12 + " TEXT"
                    + ")";


    public static final String EDUCATIONAL = "educational_table";
    public static final String EDUCATIONAL_COL_1 = "Education_id";
    public static final String EDUCATIONAL_COL_2 = "degree";
    public static final String EDUCATIONAL_COL_3 = "passingyear";
    public static final String CREATE_TABLE_EDUCATIONAL =
            "CREATE TABLE " + EDUCATIONAL + "("
                    + EDUCATIONAL_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + MEMBER_COL_2 + " TEXT,"
                    + MEMBER_COL_3 + " TEXT,"
                    + EDUCATIONAL_COL_2 + " TEXT,"
                    + EDUCATIONAL_COL_3 + " TEXT"
                    + ")";

    public static final String WORKEXPERIENCE = "workexperience_table";
    public static final String WORK_COL_1 = "id";
    public static final String WORK_COL_2 = "companyname";
    public static final String WORK_COL_3 = "profile";
    public static final String WORK_COL_4 = "startdate";
    public static final String WORK_COL_5 = "enddate";
    public static final String CREATE_TABLE_WORKEXPERIENCE =
            "CREATE TABLE " + WORKEXPERIENCE + "("
                    + WORK_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + MEMBER_COl_1 + " TEXT,"
                    + MEMBER_COL_2 + " TEXT,"
                    + MEMBER_COL_3 + " TEXT,"
                    + WORK_COL_2 + " TEXT,"
                    + WORK_COL_3 + " TEXT,"
                    + WORK_COL_4 + " TEXT,"
                    + WORK_COL_5 + " TEXT"
                    + ")";

    public DatabseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create tables
        db.execSQL(CREATE_TABLE_MEMBER);
        //CREATE TABLE EVENTS OFFLINE
        db.execSQL(CREATE_TABLE_EDUCATIONAL);
        //CREATE TABLE CONTRIBUTION OFFLINE
        db.execSQL(CREATE_TABLE_WORKEXPERIENCE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MEMBERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EDUCATIONAL);
        db.execSQL("DROP TABLE IF EXISTS " + WORKEXPERIENCE);
    }

    //Members
    public boolean insertMemberData(UserProfileModel member) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MEMBER_COL_2, member.getServerId());
        values.put(MEMBER_COL_3, member.getUserid());
        values.put(MEMBER_COL_4, member.getDisplayname());
        values.put(MEMBER_COL_5, member.getProfessionaltitle());
        values.put(MEMBER_COL_6, member.getMinhourrate());
        values.put(MEMBER_COL_7, member.getAvailability());
        values.put(MEMBER_COL_8, member.getSelftintro());
        values.put(MEMBER_COL_9, member.getSkills());
        values.put(MEMBER_COL_10, member.getMobile());
        values.put(MEMBER_COL_11, member.getDob());
        values.put(MEMBER_COL_12, member.getGender());

        long result = db.insert(MEMBERS_TABLE, null, values);

        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public List<UserProfileModel> getAllMembers() {
        List<UserProfileModel> members = new ArrayList<UserProfileModel>();

        String selectQuery = "SELECT  * FROM " + MEMBERS_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all row and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserProfileModel member = new UserProfileModel();
                member.setServerId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_2)));
                member.setUserid(cursor.getString(cursor.getColumnIndex(MEMBER_COL_3)));
                member.setDisplayname(cursor.getString(cursor.getColumnIndex(MEMBER_COL_4)));
                member.setProfessionaltitle(cursor.getString(cursor.getColumnIndex(MEMBER_COL_5)));
                member.setMinhourrate(cursor.getString(cursor.getColumnIndex(MEMBER_COL_6)));
                member.setAvailability(cursor.getString(cursor.getColumnIndex(MEMBER_COL_7)));
                member.setSelftintro(cursor.getString(cursor.getColumnIndex(MEMBER_COL_8)));
                member.setSkills(cursor.getString(cursor.getColumnIndex(MEMBER_COL_9)));
                member.setMobile(cursor.getString(cursor.getColumnIndex(MEMBER_COL_10)));
                member.setDob(cursor.getString(cursor.getColumnIndex(MEMBER_COL_11)));
                member.setGender(cursor.getString(cursor.getColumnIndex(MEMBER_COL_12)));
                members.add(member);
            } while (cursor.moveToNext());
        }
        db.close();
        return members;
    }
    public void deleteAllMembers() {
        try {
            String selectQuery = "DELETE FROM " + MEMBERS_TABLE;
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(MEMBERS_TABLE, null, null);
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Member Delete-->", ex.toString());
        }
    }
    public void updateMemberData(UserProfileModel member) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(MEMBER_COL_3, member.getUserid());
        values.put(MEMBER_COL_4, member.getDisplayname());
        values.put(MEMBER_COL_5, member.getProfessionaltitle());
        values.put(MEMBER_COL_6, member.getMinhourrate());
        values.put(MEMBER_COL_7, member.getAvailability());
        values.put(MEMBER_COL_8, member.getSelftintro());
        values.put(MEMBER_COL_9, member.getSkills());
        values.put(MEMBER_COL_10, member.getMobile());
        values.put(MEMBER_COL_11, member.getDob());
        values.put(MEMBER_COL_12, member.getGender());

        db.update(MEMBERS_TABLE, values, MEMBER_COL_2 + " = ? ",
                new String[]{member.getServerId()});
    }

    //Education
    public boolean insertEducationDetails(UserProfileModel member) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MEMBER_COL_2, member.getServerId());
        values.put(MEMBER_COL_3, member.getUserid());
        values.put(EDUCATIONAL_COL_2, member.getDegree());
        values.put(EDUCATIONAL_COL_3, member.getPassingyear());

        long result = db.insert(EDUCATIONAL, null, values);

        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public List<UserProfileModel> getEductaion() {
        List<UserProfileModel> members = new ArrayList<UserProfileModel>();

        String selectQuery = "SELECT  * FROM " + MEMBERS_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all row and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserProfileModel member = new UserProfileModel();
                member.setServerId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_2)));
                member.setUserid(cursor.getString(cursor.getColumnIndex(MEMBER_COL_3)));
                member.setEducationId(cursor.getString(cursor.getColumnIndex(EDUCATIONAL_COL_1)));
                member.setDegree(cursor.getString(cursor.getColumnIndex(EDUCATIONAL_COL_2)));
                member.setPassingyear(cursor.getString(cursor.getColumnIndex(EDUCATIONAL_COL_3)));
                members.add(member);
            } while (cursor.moveToNext());
        }
        db.close();
        return members;
    }
    public void deleteEducation() {
        try {
            String selectQuery = "DELETE FROM " + EDUCATIONAL;
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(EDUCATIONAL, null, null);
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Member Delete-->", ex.toString());
        }
    }
    public void updateEducation(UserProfileModel member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EDUCATIONAL_COL_2, member.getDegree());
        values.put(EDUCATIONAL_COL_3, member.getPassingyear());
        db.update(MEMBERS_TABLE, values, EDUCATIONAL_COL_1 + " = ? ",
                new String[]{member.getEducationId()});
    }

    //Work Experience
    public boolean insertWorkExp(UserProfileModel member) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MEMBER_COL_2, member.getServerId());
        values.put(MEMBER_COL_3, member.getUserid());
        values.put(WORK_COL_2, member.getCompanyname());
        values.put(WORK_COL_3, member.getProfile());
        values.put(WORK_COL_4, member.getStartdate());
        values.put(WORK_COL_5, member.getEnddate());


        long result = db.insert(WORKEXPERIENCE, null, values);

        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public List<UserProfileModel> getWorkexp() {
        List<UserProfileModel> members = new ArrayList<UserProfileModel>();

        String selectQuery = "SELECT  * FROM " + WORKEXPERIENCE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all row and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserProfileModel member = new UserProfileModel();
                member.setServerId(cursor.getString(cursor.getColumnIndex(MEMBER_COL_2)));
                member.setUserid(cursor.getString(cursor.getColumnIndex(MEMBER_COL_3)));
                member.setCompanyname(cursor.getString(cursor.getColumnIndex(WORK_COL_2)));
                member.setProfile(cursor.getString(cursor.getColumnIndex(WORK_COL_3)));
                member.setStartdate(cursor.getString(cursor.getColumnIndex(WORK_COL_4)));
                member.setEnddate(cursor.getString(cursor.getColumnIndex(WORK_COL_5)));
                member.setWorkId(cursor.getString(cursor.getColumnIndex(WORK_COL_1)));

                members.add(member);
            } while (cursor.moveToNext());
        }
        db.close();
        return members;
    }
    public void deleteWork() {
        try {
            String selectQuery = "DELETE FROM " + WORKEXPERIENCE;
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(WORKEXPERIENCE, null, null);
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Member Delete-->", ex.toString());
        }
    }
    public void updatework(UserProfileModel member) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(WORK_COL_2, member.getCompanyname());
        values.put(WORK_COL_3, member.getProfile());
        values.put(WORK_COL_4, member.getStartdate());
        values.put(WORK_COL_5, member.getEnddate());

        db.update(WORKEXPERIENCE, values, WORK_COL_1 + " = ? ",
                new String[]{member.getWorkId()});
    }

    //GetProfileCount
    public Float getProfileCount() {
        Float totalStatus = null ;
        //List<ProfileCount> members = new ArrayList<ProfileCount>();
        ProfileCount count = new ProfileCount();
        String selectQuery = "select count(professionaltitle)as pt,count(minHourRate) as mhr,count(availability) as ava,count(selfintro) as si,count(skills) as skl,count(mobileno)as mbn,count(dob) as dob,count(gender) as sex from member_table";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all row and adding to list
        if (cursor.moveToFirst()) {
            do {

                if(cursor.getInt(cursor.getColumnIndex("pt"))>0){
                    count.setProfessionaltitle((float) 1.25);
                }else {
                    count.setProfessionaltitle((float) 0.0);
                }
                if(cursor.getInt(cursor.getColumnIndex("mhr"))>0){
                    count.setMinhourrate((float) 1.25);
                }
                else {
                    count.setMinhourrate((float) 0.0);
                }
                if(cursor.getInt(cursor.getColumnIndex("ava"))>0){
                    count.setAvailability((float) 1.25);
                }else {
                    count.setAvailability((float) 0.0);
                }
                if(cursor.getInt(cursor.getColumnIndex("si"))>0){
                    count.setSelfintro((float) 1.25);
                }else {
                    count.setSelfintro((float) 0.0);
                }
                if(cursor.getInt(cursor.getColumnIndex("skl"))>0){
                    count.setSkills((float) 1.25);
                }else {
                    count.setSkills((float) 0.0);
                }
                if(cursor.getInt(cursor.getColumnIndex("mbn"))>0){
                    count.setMobile((float) 1.25);
                }else {
                    count.setMobile((float) 0.0);
                }
                if(cursor.getInt(cursor.getColumnIndex("dob"))>0){
                    count.setDob((float) 1.25);
                }else {
                    count.setDob((float) 0.0);
                }
                if(cursor.getInt(cursor.getColumnIndex("sex"))>0){
                    count.setGender((float) 1.25);
                }else {
                    count.setGender((float) 0.0);
                }

            } while (cursor.moveToNext());
        }
        db.close();

        totalStatus = count.getProfessionaltitle()+
                count.getMinhourrate()+
                count.getAvailability()+
                count.getSelfintro()+
                count.getSkills()+
                count.getMobile()+
                count.getDob()+
                count.getGender();


        return totalStatus;
    }
}
