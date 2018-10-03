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
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.pasistence.knockwork.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import info.hoang8f.widget.FButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;
    FButton btn_email,btn_gmail,btn_facebook,btn_phone;
    TextView txtSignIn,txtSkip;
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
        txtSignIn      = (TextView)findViewById(R.id.txt_SignIn);
        txtSkip      = (TextView)findViewById(R.id.txt_skip);
        btn_phone      = (FButton)findViewById(R.id.btn_SignUp_Number);
    }

    private void monClick() {
        btn_email.setOnClickListener(this);
        btn_gmail.setOnClickListener(this);
        btn_facebook.setOnClickListener(this);
        btn_phone.setOnClickListener(this);
        txtSkip.setOnClickListener(this);
        txtSignIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == btn_phone)
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
        if (v == btn_email)
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
        if(v==txtSignIn){
            startActivity(new Intent(mContext,SignInActivity.class));
        }
        if(v==txtSkip){
            startActivity(new Intent(mContext,DashboardActivity.class));
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PRE_LOGIN) {
            handleSignInResponse(resultCode, data);
            phoneNumberSignInResponse(resultCode, data);
            return;
        }
    }

    private void phoneNumberSignInResponse(int resultCode, Intent data) {
        IdpResponse response =  IdpResponse.fromResultIntent(data);

        //successfully sign in
        if(resultCode == RESULT_OK)
        {
            if(!FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty())
            {
                startActivity(new Intent(LoginActivity.this,PhonNumberSignIn.class).putExtra("phone",FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()));
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
            Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
    }
}
