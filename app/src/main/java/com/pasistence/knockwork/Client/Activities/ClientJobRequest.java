package com.pasistence.knockwork.Client.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiClientProposalsResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Model.MilestonesModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;


import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientJobRequest extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HireSection";
    CircleImageView circleImageView;
    TextView txt_name,txt_state,txtProfTitile,txt_price,txt_feedback,txt_description,
            txt_timeline,txt_amount,txt_amount_price,txtCoverletter,txtMilestone,txtmilestone1,txtmilestone2,
    txtprice,txtprice1,txtprice2,txtduration,txtduration1,txtduration2;
    FButton btnSubmitRequest;
    MaterialEditText edtstarttime,edtendtime;
    Context mContext;
    ApiResponseRegisterLancer.Lancer Lancer;
    ApiClientProposalsResponse proposal;
    MyApi mService;
    LinearLayout linearProposal,linearHire;

    private int mYear, mMonth, mDay;
    String jobId,lid,cid,pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_job_request);

        mInit();
        mOnClick();

    }

    private void mOnClick() {
        btnSubmitRequest.setOnClickListener(this);
        edtstarttime.setOnClickListener(this);
        edtendtime.setOnClickListener(this);
    }

    private void mInit() {

        mContext = ClientJobRequest.this;

        circleImageView = (CircleImageView)findViewById(R.id.circularImage_profile);

        txt_name=(TextView)findViewById(R.id.txt_lancer_name);
        txtProfTitile=(TextView)findViewById(R.id.txt_lancer_jobName);
        txt_state=(TextView)findViewById(R.id.txt_lancer_state);
        txt_price=(TextView)findViewById(R.id.txt_lancer_earing);
        txt_feedback=(TextView)findViewById(R.id.txt_lancer_per);
        txt_description=(TextView)findViewById(R.id.txt_description_lancer);
        txt_amount=(TextView)findViewById(R.id.txt_amount);
        txt_amount_price=(TextView)findViewById(R.id.txt_hiredamount);
        txt_timeline=(TextView)findViewById(R.id.txt_timeline);

        txtCoverletter=(TextView)findViewById(R.id.txt_coverletter);
        txtMilestone=(TextView)findViewById(R.id.edt_milstone);
        txtmilestone1=(TextView)findViewById(R.id.edt_milstone1);
        txtmilestone2=(TextView)findViewById(R.id.edt_milstone2);
        txtprice=(TextView)findViewById(R.id.edt_price);
        txtprice1=(TextView)findViewById(R.id.edt_price1);
        txtprice2=(TextView)findViewById(R.id.edt_price2);
        txtduration=(TextView)findViewById(R.id.edt_duration);
        txtduration1=(TextView)findViewById(R.id.edt_duration1);
        txtduration2=(TextView)findViewById(R.id.edt_duration2);

        linearProposal = (LinearLayout)findViewById(R.id.linear_proposal);
        linearHire = (LinearLayout)findViewById(R.id.linear_hire);


        edtstarttime = (MaterialEditText)findViewById(R.id.edt_start_time);
        edtendtime = (MaterialEditText)findViewById(R.id.edt_end_time);

        mService = Common.getApi();

        btnSubmitRequest = (FButton)findViewById(R.id.btn_submit_request);
            if(getIntent().getStringExtra(Common.PROPOSAL).equals(Common.PROPOSAL)){
                loadProposalDetails();
                linearHire.setVisibility(View.GONE);
                txt_description.setVisibility(View.GONE);
            }else {
                loadAllDetails();
                linearProposal.setVisibility(View.GONE);
            }
    }

    private void loadProposalDetails() {
        proposal = (ApiClientProposalsResponse) getIntent().getSerializableExtra("lancer");
        Log.e(TAG+"-Intent", proposal.toString());

        Picasso.with(mContext).load(proposal.getLProfileImage()).into(circleImageView);

        txtProfTitile.setText(proposal.getLProfessionalTitle());
        txt_name.setText(proposal.getLName());
        txt_state.setText(proposal.getLGender());
        txt_price.setText(proposal.getLMinhourrate());
        txt_feedback.setText("80%");
        txt_description.setText(proposal.getLSelfIntro());


        loadMilestones(proposal.getId());
        loadCoverLetter(proposal.getId());








    }

    @Override
    public void onClick(View v) {
        if (v == btnSubmitRequest)
        {
            agreedialog();
            Toast.makeText(mContext, "Submitted  Request", Toast.LENGTH_SHORT).show();
        }

        if (v == edtstarttime)
        {
            dateDialog(edtstarttime);
        }
        if (v == edtendtime)
        {
            dateDialog(edtendtime);
        }

    }

    public void agreedialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        // Set Custom Title
        TextView title = new TextView(this);
        // Title Properties
        title.setText("Custom Dialog Box");
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        alertDialog.setCustomTitle(title);

        // Set Message
        TextView msg = new TextView(this);
        // Message Properties
        msg.setText("I am a Custom Dialog Box. \n Please Customize me." +
                "" +
                "In this post we will create a Custom Dialog Box. My task is to open a Dialog Box on click of a Button. I have customized it according to my design.\n" +
                "\n" +
                "I will show you how we can customise ‘Title’, ‘Message’ and ‘Button’ of a Dialog.\n" +
                "\n" +
                "There are various ways of creating a custom dialog box but I found this approach easiest to implement and maintain.");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);

        // Set Button
        // you can more buttons
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"I AGREE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
            }
        });

       //new Dialog(getApplicationContext());
        alertDialog.show();

        // Set Properties for OK Button
        final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        okBT.setPadding(50, 10, 10, 10);   // Set Position
        okBT.setTextColor(Color.BLUE);
        okBT.setLayoutParams(neutralBtnLP);

        final Button cancelBT = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams negBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        negBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        cancelBT.setTextColor(Color.RED);
        cancelBT.setLayoutParams(negBtnLP);
    }

    public void dateDialog(final MaterialEditText editText){
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
                        //edtdob.setText(dayOfMonth  + "/" + (monthOfYear + 1) + "/" + year );
                        editText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth );

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void loadAllDetails() {

        Lancer = (ApiResponseRegisterLancer.Lancer) getIntent().getSerializableExtra("lancer");
        Log.e(TAG+"-Intent", Lancer.toString());


        Picasso.with(mContext).load(Lancer.getLancerImage()).into(circleImageView);

        txtProfTitile.setText(Lancer.getLancerProfessionalTitle());
        txt_name.setText(Lancer.getLancerName());
        txt_state.setText(Lancer.getLancerGender());
        txt_price.setText(Lancer.getLancerMinHourRate());
        txt_feedback.setText("80%");
        txt_description.setText(Lancer.getLancerSelfIntro());
        txt_amount.setText(Lancer.getLancerMinHourRate());

    }

    private void loadMilestones(String updatePid) {
        mService.getMilestones(updatePid).enqueue(new Callback<List<MilestonesModel.Detail>>() {
            @Override
            public void onResponse(Call<List<MilestonesModel.Detail>> call, Response<List<MilestonesModel.Detail>> response) {
                List<MilestonesModel.Detail> result = response.body();
                Log.e(TAG,result.toString());

                txtMilestone.setText(result.get(0).getMilestone());
                txtmilestone1.setText(result.get(1).getMilestone());
                txtmilestone2.setText(result.get(2).getMilestone());

                txtprice.setText(result.get(0).getPrice());
                txtprice1.setText(result.get(1).getPrice());
                txtprice2.setText(result.get(2).getPrice());

                txtduration.setText(result.get(0).getDuration());
                txtduration1.setText(result.get(1).getDuration());
                txtduration2.setText(result.get(2).getDuration());

          /*      milid = result.get(0).getId();
                milid1 = result.get(1).getId();
                milid2 = result.get(2).getId();*/

          int r,r1,r2;
              r = Integer.valueOf(result.get(0).getPrice());
              r1 = Integer.valueOf(result.get(1).getPrice());
              r2 = Integer.valueOf(result.get(2).getPrice());

              txt_amount_price.setText(String.valueOf(r+r1+r2));
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

                txtCoverletter.setText(result.get(0).getMilestone());

            }

            @Override
            public void onFailure(Call<List<MilestonesModel.Detail>> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG,t.getMessage());
            }
        });
    }
}
