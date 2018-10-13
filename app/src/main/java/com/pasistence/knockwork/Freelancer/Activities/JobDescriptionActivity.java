package com.pasistence.knockwork.Freelancer.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pasistence.knockwork.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class JobDescriptionActivity extends AppCompatActivity {
    public CircleImageView CircularDurationImage,CircularBudgetImage,CircularJobTypeImage;
    public TextView txtname,txtpoasted,txtquotes,txtduration,txtDurationmonths,txtbudget,txtbudgetrange,txtbudgetjobtype,
            txtpoastedjob,txtdescription,txtdescription1,txtdescription2,txtdescription3,txtdescription4,txtrequirdskills,txtskill1,txtskill2,txtskill3,txtskill4;



    // RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);

        CircularDurationImage   = (CircleImageView)findViewById(R.id.img_duration);
        CircularBudgetImage     = (CircleImageView)findViewById(R.id.img_budget);
        CircularJobTypeImage    = (CircleImageView)findViewById(R.id.img_jobtype);
        txtname                 = (TextView)findViewById(R.id.txt_job_name);
        txtpoasted              = (TextView)findViewById(R.id.txt_job_posted);
        txtquotes               = (TextView)findViewById(R.id.txt_quotes);
        txtduration             = (TextView)findViewById(R.id.txt_dutation);
        txtDurationmonths       = (TextView)findViewById(R.id.txt_duration_range);
        txtbudget               = (TextView)findViewById(R.id.txt_budget);
        txtbudgetrange          = (TextView)findViewById(R.id.txt_budget_range);
        txtbudgetjobtype        = (TextView)findViewById(R.id.txt_job_type);
        txtpoastedjob           = (TextView)findViewById(R.id.txt_job_project_based);
        txtdescription          = (TextView)findViewById(R.id.txt_description);
        txtdescription1         = (TextView)findViewById(R.id.txt_description1);
        txtdescription2         = (TextView)findViewById(R.id.txt_description2);
        txtdescription3         = (TextView)findViewById(R.id.txt_description3);
        txtdescription4         = (TextView)findViewById(R.id.txt_description4);
        txtrequirdskills        = (TextView)findViewById(R.id.txt_required_skill);
        txtskill1               = (TextView)findViewById(R.id.txt_skill1);
        txtskill2               = (TextView)findViewById(R.id.txt_skill2);
        txtskill3               = (TextView)findViewById(R.id.txt_skill3);
        txtskill4               = (TextView)findViewById(R.id.txt_skill4);


    }
}

      /*  relativeLayout = (RelativeLayout)findViewById(R.id.job_description_freelancer);
        View child = getLayoutInflater().inflate(R.layout.description_freelancer_template, null);
        relativeLayout.addView(child);*/


