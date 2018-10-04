package com.pasistence.knockwork.Employeer.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Common {

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
