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

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    //localhost
    //public static final String BASE_URL = "http://10.0.2.2/knockwork/public/index.php/api/";
    //Server
    public static final String BASE_URL = "http://52.172.221.235:8985/knockwork/public/index.php/api/";
    private static final String TAG = "common";
    //public static final String BASE_URL = "http://192.168.0.150/mantra/";

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
    }
    public static void showDialog(){
        alertDialog.show();
    }
    public static void hideDialog(){
        alertDialog.dismiss();
    }

}
