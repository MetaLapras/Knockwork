package com.pasistence.knockwork.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.pasistence.knockwork.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;
    LoginButton fbLoginButton;

    //Callback Manager
    CallbackManager callbackManager;
    TextView txtSignIn,txtEmail,txtFriends,txtDob;
    ImageView imgProfilePic;
    ImageView imgProfilePic2;
    ProgressDialog mDialog;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        mInit();
        mOnClick();
        fbLoginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"));
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDialog = new ProgressDialog(mContext);
                mDialog.setMessage("Retriving Data");
                mDialog.show();
                String accesstoken = loginResult.getAccessToken().getToken();
                Log.e("accessToken",accesstoken);


                final Profile profile = Profile.getCurrentProfile();

//                Log.e("profile F",profile.getFirstName().toString());
//                Log.e("profile L",profile.getLastName().toString());
//                Log.e("profile M",profile.getMiddleName().toString());
//                Log.e("profile Ur",profile.getId().toString());
//                Log.e("profile N",profile.getName().toString());
//                Picasso.with(mContext)
//                        .load(profile.getProfilePictureUri(100,100)).into(imgProfilePic2);


                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.e("response",object.toString());
                        getFacebookData(object,profile);
                    }
                });

                //Request Graph API
                Bundle parameters = new Bundle();
                parameters.putString("fields","id,email,birthday,friends");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //if Already Login
        if(AccessToken.getCurrentAccessToken() !=null){
           // txtEmail.setText(AccessToken.getCurrentAccessToken().getUserId());
        }
    }

    private void mOnClick() {
        txtSignIn.setOnClickListener(this);
    }

    private void getFacebookData(JSONObject object, Profile profile) {
        try{
            URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"/picture?width=100&height=100");
           /* Picasso.with(mContext).load(profile_picture.toString()).into(imgProfilePic);
            txtName.setText(object.getString("id"));
            txtEmail.setText(object.getString("email"));
            txtDob.setText(object.getString("birthday"));
            txtFriends.setText(object.getString("Friends : "+object.getJSONObject("friends").getJSONObject("summary").getString("total_count")));*/
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void mInit() {
    mContext = LoginActivity.this;
    fbLoginButton = (LoginButton)findViewById(R.id.fb_login_button);

        imgProfilePic = (ImageView)findViewById(R.id.img_profile);
        Picasso.with(mContext).load(R.drawable.ic_logo).into(imgProfilePic);

        txtSignIn = (TextView)findViewById(R.id.txt_SignIn);

    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.txt_SignIn :
              startActivity(new Intent(mContext,SignInActivity.class));
      }
    }
}
