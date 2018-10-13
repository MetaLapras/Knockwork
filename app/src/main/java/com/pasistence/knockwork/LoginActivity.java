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
import com.google.firebase.auth.PhoneAuthProvider;
import com.pasistence.knockwork.Employeer.Activities.DashboardActivity;
import com.pasistence.knockwork.Freelancer.Activities.FreeLancerDashboard;

import java.util.Arrays;

import info.hoang8f.widget.FButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;
    FButton buttonEmail,buttonGmail,buttonFacebook,buttonPhone;
    TextView txtSignIn,txtSkip;
    LoginButton fbLoginButton, emailLoginButton;

    RadioGroup radioGroupWH;
    RadioButton radioButton;


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
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), EMAIL_LOGIN);

        if(v == buttonPhone)
        {
            AuthUI.IdpConfig phoneConfigWithDefaultNumber = new AuthUI.IdpConfig.PhoneBuilder()
                    .setDefaultNumber("+91")
                    .build();

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(phoneConfigWithDefaultNumber))
                            .build(),PRE_LOGIN);


        }
        if (v == buttonEmail)
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                    .build(), PRE_LOGIN);

        }
        if (v==buttonGmail){
            AuthUI.IdpConfig googleIdp = new AuthUI.IdpConfig.GoogleBuilder()
                    .build();


            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(googleIdp))
                    .build(), GMAIL_LOGIN);


            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(googleIdp))
                            .build(), PRE_LOGIN);



        }

        if (v==buttonFacebook) {
            Toast.makeText(LoginActivity.this, "Facebook Login...", Toast.LENGTH_SHORT).show();

            AuthUI.IdpConfig facebookIdp = new AuthUI.IdpConfig.FacebookBuilder()
                    .build();


            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(facebookIdp))
                    .build(),FACEBOOK_LOGIN);

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(facebookIdp))
                            .build(),PRE_LOGIN);



        }

        if(v == buttonPhone) {

            AuthUI.IdpConfig phoneConfigWithDefaultNumber = new AuthUI.IdpConfig.PhoneBuilder()
                    .setDefaultNumber("+91")
                    .build();

            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(phoneConfigWithDefaultNumber))
                    .build(),PHONE_LOGIN);

        }

        if(v==txtSignIn){
            startActivity(new Intent(mContext,FreeLancerDashboard.class));
        }

        if(v==txtSkip){
            startActivity(new Intent(mContext,DashboardActivity.class));
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == EMAIL_LOGIN) {
            handleSignInResponse(resultCode, data);
            return;
        }
        if (requestCode == GMAIL_LOGIN) {
            handleSignInResponse(resultCode, data);
            return;
        }
        if (requestCode == FACEBOOK_LOGIN) {
            handleSignInResponse(resultCode, data);
            return;
        }
        if (requestCode == PHONE_LOGIN){
            phoneNumberSignInResponse(resultCode, data);
            return;
        }

    }

    private void handleSignInResponse(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            switch (radioGroupWH.getCheckedRadioButtonId()){
                case R.id.radio_button_hire:
                    intent.putExtra("isLancer",false);
                    intent.putExtra("isClient",true);
                    break;
                case R.id.radio_button_work:
                    intent.putExtra("isLancer",true);
                    intent.putExtra("isClient",false);
                    break;
            }

            startActivity(intent);
            finish();
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
                startActivity(new Intent(LoginActivity.this,FreeLancerDashboard.class).putExtra("phone",FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()));
                finish();
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

    private void handleSignInResponse(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Intent newActivity = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(newActivity);
            finish();
            return;
        }
        else
            Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
    }

}
