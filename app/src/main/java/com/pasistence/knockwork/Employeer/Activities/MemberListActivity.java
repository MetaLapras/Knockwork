package com.pasistence.knockwork.Employeer.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.pasistence.knockwork.R;

public class MemberListActivity extends AppCompatActivity {

     RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        relativeLayout = (RelativeLayout)findViewById(R.id.member_list);
        View child = getLayoutInflater().inflate(R.layout.custome_member_templets, null);
        relativeLayout.addView(child);
    }
}
