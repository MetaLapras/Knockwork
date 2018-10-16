package com.pasistence.knockwork.Freelancer.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.pasistence.knockwork.R;

import java.util.Calendar;

public class SubmitProposalActivity extends AppCompatActivity {

    TextView txtDuration;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_proposal);
        txtDuration = (TextView)findViewById(R.id.edt_duration);

        txtDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog();
            }
        });
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
                        txtDuration.setText(dayOfMonth  + "/" + (monthOfYear + 1) + "/" + year );

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
