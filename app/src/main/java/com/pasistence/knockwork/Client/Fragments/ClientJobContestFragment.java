package com.pasistence.knockwork.Client.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.pasistence.knockwork.Freelancer.Fragments.FreelancerWorkExperienceFragment;
import com.pasistence.knockwork.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientJobContestFragment extends Fragment {
    public TextView txtContestTitle,txtContestDescription,txtContestOptional,txtUpload;
    public EditText editContestTitle,editContestDesription;
    public Button btnContinue,btnUpload;
    public ImageView optionalImag;

    FirebaseDatabase fdatabase;
    FirebaseStorage fstorage;
    Uri pdfUri;
    ProgressDialog progressDialog;


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
        txtContestTitle        = (TextView)view.findViewById(R.id.contest_title);
        txtContestDescription  = (TextView)view.findViewById(R.id.contest_description);
        txtContestOptional     = (TextView)view.findViewById(R.id.contest_optional);
        txtUpload              =(TextView)view.findViewById(R.id.txt_upload);

        editContestTitle       = (EditText)view.findViewById(R.id.edt_contest_title);
        editContestDesription  = (EditText)view.findViewById(R.id.edit_contest_description);

        btnContinue            = (Button)view.findViewById(R.id.contest_btnContinue);
        optionalImag           = (ImageView)view.findViewById(R.id.contest_img);
        btnUpload              = (Button)view.findViewById(R.id.btn_upload);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contest_frame,new ClientJobContestSecondFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        txtContestOptional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                {
                    selectPDF();
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

    }

    private void uploadFile(Uri pdfUri) {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);
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

                DatabaseReference reference = fdatabase.getReference();
                reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(getContext(), "File Successfully Uploaded..", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getContext(), "File not Successfully Uploaded..", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
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
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //check weather user has selected a file or not
        if(requestCode == 86 && resultCode ==RESULT_OK && data!=null)
        {
            pdfUri = data.getData();
            txtUpload.setText("A file is selected : "+ data.getData().getLastPathSegment());
        }
        else
        {
            Toast.makeText(getContext(), "Please select a file", Toast.LENGTH_SHORT).show();
        }
    }
}
