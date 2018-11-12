package com.pasistence.knockwork;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pasistence.knockwork.ChatBox.ChatActivity;
import com.pasistence.knockwork.Client.Activities.DashboardActivity;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Freelancer.Activities.FreeLancerDashboardActivity;

import dmax.dialog.SpotsDialog;
import info.hoang8f.widget.FButton;

public class SelectionActivity extends AppCompatActivity
{

    private static final String TAG = "selection";
    FButton btnHire,btnWork;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

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

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(PreferenceUtils.getUserType(mContext).equals(Common.Client)){

                            startActivity(new Intent(mContext,DashboardActivity.class));
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
                }, 3000);
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
}
