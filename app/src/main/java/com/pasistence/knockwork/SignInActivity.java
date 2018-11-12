package com.pasistence.knockwork;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pasistence.knockwork.Client.Activities.DashboardActivity;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Freelancer.Activities.FreeLancerDashboardActivity;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterClient;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Remote.MyApi;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.CheckBox;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "signIn";
    MaterialEditText edtEmail,edtPassword;
    TextView txtSignup;
    CheckBox chkRememberMe;
    FButton btnLogin;
    Context mContext;
    private FirebaseAuth mAuth;
    String email,password;
    MyApi mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mInit();
        mOnclick();
    }

    private void mInit() {
        mContext            = SignInActivity.this;
        txtSignup           = (TextView)findViewById(R.id.txt_signuphere);
        edtEmail            = (MaterialEditText)findViewById(R.id.txt_email);
        edtPassword         = (MaterialEditText)findViewById(R.id.txt_password);
        btnLogin            = (FButton)findViewById(R.id.btn_login);
        chkRememberMe       = (CheckBox)findViewById(R.id.chk_remember_me);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Init Retrofit
        mService = Common.getApi();
    }

    private void mOnclick() {
        txtSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_signuphere:
                startActivity(new Intent(mContext,LoginActivity.class));
                break;
            case R.id.btn_login:
                if(!isCheck()){

                    email = edtEmail.getText().toString();
                    password = edtPassword.getText().toString();
                    startFirebaseLogin();


                }


        }
    }

    private boolean isCheck() {

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(edtEmail.getText())){
            edtEmail.setError("Please enter Correct EmailId * ");
            focusView=edtEmail;
            cancel=true;
        }
        if (TextUtils.isEmpty(edtPassword.getText())){
            edtPassword.setError("Please enter University * ");
            focusView=edtPassword;
            cancel=true;
        }

        return cancel;

    }

    private void startFirebaseLogin() {

        if(Common.isConnectedToInterNet(mContext)){

        }else {
            Common.commonDialog(mContext,"Please Check Your Internet Connection !");
        }


            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser currentUser = mAuth.getCurrentUser();

                                Log.v(TAG, currentUser.getUid());
                                Log.v(TAG, currentUser.getDisplayName());
                                Log.v(TAG, currentUser.getEmail());
                                Log.v(TAG, currentUser.getPhotoUrl().toString());
                                Log.v(TAG, currentUser.getProviderId());
                                Log.v(TAG, currentUser.getProviders().toString());
                                Log.v(TAG, currentUser.getPhotoUrl().toString());

                                //Setting Shared Preference
                                PreferenceUtils.setDisplayName(mContext,currentUser.getDisplayName());
                                PreferenceUtils.setUid(mContext,currentUser.getUid());
                                PreferenceUtils.setEmail(mContext,currentUser.getEmail());
                                PreferenceUtils.setPhotoUrl(mContext,currentUser.getPhotoUrl().toString());
                                PreferenceUtils.setProvider(mContext,currentUser.getProviders().toString());
                                //PreferenceUtils.setPhoneNumber(mContext,phoneNo);

                                checkUserType(currentUser.getUid());

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
//                                                               Toast.makeText(SignInActivity.this, "User Does not Exist.",
//                                        Toast.LENGTH_SHORT).show();

                                Common.commonDialog(mContext,"invalid credentials !");
                            }

                            // ...
                        }
                    });

    }

    private void checkUserType(String uid) {

        if(PreferenceUtils.getUserType(mContext).equals(Common.Lancer)){

            try{
                mService.checkLancerexist(uid).enqueue(new Callback<ApiResponseRegisterLancer>() {
                    @Override
                    public void onResponse(Call<ApiResponseRegisterLancer> call, Response<ApiResponseRegisterLancer> response) {
                        ApiResponseRegisterLancer result = response.body();
                        Log.e(TAG, result.toString() );

                        startActivity(new Intent(mContext,FreeLancerDashboardActivity.class));

                    }

                    @Override
                    public void onFailure(Call<ApiResponseRegisterLancer> call, Throwable t) {
                        t.printStackTrace();
                        Log.e(TAG, t.getMessage());
                    }
                });


            }catch (Exception e){
                e.printStackTrace();
                Log.e(TAG, e.getMessage() );
            }

        }else if(PreferenceUtils.getUserType(mContext).equals(Common.Client)){

            try{
                mService.checkClientexist(uid).enqueue(new Callback<ApiResponseRegisterClient>() {
                    @Override
                    public void onResponse(Call<ApiResponseRegisterClient> call, Response<ApiResponseRegisterClient> response) {
                        ApiResponseRegisterClient result = response.body();

                        Log.e(TAG, result.toString() );

                        startActivity(new Intent(mContext,DashboardActivity.class));

                    }

                    @Override
                    public void onFailure(Call<ApiResponseRegisterClient> call, Throwable t) {
                        t.printStackTrace();
                        Log.e(TAG, t.getMessage());
                    }
                });


            }catch (Exception e){
                e.printStackTrace();
                Log.e(TAG, e.getMessage() );
            }

        }else
        {
            Common.commonDialog(mContext,"Something Went wrong Please Try Again!" +
                    "Or Check Your Internet Connection!");
        }



    }
}
