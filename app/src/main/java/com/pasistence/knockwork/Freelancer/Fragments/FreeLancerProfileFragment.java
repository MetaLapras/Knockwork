package com.pasistence.knockwork.Freelancer.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;
import com.google.android.gms.common.api.Status;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseUpdateLancer;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;

import dmax.dialog.SpotsDialog;
import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeLancerProfileFragment extends Fragment {

    private static final String TAG = "FragProfile";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public FButton btnSubmit;
    public EditText edtTitle,edtMinhour,edtSkills,edtSelfIntro;
    public Spinner spnAvability;
    public TextView txtContactNo,txtDateofBirth;
    RadioButton rdMale,rdFemale;
    RadioGroup rdgGender;
    ImageView imgCorrect,imgEdit;
    MaterialEditText edtPhoneNo;
    String Uid,Lid,title,minhrRate,avaliblity,contactno,dob,gender,skills,selfintro;
    MyApi mService;

    private int mYear, mMonth, mDay;


    public FreeLancerProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_free_lancer_profile, container, false);
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_free_lancer_profile, container, false);
        mInit(view);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /* if(!check()){


                   final android.app.AlertDialog watingDialog = new SpotsDialog(getActivity());
                   watingDialog.show();
                   watingDialog.setMessage("Please Wait");
                   watingDialog.setCancelable(false);
                   watingDialog.show();

                    title = edtTitle.getText().toString();
                    minhrRate = edtMinhour.getText().toString();
                    avaliblity = spnAvability.getSelectedItem().toString().trim();
                    contactno = txtContactNo.getText().toString();
                    dob = txtDateofBirth.getText().toString();
                    if(rdMale.isChecked())
                    {
                        gender = rdMale.getText().toString();
                    }
                    else if(rdFemale.isChecked())
                    {
                        gender = rdFemale.getText().toString();
                    }

                    skills = edtSkills.getText().toString();
                    selfintro = edtSelfIntro.getText().toString();


                    if(Common.isConnectedToInterNet(getContext())){

                        try{
                        mService.updateLancerProfile(
                                "abcd123456",
                                "6",
                                title,
                                avaliblity,
                                selfintro,
                                dob,
                                gender,
                                minhrRate,
                                skills,
                                contactno
                        ).enqueue(new Callback<ApiResponseUpdateLancer>() {
                            @Override
                            public void onResponse(Call<ApiResponseUpdateLancer> call, Response<ApiResponseUpdateLancer> response) {
                                ApiResponseUpdateLancer result = response.body();
                                Log.e(TAG, result.toString() );

                                if(!result.getError()){

                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.fragment_profile,new FreelanceEducationFragment());
                                    ft.addToBackStack(null);
                                    ft.commit();

                                }else if(result.getError()){
                                    Common.commonDialog(getContext(),result.getMessage());
                                }else
                                {
                                    Common.commonDialog(getContext(),"Server Not Found!");
                                }


                                watingDialog.dismiss();

                            }

                            @Override
                            public void onFailure(Call<ApiResponseUpdateLancer> call, Throwable t) {
                                Log.e(TAG, t.getMessage());
                                t.printStackTrace();
                                watingDialog.dismiss();

                            }
                        });

                        }catch (Exception e){
                            e.printStackTrace();
                            Log.e(TAG, e.getMessage() );
                            Common.commonDialog(getContext(),"Server Not Found!");
                        }

                    }else
                    {
                        Common.commonDialog(getContext(),"Please Check your Internet Connection");
                    }
               }*/

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_profile,new FreelanceEducationFragment());
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        txtDateofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog();
            }
        });

        return view;
    }

    private boolean check() {

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(edtTitle.getText())){
            edtTitle.setError("Please enter Title * ");
            focusView=edtTitle;
            cancel=true;
        }
        if (TextUtils.isEmpty(edtMinhour.getText())){
            edtMinhour.setError("Please enter Min Hour Rate * ");
            focusView=edtMinhour;
            cancel=true;
        }

        if (TextUtils.isEmpty(edtSelfIntro.getText())){
            edtSelfIntro.setError("Please enter 200 words descriptions * ");
            focusView=edtSelfIntro;
            cancel=true;
        }

        if (TextUtils.isEmpty(edtSkills.getText())){
            edtSkills.setError("Please enter Min 5 skills ");
            focusView=edtSkills;
            cancel=true;
        }
