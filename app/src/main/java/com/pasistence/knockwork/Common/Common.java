package com.pasistence.knockwork.Common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pasistence.knockwork.Model.UserData;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.Remote.RetrofitClient;

import dmax.dialog.SpotsDialog;


public class Common {
   // private static final String BASE_URL = "http://192.168.0.151/mantra/public/index.php/api/";
    //private static final String BASE_URL = "http://ip.jsontest.com/";

    public UserData userData;
    public static AlertDialog alertDialog;

    public static final String Lancer = "Lancer";
    public static final String Client = "Client";
    public static final String phone = "phone";
    public static final String gmail = "gmail";
    public static final String facebook = "facebook";
    public static final String update = "update";
    public static final String register = "register";
    public static String UserName;
    public static String UserEmail ;
    public static String UserPhoto;

    public static AlertDialog watingDialog;

    //localhost
    //public static final String BASE_URL = "http://10.0.2.2/knockwork/public/index.php/api/";
    //Server
    public static final String BASE_URL = "http://52.172.221.235:8985/knockwork/public/index.php/api/";
    private static final String TAG = "common";
    //public static final String BASE_URL = "http://192.168.0.150/knockwork/public/index.php/api/";

    public static MyApi getApi(){
        return RetrofitClient.getClient(BASE_URL).create(MyApi.class);
    }

    public static FirebaseDatabase database;
    public static DatabaseReference databaseReference ;


    public static boolean isConnectedToInterNet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null)
        {
            NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
            if(infos != null)
            {
                for(int i = 0;i<infos.length;i++)
                {
                    if(infos[i].getState()==NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

    public static void InitFirebase(String datareference){
        //init Fire base
        database = FirebaseDatabase.getInstance();

        switch (datareference){
            case "popular":
                databaseReference = database.getReference("popular");
                break;
            case "top":
                databaseReference = database.getReference("top");
                break;
        }
    }

    public static void commonDialog(Context mContext,String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setNegativeButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });

        //AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void getUserPreference(Context mContext){
        if(!PreferenceUtils.getDisplayName(mContext).equals("")||
                !PreferenceUtils.getEmail(mContext).equals("")||
                !PreferenceUtils.getPhotoUrl(mContext).equals("")){

            UserName = PreferenceUtils.getDisplayName(mContext);
            UserEmail = PreferenceUtils.getEmail(mContext);
            UserPhoto = PreferenceUtils.getPhotoUrl(mContext);

        }else {
            UserName = "Guest User";
            UserEmail = "";
            UserPhoto = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5l5MBmXRwD_2mFUZsSHhw9YmBP4m_8Vg7YZZxXDZ17SfE3bZy2w";
        }

    }

    public static void showSpotDialogue(Context mContext){
        watingDialog = new SpotsDialog(mContext);
        watingDialog.show();
        watingDialog.setMessage("Please Wait");
        watingDialog.setCancelable(false);
    }

    public static void dismissSpotDilogue(){
        watingDialog.dismiss();
    }


}
