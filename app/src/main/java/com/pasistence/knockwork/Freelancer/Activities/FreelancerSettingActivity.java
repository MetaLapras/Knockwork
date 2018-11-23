package com.pasistence.knockwork.Freelancer.Activities;

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
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pasistence.knockwork.R;

public class FreelancerSettingActivity extends FreeLancerBaseActivity
{
    public TextView txtPaymentSetting,txtManageCashAccount,txtTransactionFee,txtPaymentMethod,txtGeneral,txtNotification,txtAbout,txtAboutUs,txtTermsOfServices,txtVersion;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_freelancer_setting, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        //setContentView(R.layout.activity_freelancer_setting);
        mInit();
    }

    private void mInit() {
        mContext             = (FreelancerSettingActivity.this);
        txtPaymentSetting    = (TextView)findViewById(R.id.setting_payment);
        txtManageCashAccount = (TextView)findViewById(R.id.setting_manage_cash);
        txtTransactionFee    = (TextView)findViewById(R.id.setting_transaction);
        txtPaymentMethod     = (TextView)findViewById(R.id.setting_addEdit_payment);
        txtGeneral           = (TextView)findViewById(R.id.setting_general);
        txtNotification      = (TextView)findViewById(R.id.setting_notification);
        txtAbout             = (TextView)findViewById(R.id.setting_about);
        txtAboutUs           = (TextView)findViewById(R.id.setting_aboutus);
        txtTermsOfServices   = (TextView)findViewById(R.id.setting_termsofServices);
        txtVersion           = (TextView)findViewById(R.id.setting_version);
    }

}
