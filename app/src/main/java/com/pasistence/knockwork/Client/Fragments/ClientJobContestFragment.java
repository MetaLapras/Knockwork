package com.pasistence.knockwork.Client.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pasistence.knockwork.Client.Activities.ClientJobContestActivity;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Freelancer.Fragments.FreelancerWorkExperienceFragment;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostContestResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.rey.material.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.pasistence.knockwork.Common.PreferenceUtils.getCid;
import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientJobContestFragment extends Fragment {
    private static final String TAG ="contest" ;
    public TextView txtContestTitle,txtContestDescription,txtContestOptional,txtUpload;
    public EditText editContestTitle,editContestDesription,edtPrize;
    public Button btnContinue,btnUpload;
    public ImageView optionalImag;
    RadioButton radioPublic,radioPrivate;
    Spinner spnDuration,spnCurrency;
    MyApi mService;

    ApiPostContestResponse.Result Model;

    Context mContext;
    Activity mActivity;

    FirebaseDatabase fdatabase;
    FirebaseStorage fstorage;
    Uri pdfUri;
    ProgressDialog progressDialog;
    public String Pid,Uid,Cid,Title,Description,duration,prizemoney,visibility,ModeofPayment,type;
    private String str1,str0;


    public ClientJobContestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_client_job_contest, container, false);
        mInit(view);

        fstorage = FirebaseStorage.getInstance();
        fdatabase = FirebaseDatabase.getInstance();



      /*  return inflater.inflate(R.layout.fragment_client_job_contest, container, false);*/
        return view;
    }

    private void mInit(View view) {

        mContext = getContext();
        mActivity = getActivity();

        txtContestTitle        = (TextView)view.findViewById(R.id.contest_title);
        txtContestDescription  = (TextView)view.findViewById(R.id.contest_description);
        txtContestOptional     = (TextView)view.findViewById(R.id.contest_optional);
        txtUpload              =(TextView)view.findViewById(R.id.txt_upload);

        editContestTitle       = (EditText)view.findViewById(R.id.edt_contest_title);
        editContestDesription  = (EditText)view.findViewById(R.id.edit_contest_description);

        btnContinue            = (Button)view.findViewById(R.id.contest_btnContinue);
        optionalImag           = (ImageView)view.findViewById(R.id.contest_img);
        btnUpload              = (Button)view.findViewById(R.id.btn_upload);

        radioPublic       =(RadioButton)view.findViewById(R.id.radio_public);
        radioPrivate      =(RadioButton)view.findViewById(R.id.radio_private);

        spnDuration          = (Spinner)view.findViewById(R.id.spn_duration);
        spnCurrency       = (Spinner)view.findViewById(R.id.spn_currency);
        edtPrize           = (EditText)view.findViewById(R.id.edit_inr);

        mService = Common.getApi();


        if(getArguments()!=null){

            type = getArguments().getString("type");

            if(type.equals(Common.update)) {
                loadAllData();
                btnContinue.setText("Update Contest");
                Log.e("-->","Update job");
            }


        }else {
            Log.e(TAG, "Failed Argu" );
        }


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isCheck()){

                    if(type.equals(Common.update)) {
                        updateAContest();
                        Log.e("-->","Update job");
                    }else{
                        createAContest();
                        Log.e("-->","RegisterJob");
                    }


                }

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contest_frame,new ClientJobContestSecondFragment());
                //ft.addToBackStack(null);
                ft.commit();
            }
        });

        txtContestOptional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                {
                   // selectPDF();
                    agreedialog();
                }else
                    ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);


            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pdfUri!=null)
                    uploadFile(pdfUri);
                else
                    Toast.makeText(getContext(), "Select a File", Toast.LENGTH_SHORT).show();
            }
        });

        Uid = getUid(mContext);
        Cid = getCid(mContext);






    }

    private void loadAllData() {

        if(getArguments()!=null){

            Pid = getArguments().getString("pid");
            Uid = getArguments().getString("uid");
            Cid = getArguments().getString("cid");
            Title = getArguments().getString("title");
            Description = getArguments().getString("description");
            duration = getArguments().getString("duration");
            prizemoney = getArguments().getString("prizemoney");
            ModeofPayment = getArguments().getString("mode");
            visibility = getArguments().getString("types");

            editContestTitle.setText(Title);
            editContestDesription.setText(Description);

            String[] arrOfStr = prizemoney.split("_", 5);

            for(int i =0 ;i<=arrOfStr.length;i++){
                str0 = arrOfStr[0];
                str1 = arrOfStr[1];
            }

            edtPrize.setText(str0);
            getCurrency(str1);
            getSpntill(duration);


            if(visibility.equals(radioPrivate.getText().toString()))
            {
                radioPrivate.setChecked(true);

            }else if(visibility.equals(radioPublic.getText().toString())){
                radioPublic.setChecked(true);
            }



        }



    }

    private void createAContest() {

        Common.showSpotDialogue(mActivity);

        Title = editContestTitle.getText().toString();
        Description =editContestDesription.getText().toString();
        prizemoney =edtPrize.getText().toString()+"_"+spnCurrency.getSelectedItem().toString();
        duration = spnDuration.getSelectedItem().toString();


        if(radioPrivate.isChecked()){
            visibility = radioPrivate.getText().toString();
        }else {
            visibility = radioPublic.getText().toString();
        }

        try{

            if(Common.isConnectedToInterNet(mContext)){

                mService.ClientPostContest(
                        Uid,
                        Cid,
                        Title,
                        Description,
                        duration,
                        prizemoney,
                        "Cash",
                        visibility)
                        .enqueue(new Callback<ApiPostContestResponse>() {
                            @Override
                            public void onResponse(Call<ApiPostContestResponse> call, Response<ApiPostContestResponse> response) {
                                ApiPostContestResponse result = response.body();
                                Log.e(TAG, result.toString());
                                Common.dismissSpotDilogue();
                                Common.commonDialog(mContext,result.getMessage().toString());
                            }

                            @Override
                            public void onFailure(Call<ApiPostContestResponse> call, Throwable t) {
                                t.printStackTrace();
                                Common.dismissSpotDilogue();
                                Common.commonDialog(mContext,"Server Not Found");
                            }
                        });

            }else {
                Common.commonDialog(mContext,"Please Check your Internet Connection");
            }



        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void updateAContest(){

        Common.showSpotDialogue(mActivity);


        Title = editContestTitle.getText().toString();
        Description =editContestDesription.getText().toString();
        prizemoney =edtPrize.getText().toString()+"_"+spnCurrency.getSelectedItem().toString();
        duration = spnDuration.getSelectedItem().toString();


        if(radioPrivate.isChecked()){
            visibility = radioPrivate.getText().toString();
        }else {
            visibility = radioPublic.getText().toString();
        }

        try{

            if(Common.isConnectedToInterNet(mContext)){

                mService.ClientPostContestUpdate(
                        Pid,
                        Uid,
                        Cid,
                        Title,
                        Description,
                        duration,
                        prizemoney,
                        ModeofPayment,
                        visibility).enqueue(new Callback<ApiPostContestResponse>() {
                    @Override
                    public void onResponse(Call<ApiPostContestResponse> call, Response<ApiPostContestResponse> response) {
                        ApiPostContestResponse result = response.body();
                        Log.e(TAG, result.toString());
                        Common.dismissSpotDilogue();
                        Common.commonDialog(mContext,result.getMessage().toString());
                    }

                    @Override
                    public void onFailure(Call<ApiPostContestResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


            }else {
                Common.commonDialog(mContext,"Please Check your Internet Connection");
            }



        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private boolean isCheck() {

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(editContestTitle.getText())){
            editContestTitle.setError("Please enter Degree * ");
            focusView=editContestTitle;
            cancel=true;
        }
        if (TextUtils.isEmpty(editContestDesription.getText())){
            editContestDesription.setError("Please enter University * ");
            focusView=editContestDesription;
            cancel=true;
        }

        if(( Uid.equals(null)||Uid.equals(""))&&(Cid.equals(null)||Cid.equals(""))){
            Common.commonDialog(getContext(),"You Need to Complete your Registration First");
            cancel=true;
        }

        return cancel;

    }

    //upload Spinner
    private void uploadFile(Uri pdfUri) {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("UPloading File...");
        progressDialog.setProgress(0);
        progressDialog.show();


        final String fileName = System.currentTimeMillis()+"";
        StorageReference storageReference = fstorage.getReference();
        storageReference.child("Upload").child(fileName).putFile(pdfUri)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                DatabaseReference reference = fdatabase.getReference("contestAttachment");
                reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Log.e(TAG, task.toString() );
                            Toast.makeText(getContext(), "File Successfully Uploaded..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "File not Successfully Uploaded..", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "File not Successfully Uploaded..", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
           selectPDF();
        }
        else
        Toast.makeText(getContext(), "please provide permission", Toast.LENGTH_SHORT).show();

    }

    private void selectPDF() {
        //to offer user to select a file using file manager
        //we will be using an Intent
        Intent intent = new Intent();
        intent.setType("application/pdf");
        //intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent,86);
    }
    private void selectImage() {
        //to offer user to select a file using file manager
        //we will be using an Intent
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent,86);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //check weather user has selected a file or not
        if(requestCode == 86 && resultCode ==RESULT_OK && data!=null)
        {
            pdfUri = data.getData();
            txtContestOptional.setText(" selected file : "+ data.getData().getLastPathSegment());
           // progressDialog.dismiss();
        }
        else
        {
            Toast.makeText(getContext(), "Please select a file", Toast.LENGTH_SHORT).show();
        }
    }

    private void agreedialog() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.custom_alertdialog_attachment, null);
        final FButton btnSelectPdf = (FButton) alertLayout.findViewById(R.id.btn_selectpdf);
        final FButton btnSelectImage = (FButton) alertLayout.findViewById(R.id.btn_selectimage);



        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
       // alert.setTitle("");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

       /* alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user = etUsername.getText().toString();
                String pass = etEmail.getText().toString();
                Toast.makeText(getBaseContext(), "Username: " + user + " Email: " + pass, Toast.LENGTH_SHORT).show();
            }
        });*/


        final AlertDialog dialog = alert.create();
        dialog.show();

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
                dialog.dismiss();
            }
        });

        btnSelectPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDF();
                dialog.dismiss();
            }
        });


    }

    public void getSpntill(String str) {
        List<String> l = Arrays.asList(getResources().getStringArray(R.array.spinner_week));
        for (int i=0; i<l.size();i++){
            if(l.get(i).toLowerCase().equals(str.toLowerCase())){
                spnDuration.setSelection(i+1);
            }
        }
    }

    public void getCurrency(String str) {



    List<String> l = Arrays.asList(getResources().getStringArray(R.array.spinner_week));
        for (int i=0; i<l.size();i++){
            if(l.get(i).toLowerCase().equals(str.toLowerCase())){
                spnCurrency.setSelection(i+1);
            }
        }
    }
}
