package com.pasistence.knockwork.Freelancer.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.pasistence.knockwork.Adapter.FreeLancerExperienceAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Model.ApiResponse.ApiExperienceResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseUpdateLancer;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Compiler.disable;

public class FreelancerWorkExperienceFragment extends Fragment {

    private static final String TAG = "workexp";
    public FButton btnSubmit,btnAdd;
    public Spinner spnYears,spnMonths;
    public EditText edtCompanyName,edtProfile;
    public TextView txtWorkfrom,txtWorkto;
    public CheckBox chkExperience;
    MyApi mService;
    ListView lstExperience;
    ArrayList<ApiExperienceResponse.LancerExperience> lancerExperiences ;
    FreeLancerExperienceAdapter adapter;

    private int mYear, mMonth, mDay;
    String Uid,Lid,companyname,profile,startdate,endate;


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

        if(!chkExperience.isChecked()){
            disableComponent();
        }

        chkExperience.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    enableComponent();
                }else if(!isChecked){
                    disableComponent();
                }
            }
        });


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
                    if(Common.isConnectedToInterNet(getContext())){

                        companyname = edtCompanyName.getText().toString();
                        profile = edtProfile.getText().toString();
                        startdate = txtWorkfrom.getText().toString();
                        endate = txtWorkto.getText().toString();

                        try{
                            mService.LancerProfileExperience(
                                    Uid,
                                    Lid,
                                    companyname,
                                    profile,
                                    startdate,
                                    endate)
                                    .enqueue(new Callback<ApiExperienceResponse>() {
                                        @Override
                                        public void onResponse(Call<ApiExperienceResponse> call, Response<ApiExperienceResponse> response) {

                                            ApiExperienceResponse result = response.body();
                                            Log.e(TAG, result.toString() );
                                            if(!result.getError()){
                                                //Add Adapter
                                                ArrayList<ApiExperienceResponse.LancerExperience> list = result.getLancerExperience();
                                                adapter = new FreeLancerExperienceAdapter(getContext(),list);
                                                lstExperience.setVisibility(View.VISIBLE);
                                                lstExperience.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();

                                            }else if(result.getError()){
                                                Log.e(TAG, result.getMessage());
                                                Common.commonDialog(getContext(),"Something went Wrong please try after sometime");
                                            }else {
                                                Common.commonDialog(getContext(),"Server Not Found!");
                                            }

                                        }
                                        @Override
                                        public void onFailure(Call<ApiExperienceResponse> call, Throwable t) {
                                            Log.e(TAG, t.getMessage());
                                            t.printStackTrace();
                                            Common.commonDialog(getContext(),"Server Not Found!");
                                        }
                                    });

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                            Log.e(TAG, e.getMessage());
                        }



                    }else {
                        Common.commonDialog(getContext(),"Please Check Your Internet Connection!");
                    }
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


        if((Uid.equals(null)||Uid.equals(""))&&(Lid.equals(null)||Lid.equals(""))){
            Common.commonDialog(getContext(),"You Need to Complete your Registration First");
            cancel=true;
        }
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

        chkExperience = (CheckBox)view.findViewById(R.id.chk_haveExperience);


        lstExperience = (ListView)view.findViewById(R.id.list_Experience);

        disableComponent();

        mService = Common.getApi();

        Uid = PreferenceUtils.getUid(getContext());
        Lid = PreferenceUtils.getLid(getContext());

    }

    private void disableComponent() {

        btnAdd.setEnabled(false);
        edtCompanyName.setEnabled(false);
        edtProfile.setEnabled(false);
        spnMonths.setEnabled(false);
        spnYears.setEnabled(false);
        txtWorkfrom.setEnabled(false);
        txtWorkto.setEnabled(false);
    }

    private void enableComponent() {

        btnAdd.setEnabled(true);
        edtCompanyName.setEnabled(true);
        edtProfile.setEnabled(true);
        spnMonths.setEnabled(true);
        spnYears.setEnabled(true);
        txtWorkfrom.setEnabled(true);
        txtWorkto.setEnabled(true);
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
