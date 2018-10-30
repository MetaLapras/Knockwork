package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.pasistence.knockwork.R;

public class ClientJobPostingActivity extends AppCompatActivity {

    TextView txtDone,txtBelong,txtAbout,txtExplain,txtSkill,txtChkFeatured,txtChkRecruiter,txtChkNDA,txtChkUrgent,txtChkPrivate, txtFeatured,txtRecruiter,txtNDA,txtUrgent,txtPrivate,txtFINR,txtRINr,txtNINR,txtUINr,txtPINr;
    EditText editAbout,editExplain,editSkill,editINR;
    Spinner spnDone,spnBelong,spnSpend;
    RadioButton radioFixed,radioHourly,radioPublic,radioPrivate;
    CheckBox checkBox,chkFeatured,chkRecruiter,chkNDA,chkUrgent,chkPrivate;
    Button btnSubmit;

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_job_posting);

        mInit();
    }

    private void mInit() {
        mContext = ClientJobPostingActivity.this;
        txtDone =(TextView)findViewById(R.id.txt_work);
        txtBelong =(TextView)findViewById(R.id.txt_specility);
        txtAbout =(TextView)findViewById(R.id.txt_project_about);
        txtExplain =(TextView)findViewById(R.id.txt_explain);
        txtSkill =(TextView)findViewById(R.id.txt_skil);
        txtChkFeatured =(TextView)findViewById(R.id.txt_chk_feature);
        /*txtChkRecruiter =(TextView)findViewById(R.id.txt_chk_recuiter);
        txtChkNDA =(TextView)findViewById(R.id.txt_chk_nda);
        txtChkUrgent =(TextView)findViewById(R.id.txt_chk_urgent);
        txtChkPrivate =(TextView)findViewById(R.id.txt_chk_private);*/
        txtFeatured =(TextView)findViewById(R.id.txt_feature);
       /* txtRecruiter =(TextView)findViewById(R.id.txt_recuiter);
        txtNDA =(TextView)findViewById(R.id.txt_nda);
        txtUrgent =(TextView)findViewById(R.id.txt_urgent);
        txtPrivate =(TextView)findViewById(R.id.txt_private);*/
        txtFINR =(TextView)findViewById(R.id.txt_feature_INR);
       /* txtRINr =(TextView)findViewById(R.id.txt_recuiter_inr);
        txtNINR =(TextView)findViewById(R.id.txt_nda_inr);
        txtUINr =(TextView)findViewById(R.id.txt_uregent_inr);
        txtPINr =(TextView)findViewById(R.id.txt_private_inr);
*/


        editAbout = (EditText)findViewById(R.id.edt_project_about);
        editExplain = (EditText)findViewById(R.id.edt_explain);
        editSkill = (EditText)findViewById(R.id.edit_skil);
        editINR = (EditText)findViewById(R.id.edit_inr);

        spnDone = (Spinner)findViewById(R.id.spn_work);
        spnBelong = (Spinner)findViewById(R.id.spn_specility);
        spnSpend = (Spinner)findViewById(R.id.spn_spend);

        radioFixed = (RadioButton)findViewById(R.id.radio_fixed);
        radioHourly =(RadioButton)findViewById(R.id.radio_hourly);
        radioPublic =(RadioButton)findViewById(R.id.radio_public);
        radioPrivate =(RadioButton)findViewById(R.id.radio_private);

        checkBox =(CheckBox)findViewById(R.id.chk_agree);
        chkFeatured =(CheckBox)findViewById(R.id.chk_feature);
       /* chkRecruiter =(CheckBox)findViewById(R.id.chk_recruiter);
        chkNDA =(CheckBox)findViewById(R.id.chk_nda);
        chkUrgent =(CheckBox)findViewById(R.id.chk_urgent);
        chkPrivate =(CheckBox)findViewById(R.id.chk_private);*/

        btnSubmit = (Button)findViewById(R.id.btn_submit_project);

    }
}
