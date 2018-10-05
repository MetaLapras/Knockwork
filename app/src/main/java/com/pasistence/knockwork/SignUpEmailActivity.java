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
    TextView txt_email,txt_status,txt_uid;
    Button btn_send,btn_refresh,btn_Signout;
    ImageView imageView;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);

        firebaseAuth = FirebaseAuth.getInstance();
        mInit();
        setInfo();
        monClick();
    }


    private void mInit() {


            txt_email    = (TextView) findViewById(R.id.txt_email);
            txt_status   = (TextView) findViewById(R.id.txt_status);
            txt_uid      = (TextView) findViewById(R.id.txt_uid);
            btn_send     = (Button) findViewById(R.id.btn_send);
            btn_refresh  = (Button) findViewById(R.id.btn_refresh);
            imageView    = (ImageView) findViewById(R.id.img);
            btn_Signout  = (Button) findViewById(R.id.btn_logout);
        }

    private void monClick() {
        btn_send.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
        btn_Signout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btn_send) {
            btn_send.setEnabled(false);

            FirebaseAuth.getInstance().getCurrentUser()
                    .sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            btn_send.setEnabled(true);

                            if (task.isSuccessful())
                                Toast.makeText(SignUpEmailActivity.this, "Verification email sent to : " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(Status.this, "task complete", Toast.LENGTH_SHORT).show();

                            else
                                Toast.makeText(SignUpEmailActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        if (v == btn_refresh) {
            FirebaseAuth.getInstance().getCurrentUser()
                    .reload()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            setInfo();
                        }
                    });
        }

        if (v==btn_Signout)
        {
            signOut();
        }
    }





      /*  btn_Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });*/

        //Set Event
      /*  btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_send.setEnabled(false);

                FirebaseAuth.getInstance().getCurrentUser()
                        .sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                btn_send.setEnabled(true);

                                if (task.isSuccessful())
                                    Toast.makeText(SignUpEmailActivity.this, "Verification email sent to : "+FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(Status.this, "task complete", Toast.LENGTH_SHORT).show();

                                else
                                    Toast.makeText(SignUpEmailActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });*/

       /* btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().getCurrentUser()
                        .reload()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                setInfo();
                            }
                        });
            }
        });
    }*/



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
                //txt_uid.setText(user.getDisplayName());
                txt_email.setText(new StringBuilder("EMAIL : ").append(user.getEmail()));
                txt_uid.setText(new StringBuilder("UID : ").append(user.getUid()));
                txt_status.setText(new StringBuilder("STATUS : ").append(String.valueOf(user.isEmailVerified())));
            }

        }


      /*  Log.e("-->user", user.getPhotoUrl().getPath());
        Log.e("-->user", user.getDisplayName());*/
       /* Log.e("-->user", user.getProviderData().toString());
        for (Iterator iterator = user.getProviderData().iterator(); iterator.hasNext(); ){
            Log.e("-->as", iterator.next().toString());
    }))));*/

    }


}
