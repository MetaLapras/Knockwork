package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.LoginActivity;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiSkillsResponse;
import com.pasistence.knockwork.Model.ResponseSubCategory;
import com.pasistence.knockwork.Model.ResponseTopService;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.CommonDataSource;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientJobPostingActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "JobPosting";
    TextView txtDone,txtBelong,txtAbout,txtExplain,txtSkill,txtChkFeatured,txtChkRecruiter,txtChkNDA,txtChkUrgent,txtChkPrivate, txtFeatured,txtRecruiter,txtNDA,txtUrgent,txtPrivate,txtFINR,txtRINr,txtNINR,txtUINr,txtPINr;
    EditText editAbout,editExplain,editSkill,editINR;
    Spinner spnDone,spnBelong,spnSpend;
    RadioButton radioFixed,radioHourly,radioPublic,radioPrivate;
    RadioGroup radioGrouptype,radioGroupvisibility;
    CheckBox checkBox,chkFeatured,chkRecruiter,chkNDA,chkUrgent,chkPrivate;
    Button btnSubmit;
    MultiAutoCompleteTextView MSkills;
    MyApi mServices;
    CardView cardFeatured;
    String check = Common.register;
    ApiPostJobResponse.Result clientJobs ;
    List<String> labelsBelong = new ArrayList<String>();
    List<String> labelsDone = new ArrayList<String>();

    Context mContext;
    String Uid,Cid,category,subcategory,title,details,skills,type,rate,duration,visibility,featured;

     ArrayList<String> Skills = new ArrayList<String>();
            /*{
                    "java","Android","JavaScript","Pythan","Objective C","Swift","Angular","Angular jS","Ajax","Jquery"
                    ,"C++","C","Wordpress","HTML","CSS","Web design","web Development","logo Design","Graphics"
            };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_job_posting);

        mInit();
        mOnClick();
        Bundle bu;
        bu = getIntent().getExtras();

        if(getIntent().getStringExtra("type")!=null){

            btnSubmit.setText("Update Job");
            check = getIntent().getStringExtra("type");
            loadAllDetails();

        }else {
            Log.e(TAG, "Failed Intent" );
        }

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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Skills);
       // ArrayAdapter<String> adapter = new ArrayAdapter<> (this,R.layout.cusome_simple_autocomplete_textview,Skills);
        MSkills.setAdapter(adapter);
        MSkills.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

    }

    private void loadAllDetails() {

        clientJobs = (ApiPostJobResponse.Result) getIntent().getSerializableExtra("Jobs");
        Log.e(TAG+"-Intent", clientJobs.toString());

      /*  getSpnDone(clientJobs.getCategory());
        getSpnBelong(clientJobs.getSubcategory());*/

       /* if(!clientJobs.getSubcategory().equals(null)){
            for(int i = 0; i<labelsBelong.size(); i++){
                if(labelsBelong.get(i).equals(clientJobs.getSubcategory())) {
                    spnBelong.setSelection(i+1);
                }
            }
        }

        if(!clientJobs.getCategory().equals(null)){
            for(int i = 0; i<labelsDone.size(); i++){
                if(labelsDone.get(i).equals(clientJobs.getCategory())) {
                    spnDone.setSelection(i+1);
                }
            }
        }*/


        getSpntill(clientJobs.getDuration());

        editAbout.setText(clientJobs.getTitle());
        editExplain.setText(clientJobs.getDetails());
        editSkill.setText(clientJobs.getSkills());

        if(clientJobs.getType().equals("Fixed"))
        {
            radioFixed.setChecked(true);

        }else if(clientJobs.getType().equals("Hourly")){

            radioHourly.setChecked(true);
        }

        if(clientJobs.getType().equals(radioPrivate.getText().toString()))
        {
            radioPrivate.setChecked(true);

        }else if(clientJobs.getType().equals(radioPublic.getText().toString())){
            radioPublic.setChecked(true);

        }

        editINR.setText(clientJobs.getRate());

        if(clientJobs.getFeatured().equals("yes")){
            chkFeatured.setChecked(true);
        }


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

                    spnDone.setAdapter(adapter1);


                    if(check.equals(Common.update)){
                          for(int i = 0; i<labelsDone.size(); i++){
                                if(labelsDone.get(i).equals(clientJobs.getCategory())) {
                                    spnDone.setSelection(i);
                                }
                            }
                    }


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
                    labelsBelong = new ArrayList<String>();
                    labelsBelong.add("Select");
                    for(int i=0; i<list.size(); i++)
                    {
                        Skills.add(list.get(i).getSub_categories_title().toString());
                        labelsBelong.add(list.get(i).getSub_categories_title().toString());
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,labelsBelong);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter1.notifyDataSetChanged();

                    spnBelong.setAdapter(adapter1);

                    if(check.equals(Common.update)){
                        for(int i = 0; i<labelsBelong.size(); i++){
                            if(labelsBelong.get(i).equals(clientJobs.getSubcategory())) {
                                spnBelong.setSelection(i);
                            }
                        }
                    }

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

    private void mOnClick() {
        btnSubmit.setOnClickListener(this);
    }

    private void mInit() {
        mContext            = ClientJobPostingActivity.this;
        txtDone             =(TextView)findViewById(R.id.txt_work);
        txtBelong           =(TextView)findViewById(R.id.txt_specility);
        txtAbout            =(TextView)findViewById(R.id.txt_project_about);
        txtExplain          =(TextView)findViewById(R.id.txt_explain);
        txtSkill            =(TextView)findViewById(R.id.txt_skil);
        txtChkFeatured      =(TextView)findViewById(R.id.txt_chk_feature);
        txtFeatured         =(TextView)findViewById(R.id.txt_feature);

        txtFINR            =(TextView)findViewById(R.id.txt_feature_INR);



        editAbout         = (EditText)findViewById(R.id.edt_project_about);
        editExplain       = (EditText)findViewById(R.id.edt_explain);
        editSkill         = (EditText)findViewById(R.id.edit_skil);
        editINR           = (EditText)findViewById(R.id.edit_inr);

        spnDone           = (Spinner)findViewById(R.id.spn_work);
        spnBelong         = (Spinner)findViewById(R.id.spn_specility);
        spnSpend          = (Spinner)findViewById(R.id.spn_spend);

        radioFixed        = (RadioButton)findViewById(R.id.radio_fixed);
        radioHourly       =(RadioButton)findViewById(R.id.radio_hourly);
        radioPublic       =(RadioButton)findViewById(R.id.radio_public);
        radioPrivate      =(RadioButton)findViewById(R.id.radio_private);

        radioGrouptype      =(RadioGroup) findViewById(R.id.radio_type);
        radioGroupvisibility      =(RadioGroup) findViewById(R.id.radio_vivibility);



        checkBox          =(CheckBox)findViewById(R.id.chk_agree);
        chkFeatured       =(CheckBox)findViewById(R.id.chk_feature);

        cardFeatured = (CardView)findViewById(R.id.card_featured) ;

        MSkills          = (MultiAutoCompleteTextView)findViewById(R.id.edit_skil);

        btnSubmit       = (Button)findViewById(R.id.btn_submit_project);


      /*  Skills.add( "java");
        Skills.add( "Android");
        Skills.add( "Wordpress");
        Skills.add( "Pythan");
        Skills.add( "Objective C");
        Skills.add( "Swift");
        Skills.add( "Angular");
        Skills.add( "C++");*/

        //init retrofit
        mServices = Common.getApi();


    }

    @Override
    public void onClick(View v) {
        if (!isCheck())
        {
            if(check.equals(Common.update)) {
                updatePostJobServer();
                Log.e("-->","Update job");
            }else{
                postJobServer();
                Log.e("-->","RegisterJob");
            }
        }

    }

    private void updatePostJobServer() {

        Common.showSpotDialogue(mContext);

        //Uid = PreferenceUtils.getUid(mContext);
        //Cid = PreferenceUtils.getCid(mContext);
        category = spnDone.getSelectedItem().toString().trim();
        subcategory = spnBelong.getSelectedItem().toString().trim();

        title = editAbout.getText().toString();
        details = editExplain.getText().toString();

        skills  = getSkillValues();
        if(radioFixed.isChecked()){
            type = radioFixed.getText().toString();
        }else if(radioHourly.isChecked()) {
            type = radioHourly.getText().toString();
        }

        if(radioPrivate.isChecked()){
            visibility = radioPrivate.getText().toString();
        }else if(radioPublic.isChecked()){
            visibility = radioPublic.getText().toString();
        }
        rate = editINR.getText().toString();
        duration = spnSpend.getSelectedItem().toString().trim();
        if(chkFeatured.isChecked()){
            featured = "yes";
        }
        try
        {
            mServices.ClientPostAJobUpdate(
                    clientJobs.getPId(),
                    clientJobs.getUid(),
                    clientJobs.getCid(),
                    category,
                    subcategory,
                    title,
                    details,
                    skills,
                    type,
                    rate,
                    duration,
                    visibility,
                    featured).enqueue(new Callback<ApiPostJobResponse>() {
                @Override
                public void onResponse(Call<ApiPostJobResponse> call, Response<ApiPostJobResponse> response) {
                    ApiPostJobResponse result = response.body();
                    Log.e(TAG, result.toString());
                    if(!result.getError()){
                        Common.dismissSpotDilogue();
                    }else if(result.getError()){
                        Common.commonDialog(mContext,result.getMessage());
                        Common.dismissSpotDilogue();
                    }else {
                        Common.commonDialog(mContext,"Sever not found..");
                        Common.dismissSpotDilogue();
                    }
                }

                @Override
                public void onFailure(Call<ApiPostJobResponse> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                    t.printStackTrace();
                    Common.dismissSpotDilogue();
                    Common.commonDialog(mContext,t.getMessage());
                }
            });
        }catch (Exception e){
            Log.e(TAG, e.getMessage() );
            e.printStackTrace();
            Common.commonDialog(mContext,"Sever not found..");
        }


    }

    private void postJobServer() {

        Common.showSpotDialogue(mContext);

        //Uid = PreferenceUtils.getUid(mContext);
        //Cid = PreferenceUtils.getCid(mContext);
        category = spnDone.getSelectedItem().toString().trim();
        subcategory = spnBelong.getSelectedItem().toString().trim();
        title = editAbout.getText().toString();
        details = editExplain.getText().toString();
        skills  = getSkillValues();
        if(radioFixed.isChecked()){
            type = radioFixed.getText().toString();
        }else {
            type = radioHourly.getText().toString();
        }

        if(radioPrivate.isChecked()){
            visibility = radioPrivate.getText().toString();
        }else {
            visibility = radioPublic.getText().toString();
        }
        rate = editINR.getText().toString();
        duration = spnSpend.getSelectedItem().toString().trim();
        if(chkFeatured.isChecked()){
            featured = "yes";
        }
        try
        {
            mServices.ClientPostAJob(
                    "EXSYrhgNDZPrRRyRgZPvy3agVJR2",
                    "44",
                    category,
                    subcategory,
                    title,
                    details,
                    skills,
                    type,
                    rate,
                    duration,
                    visibility,
                    featured).enqueue(new Callback<ApiPostJobResponse>() {
                @Override
                public void onResponse(Call<ApiPostJobResponse> call, Response<ApiPostJobResponse> response) {
                    ApiPostJobResponse result = response.body();
                    Log.e(TAG, result.toString());
                    if(!result.getError()){
                        Common.dismissSpotDilogue();
                    }else if(result.getError()){
                        Common.commonDialog(mContext,result.getMessage());
                        Common.dismissSpotDilogue();
                    }else {
                        Common.commonDialog(mContext,"Sever not found..");
                        Common.dismissSpotDilogue();
                    }
                }

                @Override
                public void onFailure(Call<ApiPostJobResponse> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                    t.printStackTrace();
                    Common.dismissSpotDilogue();
                    Common.commonDialog(mContext,t.getMessage());
                }
            });
        }catch (Exception e){
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

    private boolean isCheck() {
        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(editAbout.getText())){
            editAbout.setError("Please enter Project Title * ");
            focusView=editAbout;
            cancel=true;
        }
        if (TextUtils.isEmpty(editExplain.getText())){
            editExplain.setError("Please enter Project Description * ");
            focusView=editExplain;
            cancel=true;
        }

        if (TextUtils.isEmpty(MSkills.getText())){
            MSkills.setError("Please enter min 5 Skills* ");
            focusView=MSkills;
            cancel=true;
        }

        if (TextUtils.isEmpty(editINR.getText())){
            editINR.setError("Please enter min 5 Skills* ");
            focusView=editINR;
            cancel=true;
        }

        if(spnDone.getSelectedItemPosition() == 0)
        {
            ((TextView)spnDone.getSelectedView()).setError("please select category");
            focusView=spnDone;
            cancel=true;
        }

        if(spnBelong.getSelectedItemPosition() == 0)
        {
            ((TextView)spnBelong.getSelectedView()).setError("please select category");
            focusView=spnBelong;
            cancel=true;
        }

       /* if (!radioFixed.isChecked() && !radioHourly.isChecked())
        {
            radioHourly.setError("select one ");
            cancel=true;
        }

        if (!radioPublic.isChecked() && !radioPrivate.isChecked())
        {
            radioPublic.setError("select one ");
            cancel=true;
        }*/
/*
        if(!chkFeatured.isChecked())
        {
            txtChkFeatured.setError("please check");
        }*/


        if(!checkBox.isChecked())
        {
            checkBox.setError("please confirm terms & Condition");
            cancel=true;
        }
      return cancel;
    }

    public void getSpnDone(String str) {

        List<String> lables = labelsDone;
        Log.e(TAG+"lab", labelsDone.toString() );
        Log.e(TAG+"lab2", lables.toString() );

       /* for(int i = 0; i<lables.size(); i++)
        {
            if(calendarArrayList.get(i).getCalendar_id().equals(calendarID)) {
                spnCalendar.setSelection(i+1);
            }
        }*/
    }

    public void getSpnBelong(String str){
        List<String> l = labelsBelong;
        for (int i=0; i<l.size();i++){
            if(l.get(i).toLowerCase().equals(str.toLowerCase())){
                spnBelong.setSelection(i+1);
            }
        }
    }

    public void getSpntill(String str) {
        List<String> l = Arrays.asList(getResources().getStringArray(R.array.spinner_week));
        for (int i=0; i<l.size();i++){
            if(l.get(i).toLowerCase().equals(str.toLowerCase())){
                spnSpend.setSelection(i+1);
            }
        }
    }

}
