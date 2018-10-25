package com.pasistence.knockwork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pasistence.knockwork.Common.PreferenceUtils;

import info.hoang8f.widget.FButton;

public class SelectionActivity extends AppCompatActivity
{

    FButton btnHire,btnWork;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        mInit();
    }

    //Init
    private void mInit() {
        mContext = SelectionActivity.this;
        btnHire = (FButton)findViewById(R.id.btn_hire);
        btnWork = (FButton)findViewById(R.id.btn_work);

        btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,LoginActivity.class));
                PreferenceUtils.setUserType(mContext,"Lancer");
            }
        });
        btnHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,LoginActivity.class));
                PreferenceUtils.setUserType(mContext,"Client");
            }
        });
    }


}
