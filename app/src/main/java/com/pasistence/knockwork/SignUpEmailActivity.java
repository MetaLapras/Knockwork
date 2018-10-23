package com.pasistence.knockwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpEmailActivity extends AppCompatActivity implements View.OnClickListener {

    private static String aaa;
    private static final String TAG =aaa ;
    TextView textViewEmail,textViewStatus,textViewUID;
    Button buttonSend,buttonRefresh,buttonSignOut;
    ImageView imageView;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);
        firebaseAuth = FirebaseAuth.getInstance();
        mInit();
        setInfo();
        mOnClick();
    }


    private void mInit() {


            textViewEmail    = (TextView) findViewById(R.id.txt_email);
            textViewStatus   = (TextView) findViewById(R.id.txt_status);
            textViewUID      = (TextView) findViewById(R.id.txt_uid);
            buttonSend       = (Button) findViewById(R.id.btn_send);
            buttonRefresh    = (Button) findViewById(R.id.btn_refresh);
            buttonSignOut    = (Button) findViewById(R.id.btn_logout);
            imageView        = (ImageView) findViewById(R.id.img);
            }

    private void mOnClick() {
        buttonSignOut.setOnClickListener(this);
        buttonSend.setOnClickListener(this);
        buttonRefresh.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == buttonSend) {
            buttonSend.setEnabled(false);

            FirebaseAuth.getInstance().getCurrentUser()
                    .sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            buttonSend.setEnabled(true);

                            if (task.isSuccessful())
                                Toast.makeText(SignUpEmailActivity.this, "Verification email sent to : " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(SignUpEmailActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        if (v == buttonRefresh) {
            FirebaseAuth.getInstance().getCurrentUser()
                    .reload()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            setInfo();
                        }
                    });
        }

        if (v==buttonSignOut)
        {
            signOut();
        }
    }

    public void signOut() {
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e(TAG, "USER LOGGED OUT!!!");
                        finish();
                    }
                });

        //closing activity
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }


    private void setInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if(user.getPhotoUrl() !=null)
            {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(imageView);

            }
            if (user.getDisplayName() !=null)
            {
                textViewEmail.setText(new StringBuilder("EMAIL : ").append(user.getEmail()));
                textViewUID.setText(new StringBuilder("UID : ").append(user.getUid()));
                textViewStatus.setText(new StringBuilder("STATUS : ").append(String.valueOf(user.isEmailVerified())));

            }

        }

    }


}
