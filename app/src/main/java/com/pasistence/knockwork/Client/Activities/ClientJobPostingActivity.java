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
import com.pasistence.knockwork.LoginActivity;
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

    Context mContext;

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

        if (Common.isConnectedToInterNet(mContext))
        {
            try
            {
                allCategories();
                AllSubCategories();

            /*    mServices.getSubCategories().enqueue(new Callback<List<ResponseSubCategory>>() {
                    @Override
                    public void onResponse(Call<List<ResponseSubCategory>> call, Response<List<ResponseSubCategory>> response) {
                        List<ResponseSubCategory> list = response.body();
                        Log.e(TAG, response.body().toString());

                        if (list != null)
                        {
                            for(int i=0; i<list.size(); i++)
                            {
                                Skills.add(list.get(i).getSub_categories_title().toString());
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
                });*/

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
        MSkills.setValidator(new Validator());
        MSkills.setOnFocusChangeListener(new FocusListener());

    }

    private void allCategories() {
        mServices.getTopServices().enqueue(new Callback<List<ResponseTopService>>() {
            @Override
            public void onResponse(Call<List<ResponseTopService>> call, Response<List<ResponseTopService>> response) {
                List<ResponseTopService> list = response.body();
                Log.e(TAG, response.body().toString());

                if (list != null)
                {
                    List<String> labels = new ArrayList<String>();
                    labels.add("Select");
                    for(int i=0; i<list.size(); i++)
                    {
                        labels.add(list.get(i).getCategories_title().toString());
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,labels);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter1.notifyDataSetChanged();

                    spnDone.setAdapter(adapter1);


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
    }

    private void AllSubCategories() {
        mServices.getSubCategories().enqueue(new Callback<List<ResponseSubCategory>>() {
            @Override
            public void onResponse(Call<List<ResponseSubCategory>> call, Response<List<ResponseSubCategory>> response) {
                List<ResponseSubCategory> list = response.body();
                Log.e(TAG, response.body().toString());

                if (list != null)
                {
                    List<String> labels = new ArrayList<String>();
                    labels.add("Select");
                    for(int i=0; i<list.size(); i++)
                    {
                        Skills.add(list.get(i).getSub_categories_title().toString());
                        labels.add(list.get(i).getSub_categories_title().toString());
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,labels);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter1.notifyDataSetChanged();

                    spnBelong.setAdapter(adapter1);


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
        /*txtChkRecruiter =(TextView)findViewById(R.id.txt_chk_recuiter);
        txtChkNDA =(TextView)findViewById(R.id.txt_chk_nda);
        txtChkUrgent =(TextView)findViewById(R.id.txt_chk_urgent);
        txtChkPrivate =(TextView)findViewById(R.id.txt_chk_private);*/
        txtFeatured         =(TextView)findViewById(R.id.txt_feature);
       /* txtRecruiter =(TextView)findViewById(R.id.txt_recuiter);
        txtNDA =(TextView)findViewById(R.id.txt_nda);
        txtUrgent =(TextView)findViewById(R.id.txt_urgent);
        txtPrivate =(TextView)findViewById(R.id.txt_private);*/
        txtFINR            =(TextView)findViewById(R.id.txt_feature_INR);
       /* txtRINr =(TextView)findViewById(R.id.txt_recuiter_inr);
        txtNINR =(TextView)findViewById(R.id.txt_nda_inr);
        txtUINr =(TextView)findViewById(R.id.txt_uregent_inr);
        txtPINr =(TextView)findViewById(R.id.txt_private_inr);
*/


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
       /* chkRecruiter =(CheckBox)findViewById(R.id.chk_recruiter);
        chkNDA =(CheckBox)findViewById(R.id.chk_nda);
        chkUrgent =(CheckBox)findViewById(R.id.chk_urgent);
        chkPrivate =(CheckBox)findViewById(R.id.chk_private);*/
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


         /*   String input = MSkills.getText().toString().trim();
            String[] singleInputs = input.split("\\s*,\\s*");

            String toastText = "";

            //  for(int i = 0; i<singleInputs.length; i++)
            for(int i = 0; i<5; i++)
            {
                toastText += "Item "  + i + ": " + singleInputs[i] + "\n";
            }
            Toast.makeText(mContext, toastText, Toast.LENGTH_SHORT).show();*/
        }

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

       /* if (TextUtils.isEmpty(editINR.getText())){
            editINR.setError("Please enter min 5 Skills* ");
            focusView=editINR;
            cancel=true;
        }*/

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

        String input = MSkills.getText().toString().trim();
            String[] singleInputs = input.split("\\s*,\\s*");

            if(singleInputs.length<5){
                MSkills.setError("Please enter min 5 Skills* ");
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

    class Validator implements AutoCompleteTextView.Validator {

        @Override
        public boolean isValid(CharSequence text) {
            Log.v("Test", "Checking if valid: "+ text);
            if (Skills.size() > 0) {
                return true;
            }
            return false;
        }

        @Override
        public CharSequence fixText(CharSequence invalidText) {
            Log.v("Test", "Returning fixed text");

            /* I'm just returning an empty string here, so the field will be blanked,
             * but you could put any kind of action here, like popping up a dialog?
             *
             * Whatever value you return here must be in the list of valid words.
             */
            return "";
        }
    }

    class FocusListener implements View.OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            Log.v("Test", "Focus changed");
            if (v.getId() == R.id.edit_skil && !hasFocus) {
                Log.v("Test", "Performing validation");
                ((AutoCompleteTextView)v).performValidation();
            }
        }
    }
}
