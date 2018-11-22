package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pasistence.knockwork.Freelancer.Activities.JobPoastingActivity;
import com.pasistence.knockwork.R;

public class SettingActivity extends ClientBaseActivity
        implements View.OnClickListener{


    Context mContext;
    TextView txtAboutus,txtVersion,txtTermsServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_setting, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(7).setChecked(true);

        mInit();
        mOnclick();
    }

    private void mOnclick() {
        txtAboutus.setOnClickListener(this);
        txtTermsServices.setOnClickListener(this);
        txtVersion.setOnClickListener(this);
    }

    private void mInit() {
        mContext = SettingActivity.this;
        txtAboutus = (TextView)findViewById(R.id.txt_aboutUs);
        txtTermsServices = (TextView)findViewById(R.id.txt_terms_services);
        txtVersion = (TextView)findViewById(R.id.txt_version);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        if(v==txtAboutus){
            showDialogue("http://www.pasistence.com/about.html");
        }
        if(v==txtTermsServices){

            showDialogue("https://termsfeed.com/terms-conditions/25c27dad185714e543c322fa2e14381b");
        }
        if(v==txtVersion){

        }
    }

    private void showDialogue(String Url) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
       // alert.setTitle("Title here");


        WebView wv = new WebView(this);
        wv.loadUrl(Url);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }


}
