package com.pasistence.knockwork;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.pasistence.knockwork.Client.Activities.DashboardActivity;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Freelancer.Activities.FreeLancerDashboardActivity;


import com.pasistence.knockwork.Client.Activities.DashboardActivity;
import com.pasistence.knockwork.Freelancer.Activities.FreeLancerDashboardActivity;
import com.pasistence.knockwork.Model.UserData;

import java.util.Arrays;

import info.hoang8f.widget.FButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "loginActivity" ;
    Context mContext;
    FButton buttonEmail,buttonGmail,buttonFacebook,buttonPhone;
    TextView txtSignIn,txtSkip;
    LoginButton fbLoginButton, emailLoginButton;

    RadioGroup radioGroupWH;
    RadioButton radiobtnWork,radiobtnHire;


    private static final int EMAIL_LOGIN      = 1000;
    private static final int GMAIL_LOGIN      = 2000;
    private static final int FACEBOOK_LOGIN   = 3000;
    private static final int PHONE_LOGIN      = 4000;

    public FirebaseAuth firebaseAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        mInit();
        mOnClick();
    }
     //Init
    private void mInit() {
        mContext       = LoginActivity.this;
        buttonEmail    = (FButton)findViewById(R.id.btn_SignUp_Email);
        buttonGmail      = (FButton)findViewById(R.id.btn_SignUp_Gmail);
        buttonFacebook   = (FButton)findViewById(R.id.btn_SignUp_Facebook);
        txtSignIn      = (TextView)findViewById(R.id.txt_SignIn);
        txtSkip        = (TextView)findViewById(R.id.txt_skip);
        buttonPhone      = (FButton)findViewById(R.id.btn_SignUp_Number);
        radioGroupWH    =(RadioGroup)findViewById(R.id.radio_group);
        radiobtnWork = (RadioButton)findViewById(R.id.radio_button_work);
        radiobtnHire = (RadioButton)findViewById(R.id.radio_button_hire);
    }

    private void mOnClick() {
        buttonEmail.setOnClickListener(this);
        buttonGmail.setOnClickListener(this);
        buttonFacebook.setOnClickListener(this);
        buttonPhone.setOnClickListener(this);
        txtSkip.setOnClickListener(this);
        txtSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == buttonEmail) {
           // startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), EMAIL_LOGIN);
           startActivity(new Intent(LoginActivity.this,EmailActivity.class));
        }

        if (v == buttonGmail) {
            AuthUI.IdpConfig googleIdp = new AuthUI.IdpConfig.GoogleBuilder()
                    .build();


            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(googleIdp))
                    .build(), GMAIL_LOGIN);

        }

            if (v == buttonFacebook) {
                Toast.makeText(LoginActivity.this, "Facebook Login...", Toast.LENGTH_SHORT).show();

                AuthUI.IdpConfig facebookIdp = new AuthUI.IdpConfig.FacebookBuilder()
                        .build();


                startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(facebookIdp))
                        .build(), FACEBOOK_LOGIN);

        }

        if (v == buttonPhone) {

            AuthUI.IdpConfig phoneConfigWithDefaultNumber = new AuthUI.IdpConfig.PhoneBuilder()
                    .setDefaultNumber("+91")
                    .build();

            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(phoneConfigWithDefaultNumber))
                    .build(), PHONE_LOGIN);

        }

        if (v == txtSignIn) {
            startActivity(new Intent(mContext, FreeLancerDashboardActivity.class));
        }

        if (v == txtSkip) {
            startActivity(new Intent(mContext, DashboardActivity.class));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == EMAIL_LOGIN) {
            handleSignInResponse(resultCode, data);
            //startActivity(new Intent(LoginActivity.this,SignUpEmailActivity.class));

            return;
        }
        if (requestCode == GMAIL_LOGIN) {
            handleSignInResponse(resultCode, data);
             FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            Log.e(TAG, currentUser.getUid());
            Log.e(TAG, currentUser.getDisplayName());
            Log.e(TAG, currentUser.getEmail());
            Log.e(TAG, currentUser.getPhotoUrl().toString());
           // Log.e(TAG, currentUser.getPhoneNumber());

            UserData userData = new UserData(currentUser.getDisplayName(),currentUser.getUid(),currentUser.getEmail(),currentUser.getPhotoUrl());
            new Common().setUserData(userData);

            return;
        }
        if (requestCode == FACEBOOK_LOGIN) {
            handleSignInResponse(resultCode, data);
           /* FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            Log.e(TAG, currentUser.getUid());
            Log.e(TAG, currentUser.getDisplayName());
            Log.e(TAG, currentUser.getEmail());
            Log.e(TAG, currentUser.getPhotoUrl().toString());
            Log.e(TAG, currentUser.getPhoneNumber());*/
            return;
        }
        if (requestCode == PHONE_LOGIN){
            phoneNumberSignInResponse(resultCode, data);
            return;
        }
    }

    private void handleSignInResponse(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (radioGroupWH.getCheckedRadioButtonId()){
                case R.id.radio_button_hire:
                    Intent intent1 = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent1);
                    finish();
                    break;

                case R.id.radio_button_work:
                    Intent intent2 = new Intent(LoginActivity.this, FreeLancerDashboardActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
            }

        }
        else
            Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
    }

    private void phoneNumberSignInResponse(int resultCode, Intent data) {
        IdpResponse response =  IdpResponse.fromResultIntent(data);

        //successfully sign in
        if(resultCode == RESULT_OK)
        {
            try{
            if(!FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty())
            {

                switch (radioGroupWH.getCheckedRadioButtonId()){
                    case R.id.radio_button_hire:
                        Intent intent1 = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent1);
                        finish();
                        break;

                    case R.id.radio_button_work:
                        Intent intent2 = new Intent(LoginActivity.this, FreeLancerDashboardActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }

//                startActivity(new Intent(LoginActivity.this,FreeLancerDashboard.class).putExtra("phone",FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()));
//                finish();
                return;
            }
            else
            {
                if (response == null)
                {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.getError().getErrorCode()== ErrorCodes.NO_NETWORK)
                {
                    Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.getError().getErrorCode()== ErrorCodes.UNKNOWN_ERROR)
                {
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Toast.makeText(this, "Unknown Sign In Error!!!!", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
                Log.e("error>> ",e.getMessage());
            }
        }
    }

}
