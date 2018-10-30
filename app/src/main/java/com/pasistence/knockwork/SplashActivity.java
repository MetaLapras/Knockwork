package com.pasistence.knockwork;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pasistence.knockwork.Common.Common;
import com.squareup.picasso.Picasso;

import io.fabric.sdk.android.Fabric;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "splash";
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_spash);
        printkeyhash();

        final ImageView imgProfile = (ImageView)findViewById(R.id.img_profile);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // This method will be executed once the timer is over
                Intent i = new Intent(SplashActivity.this, SelectionActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }

    private void printkeyhash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.pasistence.knockwork",
                    PackageManager.GET_SIGNATURES);

            for (Signature signature :info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
