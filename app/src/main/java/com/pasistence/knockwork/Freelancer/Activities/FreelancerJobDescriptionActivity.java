package com.pasistence.knockwork.Freelancer.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
import android.widget.TextView;

import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import info.hoang8f.widget.FButton;

public class FreelancerJobDescriptionActivity extends FreeLancerBaseActivity {

    private static final String TAG = "details";
    FButton btnSubmitProposal;
    Context mContext;
    ApiPostJobResponse.Result clientJobs ;
    TextView txtTitle,txtFeature,txtBudget,txtDuration;
    TextView txtDescription,txtName,txtState,txtspend,txtReview;
    CircleImageView profileImage;

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
                Intent intent = new Intent(mContext,SubmitProposalActivity.class);
                intent.putExtra("proposal",Common.PROPOSAL);
                intent.putExtra("jobs",clientJobs);
                startActivity(intent);
            }
        });


    }

    private void mInit() {
        mContext = FreelancerJobDescriptionActivity.this;
        txtTitle = (TextView)findViewById(R.id.txt_job_title);
        txtFeature = (TextView)findViewById(R.id.txt_job_feature);
        txtBudget = (TextView)findViewById(R.id.txt_job_budget);
        txtDuration = (TextView)findViewById(R.id.txt_jobduration);
        txtDescription = (TextView)findViewById(R.id.txt_job_description);
        txtName = (TextView)findViewById(R.id.txt_name_lancer);
        txtState = (TextView)findViewById(R.id.txt_lancer_state);
        txtspend = (TextView)findViewById(R.id.txt_lancer_earing);
        txtReview = (TextView)findViewById(R.id.txt_lancer_per);
        profileImage = (CircleImageView) findViewById(R.id.circularImage_profile);


        btnSubmitProposal = (FButton)findViewById(R.id.btn_submit_proposal);

        if(getIntent().getStringExtra("details") != null){

              loadAllDetails();

        }else {
            Log.e(TAG, "Failed Intent" );
        }

    }

    private void loadAllDetails() {

        clientJobs = (ApiPostJobResponse.Result) getIntent().getSerializableExtra("jobs");
        Log.e(TAG+"-Intent", clientJobs.toString());

        txtTitle.setText(clientJobs.getTitle());
        txtFeature.setText(clientJobs.getType());
        txtBudget.setText(clientJobs.getRate());
        txtDuration.setText(clientJobs.getDuration());
        txtDescription.setText(clientJobs.getDetails());
        txtName.setText(clientJobs.getCname());
        txtspend.setText("spend");
        txtReview.setText("review");
        txtState.setText("state");

        Picasso.with(mContext).load(clientJobs.getProfileImage()).into(profileImage);
    }

}