/*
        if (TextUtils.isEmpty(edtPhoneNo.getText())){
            edtPhoneNo.setError("Please enter Valid Mobile No* ");
            focusView=edtPhoneNo;
            cancel=true;
        }*/

        if (txtContactNo.getText().equals("+91-Mobile Number")||txtContactNo.getText().length()<10){
            txtContactNo.setError("Please enter Valid Mobile No* ");
            focusView=txtContactNo;
            cancel=true;
        }

        if (txtDateofBirth.getText().equals(getResources().getString(R.string.dd_mm_yy))||txtDateofBirth.getText().length()<0){
            txtDateofBirth.setError("Please enter Valid Mobile No* ");
            focusView=txtDateofBirth;
            cancel=true;
        }
/*
        if(( Uid.equals(null)||Uid.equals(""))&&(Lid.equals(null)||Lid.equals(""))){
            Common.commonDialog(getContext(),"You Need to Complete your Registration First");
            cancel=true;
        }*/

        return cancel;
    }

    private void showAlertDialog() {

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("One More Step !...");
            alertDialog.setMessage("Enter Your Mobile Number");

            //EditText In Java
       /* final EditText edtAddress = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress); //Add edittext to Alert Dialog*/

            LayoutInflater layoutInflater = this.getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.custome_simple_edittext,null);

            edtPhoneNo = (MaterialEditText) view.findViewById(R.id.edt_Mobile_Number);
            alertDialog.setView(view);
            alertDialog.setIcon(R.drawable.ic_account_box_black_24dp);
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(edtPhoneNo.getText().length()<10){
                        Toast toast = Toast.makeText(getContext(),"Please Enter At least 10 Numbers", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }else {
                        txtContactNo.setText("+91 "+ edtPhoneNo.getText().toString());
                        imgCorrect.setVisibility(View.VISIBLE);
                    }

                }
            });
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
    }

    private void mInit(View view) {
        btnSubmit =(FButton)view.findViewById(R.id.freelancer_profile_btnSubmit) ;

        edtMinhour = (EditText)view.findViewById(R.id.freelancer_profile_edtrate);
        edtTitle = (EditText)view.findViewById(R.id.freelancer_profile_edittitle);

        edtSelfIntro = (EditText)view.findViewById(R.id.freelancer_profile_editselfintroduction);
        edtSkills = (EditText)view.findViewById(R.id.freelancer_profile_editSkills);

        spnAvability = (Spinner)view.findViewById(R.id.freelancer_profile_spn_avalibility);
        txtContactNo = (TextView)view.findViewById(R.id.freelancer_profile_MNumber);

        imgCorrect=(ImageView)view.findViewById(R.id.freelancer_profile_imgright);
        imgCorrect.setVisibility(View.GONE);
        imgEdit=(ImageView)view.findViewById(R.id.freelancer_profile_imgedit);

        txtDateofBirth = (TextView)view.findViewById(R.id.freelancer_profile_DOB);
        rdFemale =(RadioButton)view.findViewById(R.id.freelancer_profile_radiomale);
        rdMale=(RadioButton)view.findViewById(R.id.freelancer_profile_radioFemale) ;
        rdgGender =(RadioGroup)view.findViewById(R.id.rdg_group);

        mService = Common.getApi();


        Uid = PreferenceUtils.getUid(getContext());
        Lid = PreferenceUtils.getLid(getContext());
    }


    private void dateDialog(){
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
                        txtDateofBirth.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth );
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


}
