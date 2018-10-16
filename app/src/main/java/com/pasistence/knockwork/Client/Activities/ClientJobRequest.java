package com.pasistence.knockwork.Client.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.pasistence.knockwork.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.app.Dialog;
import com.rey.material.widget.EditText;
import com.rey.material.widget.LinearLayout;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import info.hoang8f.widget.FButton;

public class ClientJobRequest extends AppCompatActivity implements View.OnClickListener {
    CircleImageView circleImageView;
    TextView txt_name,txt_state,txt_price,txt_feedback,txt_description,txt_timeline,txt_amount,txt_amount_price;
    FButton btnSubmitRequest;
    MaterialEditText edtstarttime,edtendtime;
    Context mContext;

    private int mYear, mMonth, mDay;

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
        txt_state=(TextView)findViewById(R.id.txt_lancer_state);
        txt_price=(TextView)findViewById(R.id.txt_lancer_earing);
        txt_feedback=(TextView)findViewById(R.id.txt_lancer_per);
        txt_description=(TextView)findViewById(R.id.txt_description_lancer);
        txt_amount=(TextView)findViewById(R.id.txt_amount);
        txt_amount_price=(TextView)findViewById(R.id.txt_hiredamount);
        txt_timeline=(TextView)findViewById(R.id.txt_timeline);


        edtstarttime = (MaterialEditText)findViewById(R.id.edt_start_time);
        edtendtime = (MaterialEditText)findViewById(R.id.edt_end_time);

        btnSubmitRequest = (FButton)findViewById(R.id.btn_submit_request);

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
            dateDialog();
        }
        if (v == edtendtime)
        {
            dateDialog();
        }

    }

    private void agreedialog() {

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

    private void dateDialog(){
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
                        edtstarttime.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth );

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
