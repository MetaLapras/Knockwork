package com.pasistence.knockwork.Freelancer.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.pasistence.knockwork.R;

import org.w3c.dom.Text;

import java.util.Calendar;

import info.hoang8f.widget.FButton;

public class FreelancerWorkExperienceFragment extends Fragment {

    public FButton btnSubmit,btnAdd;
    public Spinner spnYears,spnMonths;
    public EditText edtCompanyName,edtProfile;
    public TextView txtWorkfrom,txtWorkto;

    private int mYear, mMonth, mDay;

    public FreelancerWorkExperienceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_freelancer_work_experience, container, false);

        mInit(view);


        txtWorkto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog(txtWorkto);
            }
        });

        txtWorkfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog(txtWorkfrom);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check()){

                }
            }
        });




        return view;
    }

    private boolean check() {
        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(edtProfile.getText())){
            edtProfile.setError("Please enter Degree * ");
            focusView=edtProfile;
            cancel=true;
        }
        if (TextUtils.isEmpty(edtCompanyName.getText())){
            edtCompanyName.setError("Please enter University * ");
            focusView=edtCompanyName;
            cancel=true;
        }

        if (txtWorkfrom.getText().equals(getResources().getString(R.string.dd_mm_yy))||txtWorkfrom.getText().equals("")){
            txtWorkfrom.setError("Please enter Valid Percentage * ");
            focusView=txtWorkfrom;
            cancel=true;
        }

        if (txtWorkto.getText().equals(getResources().getString(R.string.dd_mm_yy))||txtWorkto.getText().equals("")){
            txtWorkto.setError("Please enter Passing Year ");
            focusView=txtWorkto;
            cancel=true;
        }
/*
        if (TextUtils.isEmpty(edtPhoneNo.getText())){
            edtPhoneNo.setError("Please enter Valid Mobile No* ");
            focusView=edtPhoneNo;
            cancel=true;
        }*/

       /* if(( Uid.equals(null)||Uid.equals(""))&&(Lid.equals(null)||Lid.equals(""))){
            Common.commonDialog(getContext(),"You Need to Complete your Registration First");
            cancel=true;
        }*/
        return cancel;
    }

    private void mInit(View view) {

        btnSubmit =(FButton)view.findViewById(R.id.btn_submit) ;
        //btnBack =(FButton)view.findViewById(R.id.btn_back) ;
        btnAdd =(FButton)view.findViewById(R.id.btn_addWorkExp) ;
        edtCompanyName = (EditText)view.findViewById(R.id.freelancer_profile_editcompanyName);
        edtProfile = (EditText)view.findViewById(R.id.freelancer_profile_editprofile);

        spnMonths =(Spinner)view.findViewById(R.id.freelancer_profile_spnmonths);
        spnYears =(Spinner)view.findViewById(R.id.freelancer_profile_spnyears);

        txtWorkfrom = (TextView)view.findViewById(R.id.freelancer_profile_workfrom);
        txtWorkto = (TextView)view.findViewById(R.id.freelancer_profile_workTo);

    }

    private void dateDialog(final TextView txt){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txt.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth );
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}
