package com.pasistence.knockwork.Freelancer.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.R;

import java.util.Calendar;

public class SubmitProposalActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "proposal";

    private int mYear, mMonth, mDay;
    Context mContext;
    ApiPostJobResponse.Result clientJobs ;
    TextView txtTitle,txtJobDuration,txtBudget,txtJobType;
    TextView txtDuration,txtDuration1,txtDuration2;
    Spinner spnMilstones;
    EditText edtMilestone,edtMilestone1,edtMilestone2,edtRupees,edtRupees1,edtRupees2;
    EditText edtCoverLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_proposal);

        mInit();
        mOnClick();

    }

    private void mOnClick() {
        txtDuration.setOnClickListener(this);
        txtDuration1.setOnClickListener(this);
        txtDuration2.setOnClickListener(this);
    }

    private void mInit() {

        mContext = SubmitProposalActivity.this;

        txtTitle = (TextView)findViewById(R.id.txt_job_title);
        txtJobDuration = (TextView)findViewById(R.id.txt_duration);
        txtBudget = (TextView)findViewById(R.id.txt_budget);
        txtJobType = (TextView)findViewById(R.id.txt_jobtype);

        txtDuration = (TextView)findViewById(R.id.edt_duration);
        txtDuration1 = (TextView)findViewById(R.id.edt_duration1);
        txtDuration2 = (TextView)findViewById(R.id.edt_duration2);

        spnMilstones = (Spinner)findViewById(R.id.spn_milestones);

        edtMilestone = (EditText)findViewById(R.id.edt_milstone);
        edtMilestone1 = (EditText)findViewById(R.id.edt_milstone1);
        edtMilestone2 = (EditText)findViewById(R.id.edt_milstone2);

        edtRupees = (EditText)findViewById(R.id.edt_price);
        edtRupees1 = (EditText)findViewById(R.id.edt_price1);
        edtRupees2 = (EditText)findViewById(R.id.edt_price2);

        edtCoverLetter = (EditText)findViewById(R.id.edt_coverLetter);

        if(getIntent().getStringExtra("proposal") != null){

            loadAllDetails();

        }else {
            Log.e(TAG, "Failed Intent" );
        }

    }

    private void loadAllDetails() {
        clientJobs = (ApiPostJobResponse.Result) getIntent().getSerializableExtra("jobs");
        Log.e(TAG+"-Intent", clientJobs.toString());

        txtTitle.setText(clientJobs.getTitle());
        txtJobDuration.setText(clientJobs.getDuration());
        txtBudget.setText(clientJobs.getRate());
        txtJobType.setText(clientJobs.getType());

    }

    @Override
    public void onClick(View v) {
        if(v == txtDuration){
            dateDialog(txtDuration);
        }
        if(v == txtDuration1){
            dateDialog(txtDuration1);
        }
        if(v == txtDuration2){
            dateDialog(txtDuration2);
        }
    }

    private void dateDialog(final TextView Duration){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        // edtdob.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        Duration.setText(dayOfMonth  + "/" + (monthOfYear + 1) + "/" + year );

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}
