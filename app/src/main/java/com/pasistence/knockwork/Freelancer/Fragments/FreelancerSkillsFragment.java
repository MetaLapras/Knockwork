package com.pasistence.knockwork.Freelancer.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseUpdateLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiSkillsResponse;
import com.pasistence.knockwork.Model.ResponseSubCategory;
import com.pasistence.knockwork.Model.ResponseTopService;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreelancerSkillsFragment extends Fragment {

    private static final String TAG = "FragmentSkills";
    public FButton btnNext;
    public EditText edtSubCatgory,edtSelfIntro;
    public MultiAutoCompleteTextView MSkills;
    public Spinner spnCategory;

    ArrayList<Integer> mUserItem = new ArrayList<>();
    public boolean[] checkedItems;
    MyApi mServices;
    ArrayList<String> Skills = new ArrayList<String>();
    Context mContext;
    Activity mActivity;
    ArrayList<String> labelsBelong = new ArrayList<String>();
    //String[] labelsBelong;
    List<String> labelsDone = new ArrayList<String>();
    String[] listItem ;
    String uid,lid,category,subcategory,skill,selfintro,
            avaliblity,
            title,
            dob,
            gender,
            minhrRate,
            contactno;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        final View view =  lf.inflate(R.layout.fragment_freelancer_skills, container, false);
        mInit(view);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check()){
                    insertSkillsDetails();
                    UpdateProfileDetails();
                }

            }
        });

        if (Common.isConnectedToInterNet(mContext))
        {
            try
            {
                //loadAllDetails();
                allCategories();
                AllSubCategories();
                allSkill();

            }catch (Exception e)
            {
                Log.e(TAG, e.getMessage() );
                e.printStackTrace();
                Common.commonDialog(mContext,"Sever not found..");
            }

        }else {
            Common.commonDialog(mContext,"Please Check Internet Connnection ");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity,android.R.layout.simple_list_item_1,Skills);
        // ArrayAdapter<String> adapter = new ArrayAdapter<> (this,R.layout.cusome_simple_autocomplete_textview,Skills);
        MSkills.setAdapter(adapter);
        MSkills.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        edtSubCatgory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSubCatgory.setEnabled(true);
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(mActivity);
                mBuilder.setTitle(" Specifically belongs to ");
                mBuilder.setMultiChoiceItems(listItem, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                      if(isChecked)
                      {
                          for(int i = 0; i <= mUserItem.size(); i++)
                          {
                              if (!mUserItem.contains(position))
                              {
                                  mUserItem.add(position);
                              }
                             /* else if (mUserItem.contains(position))
                              {
                                  Toast.makeText(mContext, "hii", Toast.LENGTH_SHORT).show();
                                  // mUserItem.remove(mUserItem.contains(position));
                                 // mUserItem.remove(mUserItem.get(position));
                                 // mUserItem.set(position);
                                  ((AlertDialog) dialog).getListView().setItemChecked(position, false);
                              }*/
                          }
                      }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   String item = "";
                   for(int i = 0; i<mUserItem.size(); i++)
                   {
                       item = item+listItem[mUserItem.get(i)];
                       if(i!=mUserItem.size() -1)
                       {
                           item = item + ", ";
                       }
                   }
                   edtSubCatgory.setText(item);
                    }
                });


                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     for(int i=0; i<checkedItems.length; i++)
                     {
                         checkedItems[i]= false;
                         mUserItem.clear();
                         edtSubCatgory.setText("");
                     }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        return view;
    }

    private boolean check() {

            boolean cancel = false;
            View focusView = null;

            if (TextUtils.isEmpty(edtSubCatgory.getText())){
                edtSubCatgory.setError("Please enter Title * ");
                focusView=edtSubCatgory;
                cancel=true;
            }
            if (TextUtils.isEmpty(edtSelfIntro.getText())){
                edtSelfIntro.setError("Please enter Min Hour Rate * ");
                focusView=edtSelfIntro;
                cancel=true;
            }


            if(( uid.equals(null)||uid.equals(""))&&(lid.equals(null)||lid.equals(""))){
                Common.commonDialog(getContext(),"You Need to Complete your Registration First");
                cancel=true;
            }

            return cancel;
        }

    private void mInit(View view) {
        mContext = getContext();
        mActivity = getActivity();
        mServices = Common.getApi();
        edtSubCatgory =(EditText)view.findViewById(R.id.freelancer_profile_edtSubcat);
        edtSelfIntro =(EditText)view.findViewById(R.id.freelancer_profile_editselfintroduction);
        MSkills =(MultiAutoCompleteTextView) view.findViewById(R.id.edit_skil);
        spnCategory =(Spinner) view.findViewById(R.id.spn_category);
        btnNext =(FButton)view.findViewById(R.id.freelancer_profile_btnNext);

        uid = PreferenceUtils.getUid(getContext());
        lid = PreferenceUtils.getLid(getContext());

    }

    private void allSkill() {

        try
        {
            mServices.getAllSkills().enqueue(new Callback<List<ApiSkillsResponse>>() {
                @Override
                public void onResponse(Call<List<ApiSkillsResponse>> call, Response<List<ApiSkillsResponse>> response) {
                    List<ApiSkillsResponse> list = response.body();
                    Log.e(TAG, response.body().toString());

                    if (list != null)
                    {
                        for(int i=0; i<list.size(); i++)
                        {
                            Skills.add(list.get(i).getName());
                        }

                    }else
                    {
                        Common.commonDialog(mContext,"Something went, wrong please try again");
                    }


                }
                @Override
                public void onFailure(Call<List<ApiSkillsResponse>> call, Throwable t) {
                    Log.e(TAG, t.getMessage() );
                    t.printStackTrace();
                    Common.commonDialog(mContext,"Something went, wrong please try again");
                }
            });
        }catch (Exception e)
        {
            Log.e(TAG, e.getMessage() );
            e.printStackTrace();
            Common.commonDialog(mContext,"Sever not found..");

        }

    }

    private void allCategories() {
        try
        {
            mServices.getTopServices().enqueue(new Callback<List<ResponseTopService>>() {
                @Override
                public void onResponse(Call<List<ResponseTopService>> call, Response<List<ResponseTopService>> response) {
                    List<ResponseTopService> list = response.body();
                    Log.e(TAG, response.body().toString());

                    if (list != null)
                    {
                        labelsDone = new ArrayList<String>();
                        labelsDone.add("Select");
                        for(int i=0; i<list.size(); i++)
                        {
                            labelsDone.add(list.get(i).getCategories_title());
                        }

                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,labelsDone);
                        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        adapter1.notifyDataSetChanged();

                        spnCategory.setAdapter(adapter1);


                        /*if(check.equals(Common.update)){
                            for(int i = 0; i<labelsDone.size(); i++){
                                if(labelsDone.get(i).equals(clientJobs.getCategory())) {
                                    spnDone.setSelection(i);
                                }
                            }
                        }
*/

                    }else
                    {
                        Common.commonDialog(mContext,"Something went, wrong please try again");
                    }


                }
                @Override
                public void onFailure(Call<List<ResponseTopService>> call, Throwable t) {
                    Log.e(TAG, t.getMessage() );
                    t.printStackTrace();
                    Common.commonDialog(mContext,"Something went, wrong please try again");
                }
            });
        }catch (Exception e)
        {
            Log.e(TAG, e.getMessage() );
            e.printStackTrace();
            Common.commonDialog(mContext,"Sever not found..");

        }
    }

    private void AllSubCategories() {
        try
        {
            mServices.getSubCategories().enqueue(new Callback<List<ResponseSubCategory>>() {
                @Override
                public void onResponse(Call<List<ResponseSubCategory>> call, Response<List<ResponseSubCategory>> response) {
                    List<ResponseSubCategory> list = response.body();
                    Log.e(TAG, response.body().toString());

                    if (list != null)
                    {
                        //labelsBelong = new ArrayList<String>();
                        //labelsBelong.add("Select");
                        for(int i=0; i<list.size(); i++)
                        {
                            //Skills.add(list.get(i).getSub_categories_title().toString());
                            labelsBelong.add(list.get(i).getSub_categories_title().toString());
                           // labelsBelong[i] = list.get(i).getSub_categories_title().toString();
                            listItem = new String[labelsBelong.size()];
                            listItem = labelsBelong.toArray(listItem);

                        }
                       /* ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,labelsBelong);
                        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        adapter1.notifyDataSetChanged();

                        sp.setAdapter(adapter1);*/
                       /* if(check.equals(Common.update)){
                            for(int i = 0; i<labelsBelong.size(); i++){
                                if(labelsBelong.get(i).equals(clientJobs.getSubcategory())) {
                                    spnBelong.setSelection(i);
                                }
                            }
                        }*/
                        checkedItems = new boolean[listItem.length];
                    }else
                    {
                        Common.commonDialog(mContext,"Something went, wrong please try again");
                    }


                }
                @Override
                public void onFailure(Call<List<ResponseSubCategory>> call, Throwable t) {
                    Log.e(TAG, t.getMessage() );
                    t.printStackTrace();
                    Common.commonDialog(mContext,"Something went, wrong please try again");
                }
            });
        }catch (Exception e)
        {
            Log.e(TAG, e.getMessage() );
            e.printStackTrace();
            Common.commonDialog(mContext,"Sever not found..");

        }
    }

    private String getSkillValues() {
        String input = MSkills.getText().toString().trim();
        String[] singleInputs = input.split("\\s*,\\s*");

        String toastText = "";

        //  for(int i = 0; i<singleInputs.length; i++)
        for(int i = 0; i<singleInputs.length; i++)
        {
            toastText += singleInputs[i] + ",";
        }
        return toastText;

    }

    public void insertSkillsDetails(){

        category = spnCategory.getSelectedItem().toString().trim();
        subcategory = edtSubCatgory.getText().toString();
        selfintro = edtSelfIntro.getText().toString();
        skill = MSkills.getText().toString();
        selfintro = edtSelfIntro.getText().toString();



        final android.app.AlertDialog watingDialog = new SpotsDialog(getActivity());
        watingDialog.show();
        watingDialog.setMessage("Please Wait");
        watingDialog.setCancelable(false);
        watingDialog.show();

        if(Common.isConnectedToInterNet(getContext())){

            try{
                mServices.ClientPostContestDelete(
                        uid,
                        lid,
                        category,
                        subcategory,
                        skill,
                        selfintro
                ).enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        ApiResponse result = response.body();
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
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
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
    }

    public void UpdateProfileDetails() {


        if(getArguments()!=null){
            title = getArguments().getString("title");
            avaliblity = getArguments().getString("avaiable");
            dob = getArguments().getString("dob");
            gender = getArguments().getString("gender");
            minhrRate = getArguments().getString("minhrrate");
            contactno = getArguments().getString("mobile");
        }

        final android.app.AlertDialog watingDialog = new SpotsDialog(getActivity());
        watingDialog.show();
        watingDialog.setMessage("Please Wait");
        watingDialog.setCancelable(false);
        watingDialog.show();

        if(Common.isConnectedToInterNet(getContext())){

            try{
                mServices.updateLancerProfile(
                                uid,
                                lid,
                                title,
                                avaliblity,
                                selfintro,
                                dob,
                                gender,
                                minhrRate,
                                skill,
                                contactno
                ).enqueue(new Callback<ApiResponseUpdateLancer>() {
                    @Override
                    public void onResponse(Call<ApiResponseUpdateLancer> call, Response<ApiResponseUpdateLancer> response) {
                        ApiResponseUpdateLancer result = response.body();
                        Log.e(TAG, result.toString() );

                        if(!result.getError()){
                            /*
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.fragment_profile,new FreelancerSkillsFragment());
                            ft.addToBackStack(null);
                            ft.commit();*/

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
    }


}
