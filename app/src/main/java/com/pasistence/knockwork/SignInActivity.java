package com.pasistence.knockwork;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.pasistence.knockwork.Client.Activities.DashboardActivity;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.CheckBox;

import info.hoang8f.widget.FButton;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    MaterialEditText edtEmail,edtPassword;
    TextView txtSignup;
    CheckBox chkRememberMe;
    FButton btnLogin;
    Context mContext;

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
                startActivity(new Intent(mContext,DashboardActivity.class));
        }
    }
}
