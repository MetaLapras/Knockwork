package com.pasistence.knockwork.Freelancer.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.pasistence.knockwork.Adapter.FreeLancerEducationAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Model.ApiResponse.ApiEducationResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseUpdateLancer;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.ArrayList;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreelanceEducationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "expLancer";

    public EditText edtDegree,edtUniversity,edtPassingYear,edtPercentage;
    public FButton btnNext;
    public FButton btnBack,btnAdd;
    public ListView lstEducations;
    MyApi mService;
    FreeLancerEducationAdapter adapter;



    String Uid,Lid,degree,university,percentage,passingyear;


    public FreelanceEducationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_freelance_education, container, false);
        mInit(view);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check()){
                    if(Common.isConnectedToInterNet(getContext())){

                        degree = edtDegree.getText().toString();
                        university = edtUniversity.getText().toString();
                        percentage = edtPercentage.getText().toString();
                        passingyear = edtPassingYear.getText().toString();
                        try{
                            mService.LancerProfileEducation("abcd123458","7",degree,percentage,passingyear,university)
                                    .enqueue(new Callback<ApiEducationResponse>() {
                                        @Override
                                        public void onResponse(Call<ApiEducationResponse> call, Response<ApiEducationResponse> response) {
                                            ApiEducationResponse result = response.body();
                                            Log.e(TAG, result.toString());
                                            if(!result.getError()){
                                                //Add Item into Listview
                                                Log.e(TAG, result.getLancerEducation().toString());
                                                ArrayList<ApiEducationResponse.LancerEducation> list = result.getLancerEducation();
                                                lstEducations.setVisibility(View.VISIBLE);
                                                adapter = new FreeLancerEducationAdapter(getContext(),list);
                                                lstEducations.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();

                                            }else{

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ApiEducationResponse> call, Throwable t) {
                                            t.printStackTrace();

                                        }
                                    });


                        }catch (Exception e){
                            e.printStackTrace();
                            Log.e(TAG, e.getMessage() );
                        }


                    }else {
                        Common.commonDialog(getContext(),"Please Check Your Internet Connection!");
                    }
                }
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_profile,new FreelancerWorkExperienceFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

    private void mInit(View view) {

        edtDegree = (EditText)view.findViewById(R.id.edt_freelancer_profile_Degree);
        edtUniversity = (EditText)view.findViewById(R.id.edt_freelancer_profile_Univeristy);
        edtPercentage = (EditText)view.findViewById(R.id.edt_freelancer_profile_percentage);
        edtPassingYear = (EditText)view.findViewById(R.id.edt_freelancer_profile_yearofpassing);

        btnNext =(FButton)view.findViewById(R.id.btn_next) ;
       // btnBack =(FButton)view.findViewById(R.id.btn_back) ;
        btnAdd =(FButton)view.findViewById(R.id.btn_AddEducation) ;
        lstEducations = (ListView)view.findViewById(R.id.list_Educations);

        mService = Common.getApi();

        Uid = PreferenceUtils.getUid(getContext());
        Lid = PreferenceUtils.getLid(getContext());

    }

    private boolean check() {

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(edtDegree.getText())){
            edtDegree.setError("Please enter Degree * ");
            focusView=edtDegree;
            cancel=true;
        }
        if (TextUtils.isEmpty(edtUniversity.getText())){
            edtUniversity.setError("Please enter University * ");
            focusView=edtUniversity;
            cancel=true;
        }

        if (TextUtils.isEmpty(edtPercentage.getText())){
            edtPercentage.setError("Please enter Valid Percentage * ");
            focusView=edtPercentage;
            cancel=true;
        }

        if (TextUtils.isEmpty(edtPassingYear.getText())){
            edtPassingYear.setError("Please enter Passing Year ");
            focusView=edtPassingYear;
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

}
