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
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Freelancer.Activities.FreeLancerDashboardActivity;


import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterClient;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.Arrays;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "loginActivity" ;
    Context mContext;
    FButton buttonEmail,buttonGmail,buttonFacebook,buttonPhone;
    TextView txtSignIn,txtSkip;
    LoginButton fbLoginButton, emailLoginButton;

    RadioGroup radioGroupWH;
    RadioButton radiobtnWork,radiobtnHire;
    MyApi mService;


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
        mContext            = LoginActivity.this;
        buttonEmail         = (FButton)findViewById(R.id.btn_SignUp_Email);
        buttonGmail         = (FButton)findViewById(R.id.btn_SignUp_Gmail);
        buttonFacebook      = (FButton)findViewById(R.id.btn_SignUp_Facebook);
        txtSignIn           = (TextView)findViewById(R.id.txt_SignIn);
        txtSkip             = (TextView)findViewById(R.id.txt_skip);
        buttonPhone         = (FButton)findViewById(R.id.btn_SignUp_Number);
       /* radioGroupWH        = (RadioGroup)findViewById(R.id.radio_group);
        radiobtnWork        = (RadioButton)findViewById(R.id.radio_button_work);
        radiobtnHire        = (RadioButton)findViewById(R.id.radio_button_hire);*/

        //Init retrofit
        mService = Common.getApi();
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
           finish();
        }

        if (v == buttonGmail) {
            if(Common.isConnectedToInterNet(mContext)){

                AuthUI.IdpConfig googleIdp = new AuthUI.IdpConfig.GoogleBuilder()
                        .build();
                startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(googleIdp))
                        .build(), GMAIL_LOGIN);

            }else {
                Common.commonDialog(mContext,"Please Check Your Internet Connection !");
                Common.showDialog();
            }

        }

        if (v == buttonFacebook) {

            if(Common.isConnectedToInterNet(mContext)){

                Toast.makeText(LoginActivity.this, "Facebook Login...", Toast.LENGTH_SHORT).show();
                AuthUI.IdpConfig facebookIdp = new AuthUI.IdpConfig.FacebookBuilder()
                        .build();
                startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(facebookIdp))
                        .build(), FACEBOOK_LOGIN);

            }else {
                Common.commonDialog(mContext,"Please Check Your Internet Connection !");
                Common.showDialog();
            }


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
            startActivity(new Intent(mContext, SignInActivity.class));
            finish();
        }

        if (v == txtSkip) {
            startActivity(new Intent(mContext, DashboardActivity.class));
            finish();
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
            Log.v(TAG, currentUser.getUid());
            Log.v(TAG, currentUser.getDisplayName());
            Log.v(TAG, currentUser.getEmail());
            Log.v(TAG, currentUser.getPhotoUrl().toString());
            Log.v(TAG, currentUser.getProviderId());
            Log.v(TAG, currentUser.getProviders().toString());
            Log.v(TAG, currentUser.getPhotoUrl().toString());
           // Log.e(TAG, currentUser.getPhoneNumber());
            if(PreferenceUtils.getUserType(mContext).equals("Lancer")){
                RegisterLancerUser(currentUser,Common.gmail);
            }else if(PreferenceUtils.getUserType(mContext).equals("Client")){
                RegisterClientUser(currentUser,Common.gmail);
            }else
            {

            }


            return;
        }
        if (requestCode == FACEBOOK_LOGIN) {
            handleSignInResponse(resultCode, data);
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            Log.v(TAG, currentUser.getUid());
            Log.v(TAG, currentUser.getDisplayName());
            Log.v(TAG, currentUser.getEmail());
            Log.v(TAG, currentUser.getPhotoUrl().toString());
           // Log.v(TAG, currentUser.getPhoneNumber());
            Log.v(TAG, currentUser.getProviderId());
            Log.v(TAG, currentUser.getProviders().toString());
            Log.v(TAG, currentUser.getPhotoUrl().toString());

            if(PreferenceUtils.getUserType(mContext).equals("Lancer")){
                RegisterLancerUser(currentUser, Common.facebook);
            }else if(PreferenceUtils.getUserType(mContext).equals("Client")){
                RegisterClientUser(currentUser, Common.facebook);
            }else
            {

            }

            return;
        }
        if (requestCode == PHONE_LOGIN){
            phoneNumberSignInResponse(resultCode, data);
            return;
        }
    }

    private void handleSignInResponse(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (PreferenceUtils.getUserType(mContext)){
                case Common.Client:
                    Intent intent1 = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent1);
                    finish();
                    break;

                case Common.Lancer:
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

                Log.e(TAG, FirebaseAuth.getInstance().getCurrentUser().getUid());
                Log.e(TAG, FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());

                if(PreferenceUtils.getUserType(mContext).equals("Lancer")){

                    RegisterLancerUser(FirebaseAuth.getInstance().getCurrentUser(), Common.phone);

                }else if(PreferenceUtils.getUserType(mContext).equals("Client")){
                    RegisterClientUser(FirebaseAuth.getInstance().getCurrentUser(), Common.phone);
                }else
                {
                    Common.commonDialog(mContext,"Server Not Found!");
                    Common.showDialog();

                }
                /*switch (radioGroupWH.getCheckedRadioButtonId()){
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
                }*/

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

    private void RegisterLancerUser(FirebaseUser currentUser, String type) {
        String  displayname = null,
                uid = null,
                email = null ,
                photo = null  ,
                provider = null,
                phoneNo = null;

        switch (type){
            case Common.gmail:
                        displayname = currentUser.getDisplayName();
                        uid         = currentUser.getUid();
                        email       = currentUser.getEmail();
                        photo       = currentUser.getPhotoUrl().toString();
                        provider    = currentUser.getProviders().toString();
                        // phoneNo     = currentUser.getPhoneNumber();
                break;
            case Common.facebook:
                        displayname = currentUser.getDisplayName();
                        uid         = currentUser.getUid();
                        email       = currentUser.getEmail();
                        photo       = currentUser.getPhotoUrl().toString();
                        provider    = currentUser.getProviders().toString();
                        //phoneNo     = currentUser.getPhoneNumber();
                break;

            case Common.phone:
                        displayname = "";
                        uid         = currentUser.getUid();
                        email       = "";
                        photo       = "";
                        provider    = "";
                        phoneNo     = currentUser.getPhoneNumber();
                break;

        }
        //Retrofit services
       /* String
                displayname = currentUser.getDisplayName(),
                uid         = currentUser.getUid(),
                email       = currentUser.getEmail(),
                photo       = currentUser.getPhotoUrl().toString(),
                provider    = currentUser.getProviders().toString();
               // phoneNo     = currentUser.getPhoneNumber();*/


        //If registered by Gmail OR Facebook
        mService.RegisterLancer(
                uid,
                displayname,
                email,
                photo,
                provider,
                phoneNo
                ).enqueue(new Callback<ApiResponseRegisterLancer>() {
            @Override
            public void onResponse(Call<ApiResponseRegisterLancer> call, Response<ApiResponseRegisterLancer> response) {

                ApiResponseRegisterLancer result = response.body();
                Log.e(TAG, result.toString());

                Intent intent1 = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent1);
                finish();

            }

            @Override
            public void onFailure(Call<ApiResponseRegisterLancer> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, t.getMessage());
            }
        });

        //Setting Shared Preference
        PreferenceUtils.setDisplayName(mContext,currentUser.getDisplayName());
        PreferenceUtils.setUid(mContext,currentUser.getUid());
        PreferenceUtils.setEmail(mContext,currentUser.getEmail());
        PreferenceUtils.setPhotoUrl(mContext,currentUser.getPhotoUrl().toString());
        PreferenceUtils.setProvider(mContext,currentUser.getProviders().toString());
        //PreferenceUtils.setPhoneNumber(mContext,phoneNo);

        Log.v(TAG, PreferenceUtils.getDisplayName(mContext));
        Log.v(TAG, PreferenceUtils.getUid(mContext));
        Log.v(TAG, PreferenceUtils.getEmail(mContext));
        Log.v(TAG, PreferenceUtils.getPhotoUrl(mContext));
        Log.v(TAG, PreferenceUtils.getProvider(mContext));




    }

    private void RegisterClientUser(FirebaseUser currentUser, String type) {

        String  displayname = null,
                uid = null,
                email = null ,
                photo = null  ,
                provider = null,
                phoneNo = null;

        switch (type){
            case Common.gmail:
                displayname = currentUser.getDisplayName();
                uid         = currentUser.getUid();
                email       = currentUser.getEmail();
                photo       = currentUser.getPhotoUrl().toString();
                provider    = currentUser.getProviders().toString();
                // phoneNo     = currentUser.getPhoneNumber();
                break;
            case Common.facebook:
                displayname = currentUser.getDisplayName();
                uid         = currentUser.getUid();
                email       = currentUser.getEmail();
                photo       = currentUser.getPhotoUrl().toString();
                provider    = currentUser.getProviders().toString();
                //phoneNo     = currentUser.getPhoneNumber();
                break;

            case Common.phone:
                displayname = "";
                uid         = currentUser.getUid();
                email       = "";
                photo       = "";
                provider    = "";
                phoneNo     = currentUser.getPhoneNumber();
                break;

        }

           //Retrofit services
     /*
        String
                displayname = currentUser.getDisplayName(),
                uid         = currentUser.getUid(),
                email       = currentUser.getEmail(),
                photo       = currentUser.getPhotoUrl().toString(),
                provider    = currentUser.getProviders().toString();
                //phoneNo     = currentUser.getPhoneNumber();*/

      /*  if(phoneNo.equals(null)){
            phoneNo = "";
        }
*/
        //If registered by Gmail OR Facebook
        mService.RegisterClient(
                uid,
                displayname,
                email,
                photo,
                provider,
                phoneNo
        ).enqueue(new Callback<ApiResponseRegisterClient>() {
            @Override
            public void onResponse(Call<ApiResponseRegisterClient> call, Response<ApiResponseRegisterClient> response) {

                ApiResponseRegisterClient result = response.body();
                Log.e(TAG, result.toString());

                Intent intent1 = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent1);
                finish();

            }

            @Override
            public void onFailure(Call<ApiResponseRegisterClient> call, Throwable t) {

                t.printStackTrace();
                Log.e(TAG, t.getMessage() );
            }
        });

        //Setting Shared Preference
        PreferenceUtils.setDisplayName(mContext,currentUser.getDisplayName());
        PreferenceUtils.setUid(mContext,currentUser.getUid());
        PreferenceUtils.setEmail(mContext,currentUser.getEmail());
        PreferenceUtils.setPhotoUrl(mContext,currentUser.getPhotoUrl().toString());
        PreferenceUtils.setProvider(mContext,currentUser.getProviders().toString());
        //PreferenceUtils.setPhoneNumber(mContext,phoneNo);


        Log.e(TAG,"pre"+ PreferenceUtils.getDisplayName(mContext));
        Log.e(TAG,"pre"+ PreferenceUtils.getUid(mContext));
        Log.e(TAG,"pre"+ PreferenceUtils.getEmail(mContext));
        Log.v(TAG,"pre"+ PreferenceUtils.getPhotoUrl(mContext));
        Log.v(TAG,"pre"+ PreferenceUtils.getProvider(mContext));

    }

}
