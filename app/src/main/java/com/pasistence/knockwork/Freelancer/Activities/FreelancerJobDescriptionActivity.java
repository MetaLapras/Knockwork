package com.pasistence.knockwork.Freelancer.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.pasistence.knockwork.R;

import java.util.Calendar;

import info.hoang8f.widget.FButton;

public class FreelancerJobDescriptionActivity extends FreeLancerBaseActivity {

    FButton btnSubmitProposal;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_freelancer_job_description, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        mInit();

        btnSubmitProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FreelancerJobDescriptionActivity.this,SubmitProposalActivity.class));
            }
        });


    }

    private void mInit() {
        mContext = FreelancerJobDescriptionActivity.this;
        btnSubmitProposal = (FButton)findViewById(R.id.btn_submit_proposal);
    }
}
