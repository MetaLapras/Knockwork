package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pasistence.knockwork.R;

public class JobPoastingActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtJobName,txtJobCategory,txtDescription,txtAdditionalProject,txtUpload,txtDetails,txtTypeOfProject,
            txtOneTime,txtOnGoing,txtNoType,txtAdd,txtEnterprices,txtSkills,txtVisiblity,txtehocansee,txtanyone
            ,txtonlyupwork,txtinvite,txtbudget,txtpayhour,txtweekly,txtmonthly,txteverydaymonthly,txteverydayweekly;
    EditText editJobName,editJobDescription,editjobSkills;
    Spinner spnJobCategory;
    ImageView imgUpload,imgAdd;
    View jobNamelayer,jobDescriptionlayer,jobDetailslayer;
    Button btnBtnJobNameNext,btndescriptionCancel,btndescriptionNext,btnpostajob;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_poasting);

        mInit();
        mOnClick();
    }
    private void mInit() {
        mContext              = JobPoastingActivity.this;
        txtJobName            = (TextView)findViewById(R.id.txt_jobName);
        txtJobCategory        = (TextView)findViewById(R.id.txt_jobCatrgory);
        txtDescription        = (TextView)findViewById(R.id.txt_description);
        txtAdditionalProject  = (TextView)findViewById(R.id.txt_additional_projects);
        txtUpload             = (TextView)findViewById(R.id.txt_upload);
        txtDetails            = (TextView)findViewById(R.id.txt_details);
        txtTypeOfProject      = (TextView)findViewById(R.id.txt_type_of_projects);
        txtOneTime            = (TextView)findViewById(R.id.txt_one_time_projects);
        txtOnGoing            = (TextView)findViewById(R.id.txt_ongoing_projects);
        txtNoType             = (TextView)findViewById(R.id.txt_no_type_projects);
        txtAdd                = (TextView)findViewById(R.id.txt_add);
        txtEnterprices        = (TextView)findViewById(R.id.txt_enterprice);
        txtSkills             = (TextView)findViewById(R.id.txt_skill_enterprice);
        txtVisiblity          = (TextView)findViewById(R.id.txt_visibility);
        txtehocansee          = (TextView)findViewById(R.id.txt_whocansee);
        txtanyone             = (TextView)findViewById(R.id.txt_visibalanyone);
        txtonlyupwork         = (TextView)findViewById(R.id.txt_upworkmember);
        txtinvite             = (TextView)findViewById(R.id.txt_invite);
        txtbudget             = (TextView)findViewById(R.id.txt_budget);
        txtpayhour            = (TextView)findViewById(R.id.txt_payhours);
        txtweekly             = (TextView)findViewById(R.id.txt_payweekly);
        txtmonthly            = (TextView)findViewById(R.id.txt_paymonthly);
        txteverydaymonthly    = (TextView)findViewById(R.id.txt_payeveryotherday);
        txteverydayweekly     = (TextView)findViewById(R.id.txt_payeveryotherweek);




        editJobName           = (EditText)findViewById(R.id.edit_jobName);
        editJobDescription    =(EditText)findViewById(R.id.edit_description);
        editjobSkills         = (EditText) findViewById(R.id.edit_addSkills);

        spnJobCategory        =(Spinner)findViewById(R.id.spinner_jobCategory);

        imgUpload             = (ImageView)findViewById(R.id.img_upload);
        imgAdd                = (ImageView)findViewById(R.id.img_add);

        btnpostajob           = (Button)findViewById(R.id.btn_post_job);
        btnBtnJobNameNext     = (Button)findViewById(R.id.btn_next);
        btndescriptionCancel  = (Button)findViewById(R.id.btn_details_cancel);
        btndescriptionNext    = (Button)findViewById(R.id.btn_details_next);

        jobNamelayer         =findViewById(R.id.layer1);
        jobDescriptionlayer  =findViewById(R.id.layer2);
        jobDetailslayer      =findViewById(R.id.layer3);


    }
    private void mOnClick() {
        btnpostajob.setOnClickListener(this);
        btnBtnJobNameNext.setOnClickListener(this);
        btndescriptionCancel.setOnClickListener(this);
        btndescriptionNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnBtnJobNameNext)
        {
            jobNamelayer.setVisibility(v.INVISIBLE);
            jobDescriptionlayer.setVisibility(v.VISIBLE);

        }

        if(v==btndescriptionNext)
        {
            jobDescriptionlayer.setVisibility(v.INVISIBLE);
            jobDetailslayer.setVisibility(v.VISIBLE);

        }
        if(v==btndescriptionCancel)
        {
            jobDescriptionlayer.setVisibility(v.INVISIBLE);
            jobNamelayer.setVisibility(v.VISIBLE);

        }

        if(v==btnpostajob)
        {
            Toast.makeText(mContext, "Your Praposal is Submited", Toast.LENGTH_SHORT).show();

        }

    }
}
