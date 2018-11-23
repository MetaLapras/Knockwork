package com.pasistence.knockwork;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pasistence.knockwork.Client.Activities.DashboardActivityClient;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.Config;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Freelancer.Activities.FreeLancerDashboardActivity;

import java.util.Random;

import dmax.dialog.SpotsDialog;
import info.hoang8f.widget.FButton;

public class SelectionActivity extends AppCompatActivity
{

    private static final String TAG = "selection";
    FButton btnHire,btnWork;
    Context mContext;

    public BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
//        FirebaseMessaging.getInstance().subscribeToTopic("marketing");
        FirebaseMessaging.getInstance().subscribeToTopic("marketing")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });


        mBroadcastReceive();
        mInit();

        try {
            //Create Dialog

        if(Common.isConnectedToInterNet(getBaseContext())) {

            FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();

            if(auth!=null){

                final AlertDialog watingDialog = new SpotsDialog(this);
                watingDialog.show();
                watingDialog.setMessage("Please Wait");
                watingDialog.setCancelable(false);

                Log.e(TAG,auth.getDisplayName());
                Log.e(TAG, auth.getDisplayName()+"");
                Log.e(TAG, auth.getUid()+"");
                Log.e(TAG, auth.getEmail()+"");
                Log.e(TAG, auth.getPhotoUrl().toString());
                Log.e(TAG, auth.getProviders().toString()+"");
                Log.e(TAG, auth.getPhoneNumber()+"");


                        if(PreferenceUtils.getUserType(mContext).equals(Common.Client)){

                            startActivity(new Intent(mContext,DashboardActivityClient.class));
                            watingDialog.dismiss();
                            finish();

                        }else if(PreferenceUtils.getUserType(mContext).equals(Common.Lancer)){

                            startActivity(new Intent(mContext,FreeLancerDashboardActivity.class));
                            watingDialog.dismiss();
                            finish();

                        }

                        // imgProfile.setAnimation(AnimationUtils.loadAnimation(SplashActivity.this,R.anim.zoom_in_animation));

                        // This method will be executed once the timer is over


            }
        }else
            {
                Common.commonDialog(mContext,"Please Check Your Internet Connection !");
                return;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
      /*  //Create Dialog
        final AlertDialog watingDialog = new SpotsDialog(this);
        watingDialog.show();
        watingDialog.setMessage("Please Wait");
        watingDialog.setCancelable(false);*/


    }


    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
  //      NotificationUtils.clearNotifications(getApplicationContext());
    }


    private void mBroadcastReceive() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);


            }
        };
    }

    //Init
    private void mInit() {
        mContext = SelectionActivity.this;
        btnHire = (FButton)findViewById(R.id.btn_hire);
        btnWork = (FButton)findViewById(R.id.btn_work);

        btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,LoginActivity.class));
                //startActivity(new Intent(mContext,FreeLancerDashboardActivity.class));
                PreferenceUtils.setUserType(mContext,Common.Lancer);
                finish();
            }
        });
        btnHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(mContext,ChatActivity.class));
                startActivity(new Intent(mContext,LoginActivity.class));
                PreferenceUtils.setUserType(mContext,Common.Client);
                finish();
            }
        });
    }

    private void showNotification(String title,String body){
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID= "com.pasistence.knockwork";

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("ASHCALNZ Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,
                NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_logo)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("Info");

        notificationManager.notify(new Random().nextInt(),notificationBuilder.build());

    }


}
