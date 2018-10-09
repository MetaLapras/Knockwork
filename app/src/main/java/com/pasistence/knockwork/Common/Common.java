package com.pasistence.knockwork.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.Remote.RetrofitClient;


public class Common {
   // private static final String BASE_URL = "http://192.168.0.151/mantra/public/index.php/api/";
    //private static final String BASE_URL = "http://ip.jsontest.com/";

    public static final String BASE_URL = "http://192.168.0.102/knockwork/public/index.php/api/jobdescriptions/lancer/page/2/";

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

}
