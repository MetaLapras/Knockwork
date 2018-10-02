package com.pasistence.knockwork.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.pasistence.knockwork.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import info.hoang8f.widget.FButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;
    FButton btn_email,btn_gmail,btn_facebook;
    //LoginButton fbLoginButton, emilLoginButton;

    private static final int PRE_LOGIN = 1000;
    public FirebaseAuth firebaseAuth;
    //private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        mInit();
        monClick();
    }
     //Init
    private void mInit() {
        mContext       = LoginActivity.this;
        btn_email      = (FButton)findViewById(R.id.btn_SignUp_Email);
        btn_gmail      = (FButton)findViewById(R.id.btn_SignUp_Gmail);
        btn_facebook   = (FButton)findViewById(R.id.btn_SignUp_Facebook);
    }

    private void monClick() {
        btn_email.setOnClickListener(this);
        btn_gmail.setOnClickListener(this);
        btn_facebook.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v==btn_email)
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .build(), PRE_LOGIN);
        }
        if (v==btn_gmail){
            AuthUI.IdpConfig googleIdp = new AuthUI.IdpConfig.GoogleBuilder()
                    //.setScopes(Arrays.asList(Scopes.GAMES))
                    .build();


            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(googleIdp))
                            .build(), PRE_LOGIN);
        }
        if (v==btn_facebook)
        {
            Toast.makeText(LoginActivity.this, "Facebook Login...", Toast.LENGTH_SHORT).show();

            AuthUI.IdpConfig facebookIdp = new AuthUI.IdpConfig.FacebookBuilder()
                    //  .setPermissions(Arrays.asList("user_friends"))
                    .build();

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(facebookIdp))
                            .build(),PRE_LOGIN);
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PRE_LOGIN) {
            handleSignInResponse(resultCode, data);
            return;
        }
    }

    private void handleSignInResponse(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Intent newActivity = new Intent(LoginActivity.this, SignUpEmailActivity.class);
            startActivity(newActivity);
            finish();
            return;
        }
        else
            Toast.makeText(this, "Login Failed!!!!!!!", Toast.LENGTH_SHORT).show();
    }
}
