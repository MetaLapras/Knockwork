package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.ProfileActivity;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.SelectionActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class ClientBaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    Context mContext;

    TextView txtUserName,txtUserEmail;
    CircleImageView imgUserProfile;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContext = ClientBaseActivity.this;
        mAuth = FirebaseAuth.getInstance();

        //Init Preference Values for Navigation Drawer
        Common.getUserPreference(mContext);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar_dash);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_dash);
        //drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        txtUserName = (TextView)header.findViewById(R.id.txt_user_name);
        txtUserEmail = (TextView)header.findViewById(R.id.txt_user_emailid);
        imgUserProfile = (CircleImageView)header.findViewById(R.id.user_profile_image) ;

        try {

            txtUserName.setText(Common.UserName);
            txtUserEmail.setText(Common.UserEmail);
            //txtUserEmail.setText(getUid(mContext));
            Picasso.with(mContext).load(Common.UserPhoto).into(imgUserProfile);

            imgUserProfile.setOnClickListener(this);
            txtUserName.setOnClickListener(this);
            txtUserEmail.setOnClickListener(this);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            startActivity(new Intent(mContext,DashboardActivityClient.class));


        } else if (id == R.id.nav_inbox) {

            startActivity(new Intent(mContext,InboxActivity.class));


        } else if (id == R.id.nav_notification) {


        } else if (id == R.id.nav_manage) {

            startActivity(new Intent(mContext,ManageJobPostActivity.class));


        } else if (id == R.id.nav_posting) {

            startActivity(new Intent(mContext,ClientJobPostingActivity.class));


        } else if (id == R.id.nav_contest) {

            startActivity(new Intent(mContext,ClientJobContestActivity.class));


        }else if (id == R.id.nav_settings) {

            startActivity(new Intent(mContext,SettingActivity.class));


        }else if (id == R.id.nav_support) {


        }
        else if (id == R.id.nav_logout) {
            onLogout();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onLogout() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage("Are you Sure Want to Logout")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        PreferenceUtils.setSignIn(mContext,false);
                        //Setting Shared Preference
                        PreferenceUtils.setDisplayName(mContext,"");
                        PreferenceUtils.setUid(mContext,"");
                        PreferenceUtils.setEmail(mContext,"");
                        PreferenceUtils.setPhotoUrl(mContext,"");
                        PreferenceUtils.setProvider(mContext,"");
                        PreferenceUtils.setPhoneNumber(mContext,"");
                        PreferenceUtils.setLid(mContext,"");
                        PreferenceUtils.setCid(mContext,"");

                        //FirebaseAuth LogOut
                        mAuth.signOut();

                        Intent signin = new Intent(mContext,SelectionActivity.class);
                        signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(signin);
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
       // actionBarDrawerToggle.syncState();
    }

    @Override
    public void finish() {
        super.finish();
      //  overridePendingTransitionExit();
    }

   /* @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    *//**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     *//*
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.fui_slide_in_right, R.anim.fui_slide_out_left);
    }

    *//**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     *//*
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.fui_slide_out_left, R.anim.fui_slide_in_right);
    }*/

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        if(v == txtUserName || v == txtUserEmail || v == imgUserProfile){
            startActivity(new Intent(mContext,ProfileActivity.class));
        }
    }
}