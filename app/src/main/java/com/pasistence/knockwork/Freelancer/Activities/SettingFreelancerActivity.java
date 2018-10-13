package com.pasistence.knockwork.Freelancer.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pasistence.knockwork.R;

public class SettingFreelancerActivity extends AppCompatActivity {
    public TextView txtPaymentSetting,txtManageCashAccount,txtTransactionFee,txtPaymentMethod,txtGeneral,txtNotification,txtAbout,txtAboutUs,txtTermsOfServices,txtVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_freelancer);

        mInit();
    }

    private void mInit() {
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
