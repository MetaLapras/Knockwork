package com.pasistence.knockwork.Employeer.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pasistence.knockwork.R;

public class PhonNumberSignIn extends AppCompatActivity {

    TextView txtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phon_number_sign_in);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("Firebase Auth");
        setSupportActionBar(toolbar);

        txtPhone = (TextView)findViewById(R.id.txtPhone);
        if (getIntent()!= null)
            txtPhone.setText(getIntent().getStringExtra("phone"));
    }
}
