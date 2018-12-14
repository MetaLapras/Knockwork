package com.pasistence.knockwork.Freelancer.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiBidsResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiGetProposals;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiProposalResponse;
import com.pasistence.knockwork.Model.MilestonesModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pasistence.knockwork.Common.PreferenceUtils.getLid;
import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

public class SubmitProposalActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "proposal";

    private int mYear, mMonth, mDay;
    Context mContext;
    ApiPostJobResponse.Result clientJobs ;
    TextView txtTitle,txtJobDuration,txtBudget,txtJobType,txtTotal;
    TextView txtDuration,txtDuration1,txtDuration2;
    Spinner spnMilstones;
    EditText edtMilestone,edtMilestone1,edtMilestone2,edtRupees,edtRupees1,edtRupees2;
    EditText edtCoverLetter;
    String cid,pid,lid,jobid,milestone,status,milid,milid1,milid2,time,coverletter,Uid,updatePid;
    FButton btnSubmit,btnAttach;
    MyApi mService;
    List<MilestonesModel.Detail> lstMilestone = new ArrayList<MilestonesModel.Detail>();
    MilestonesModel  milestonesModel;
    ApiGetProposals proposals;
    int Total;
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
        btnAttach.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
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

        txtTotal = (TextView)findViewById(R.id.txt_total);

        edtCoverLetter = (EditText)findViewById(R.id.edt_coverLetter);

        btnSubmit = (FButton)findViewById(R.id.btn_submit_proposal);
        btnAttach = (FButton)findViewById(R.id.btn_attach_work);
        mService = Common.getApi();

        if(getIntent().getStringExtra("proposal") != null){

            loadAllDetails();

        }else {
            Log.e(TAG, "Failed Intent" );
        }

    }

    private void loadAllDetails() {
        status  = getIntent().getStringExtra("status");
        if(status.equals(Common.update)){

            proposals = (ApiGetProposals) getIntent().getSerializableExtra("jobs");
            Log.e(TAG+"-Intent", proposals.toString());

            cid = proposals.getCid();
            jobid = proposals.getJobid();
            lid = getLid(mContext);
            updatePid = proposals.getId();

            txtTitle.setText(proposals.getTitle());
            txtJobDuration.setText(proposals.getDuration());
            txtBudget.setText(proposals.getRate());
            txtJobType.setText(proposals.getType());

            loadMilestones(updatePid);
            loadCoverLetter(updatePid);

            btnSubmit.setText("Update Proposal");


        }else if(status.equals(Common.register)){

            clientJobs = (ApiPostJobResponse.Result) getIntent().getSerializableExtra("jobs");
            Log.e(TAG+"-Intent", clientJobs.toString());

            cid = clientJobs.getCid();
            jobid = clientJobs.getpId();
            lid = getLid(mContext);

            txtTitle.setText(clientJobs.getTitle());
            txtJobDuration.setText(clientJobs.getDuration());
            txtBudget.setText(clientJobs.getRate());
            txtJobType.setText(clientJobs.getType());
        }
    }

    private void loadMilestones(String updatePid) {
        mService.getMilestones(updatePid).enqueue(new Callback<List<MilestonesModel.Detail>>() {
            @Override
            public void onResponse(Call<List<MilestonesModel.Detail>> call, Response<List<MilestonesModel.Detail>> response) {
                List<MilestonesModel.Detail> result = response.body();
                Log.e(TAG,result.toString());

                    edtMilestone.setText(result.get(0).getMilestone());
                    edtMilestone1.setText(result.get(1).getMilestone());
                    edtMilestone2.setText(result.get(2).getMilestone());

                    edtRupees.setText(result.get(0).getPrice());
                    edtRupees1.setText(result.get(1).getPrice());
                    edtRupees2.setText(result.get(2).getPrice());

                    txtDuration.setText(result.get(0).getDuration());
                    txtDuration1.setText(result.get(1).getDuration());
                    txtDuration2.setText(result.get(2).getDuration());

                    milid = result.get(0).getId();
                    milid1 = result.get(1).getId();
                    milid2 = result.get(2).getId();

            }

            @Override
            public void onFailure(Call<List<MilestonesModel.Detail>> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG,t.getMessage());
            }
        });
    }

    private void loadCoverLetter(String updatePid) {
        mService.getCoverletter(updatePid).enqueue(new Callback<List<MilestonesModel.Detail>>() {
            @Override
            public void onResponse(Call<List<MilestonesModel.Detail>> call, Response<List<MilestonesModel.Detail>> response) {
                List<MilestonesModel.Detail> result = response.body();
                Log.e(TAG,result.toString());
                if(!result.isEmpty()){
                    edtCoverLetter.setText(result.get(0).getMilestone());
                }



            }

            @Override
            public void onFailure(Call<List<MilestonesModel.Detail>> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG,t.getMessage());
            }
        });
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
        if(v == btnSubmit){
            if(status.equals(Common.register)){
                submitProposal();
            }else if (status.equals(Common.update)){
                updateProposal();
            }
        }

    }

    private void updateProposal() {
        Common.showSpotDialogue(mContext);
        updateMilestone();
    }

    private void updateMilestone() {

        MilestonesModel.Detail detail = new MilestonesModel.Detail(milid,updatePid,edtMilestone.getText().toString(),edtRupees.getText().toString(),txtDuration.getText().toString());
        MilestonesModel.Detail detail1 = new MilestonesModel.Detail(milid1,updatePid,edtMilestone1.getText().toString(),edtRupees1.getText().toString(),txtDuration1.getText().toString());
        MilestonesModel.Detail detail2 = new MilestonesModel.Detail(milid2,updatePid,edtMilestone2.getText().toString(),edtRupees2.getText().toString(),txtDuration2.getText().toString());

        lstMilestone.add(detail);
        lstMilestone.add(detail1);
        lstMilestone.add(detail2);

        milestonesModel = new MilestonesModel(lstMilestone);
        coverletter = edtCoverLetter.getText().toString();

        mService.updateMilestones(milestonesModel).enqueue(new Callback<ApiProposalResponse>() {
            @Override
            public void onResponse(Call<ApiProposalResponse> call, Response<ApiProposalResponse> response) {
                ApiProposalResponse result = response.body();
                Log.e(TAG, result.toString());
                if(!result.getError()){

                    mService.updateCoverLetter(updatePid,coverletter).enqueue(new Callback<ApiProposalResponse>() {
                        @Override
                        public void onResponse(Call<ApiProposalResponse> call, Response<ApiProposalResponse> response) {
                            ApiProposalResponse result = response.body();
                            Log.e(TAG, result.toString());

                            if(!result.getError()){

                                reduceBids();

                            }else if(result.getError()) {
                                Common.commonDialog(mContext,result.getMessage());
                                Common.dismissSpotDilogue();
                            }

                        }

                        @Override
                        public void onFailure(Call<ApiProposalResponse> call, Throwable t) {
                            Log.e(TAG, t.getMessage() );
                            t.printStackTrace();
                            Common.dismissSpotDilogue();
                            Common.commonDialog(mContext,"Internal Server Error!");
                        }
                    });

                }else if(result.getError()) {
                    Common.commonDialog(mContext,result.getMessage());
                    Common.dismissSpotDilogue();
                }

            }

            @Override
            public void onFailure(Call<ApiProposalResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage() );
                t.printStackTrace();
                Common.dismissSpotDilogue();
            }
        });


    }

    private void submitProposal() {
        Common.showSpotDialogue(mContext);
        mService.addProposal(cid,lid,jobid).enqueue(new Callback<ApiProposalResponse>() {
            @Override
            public void onResponse(Call<ApiProposalResponse> call, Response<ApiProposalResponse> response) {
                ApiProposalResponse result = response.body();
                Log.e(TAG, result.toString());
                if(!result.getError()){
                    pid = result.getDetails().getId();
                    submitMilestones(pid);
                }else if(result.getError()) {
                    Common.commonDialog(mContext,result.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiProposalResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage() );
                t.printStackTrace();
                Common.commonDialog(mContext,"Internal Server Error!");
            }
        });

    }

    private void submitMilestones(final String pid) {

        MilestonesModel.Detail detail = new MilestonesModel.Detail(pid,edtMilestone.getText().toString(),edtRupees.getText().toString(),txtDuration.getText().toString());
        MilestonesModel.Detail detail1 = new MilestonesModel.Detail(pid,edtMilestone1.getText().toString(),edtRupees1.getText().toString(),txtDuration1.getText().toString());
        MilestonesModel.Detail detail2 = new MilestonesModel.Detail(pid,edtMilestone2.getText().toString(),edtRupees2.getText().toString(),txtDuration2.getText().toString());

        lstMilestone.add(detail);
        lstMilestone.add(detail1);
        lstMilestone.add(detail2);

        milestonesModel = new MilestonesModel(lstMilestone);
        coverletter = edtCoverLetter.getText().toString();

        mService.addMilestones(milestonesModel).enqueue(new Callback<ApiProposalResponse>() {
            @Override
            public void onResponse(Call<ApiProposalResponse> call, Response<ApiProposalResponse> response) {
                ApiProposalResponse result = response.body();
                Log.e(TAG, result.toString());
                if(!result.getError()){

                    mService.addCoverLetter(pid,coverletter).enqueue(new Callback<ApiProposalResponse>() {
                        @Override
                        public void onResponse(Call<ApiProposalResponse> call, Response<ApiProposalResponse> response) {
                            ApiProposalResponse result = response.body();
                            Log.e(TAG, result.toString());

                            if(!result.getError()){

                                reduceBids();

                            }else if(result.getError()) {
                                Common.commonDialog(mContext,result.getMessage());
                            }

                        }

                        @Override
                        public void onFailure(Call<ApiProposalResponse> call, Throwable t) {
                            Log.e(TAG, t.getMessage() );
                            t.printStackTrace();
                            Common.commonDialog(mContext,"Internal Server Error!");
                        }
                    });

                }else if(result.getError()) {
                    Common.commonDialog(mContext,result.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ApiProposalResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage() );
                t.printStackTrace();
            }
        });

    }

    private void reduceBids() {
        Uid = getUid(mContext);
        mService.DecreaseBids(Uid,lid,2).enqueue(new Callback<ApiBidsResponse>() {
            @Override
            public void onResponse(Call<ApiBidsResponse> call, Response<ApiBidsResponse> response) {
                Log.e(TAG, response.body().toString() );
                ApiBidsResponse result = response.body();
                if(!result.getError()){
                    //Common.commonDialog(mContext,"Bids Added Successfully");
                    Common.dismissSpotDilogue();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                    alertDialogBuilder.setMessage("Your Proposal Has Submitted Successfully!");
                    alertDialogBuilder.setNegativeButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    arg0.dismiss();
                                    startActivity(new Intent(mContext,FreeLancerDashboardActivity.class));
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }else {
                    Common.commonDialog(mContext,"Something Went Wrong! Please Try Again");
                }
            }

            @Override
            public void onFailure(Call<ApiBidsResponse> call, Throwable t) {
                t.printStackTrace();
                Common.dismissSpotDilogue();
                Common.commonDialog(mContext,"Server Not Found");
            }
        });
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
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {

                        // edtdob.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        //Duration.setText(dayOfMonth  + "/" + (monthOfYear + 1) + "/" + year );
                        Duration.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}
