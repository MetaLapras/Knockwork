package com.pasistence.knockwork;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pasistence.knockwork.Client.Activities.DashboardActivity;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.SignUpEmailModel;
import com.rey.material.widget.LinearLayout;

import java.io.IOException;
import java.util.UUID;
import java.util.jar.Attributes;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "abc";
    Context mContext;
    //defining view objects
    EditText editTextEmail, editTextUserName, editTextNumber, EditTextRetypePassword;
    EditText editTextPassword;
    Button buttonSignup;
    private ProgressDialog progressDialog;
    CircleImageView imgProfile;
    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    //insert firebase
    FirebaseDatabase database;
    DatabaseReference signupemaildatabase;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        mInit();


    }

    private void mInit() {

        mContext = EmailActivity.this;
        //firebase databse
        database = FirebaseDatabase.getInstance();
        signupemaildatabase = database.getReference("Users");

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();
        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        EditTextRetypePassword = (EditText) findViewById(R.id.editTextRePassword);
        editTextUserName = (EditText) findViewById(R.id.editTextusername);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        imgProfile = (CircleImageView) findViewById(R.id.img_profile);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        progressDialog = new ProgressDialog(this);
        //attaching listener to button
        buttonSignup.setOnClickListener(this);
        imgProfile.setOnClickListener(this);
    }

    private boolean registerUser() {

        if (checkuser())
        {
            Task<AuthResult> mainTask =firebaseAuth.createUserWithEmailAndPassword(editTextEmail.getText().toString().trim(), editTextPassword.getText().toString().trim());
              mainTask.continueWith(new Continuation<AuthResult, Object>() {
                        @Override
                        public Object then(@NonNull Task<AuthResult> task) throws Exception {
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(editTextUserName.getText().toString())
                                    .setPhotoUri(filePath)
                                    .build();
                            return task.getResult().getUser().updateProfile(profileUpdates);
                            //return null;
                        }
                    });
              mainTask.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {
                                //display some message here
                                Toast.makeText(EmailActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                            } else {
                                //display some message here
                                Toast.makeText(EmailActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                            }
                            //progressDialog.dismiss();
                        }
                    });
            return true;
        }

        else
        {
            return false;
        }




        //if the email and password are not empty
        //displaying a progress dialog

        //String email = Email.trim();
        //String password =word.trim();


        //creating a new user




        //getting email and password from edit texts
       /* String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return false;
        }*/

    }

    private boolean checkuser() {
        String Name = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        if (Name.length() == 0)
        {
            editTextUserName.requestFocus();
            editTextUserName.setError("FIELD CANNOT BE EMPTY");
            return false;
        } else if (!Name.matches("[a-zA-Z ]+")) {
            editTextUserName.requestFocus();
            editTextUserName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        }
        if (email.length() == 0)
        {
            editTextEmail.requestFocus();
            editTextEmail.setError("FIELD CANNOT BE EMPTY");
            return false;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            editTextEmail.requestFocus();
            editTextEmail.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        }
        else if (password.length() == 0) {
            editTextPassword.requestFocus();
            editTextPassword.setError("FIELD CANNOT BE EMPTY");
            return false;
        } else {
            return true;
            //Toast.makeText(EmailActivity.this, "Validation Successful", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        //calling register method on click
       // registerUser();

        if (view == buttonSignup) {

            if(Common.isConnectedToInterNet(mContext)){

            if (registerUser()) {
                signupnow();
                uploadFile();

                progressDialog.setMessage("Registering Please Wait...");
                progressDialog.show();


                Intent mIntent = new Intent(
                        EmailActivity.this,
                        DashboardActivity.class);
                startActivity(mIntent);
                progressDialog.dismiss();

            }

            else {
                Toast.makeText(this, "something is missing", Toast.LENGTH_SHORT).show();
            }

            }else {
                Common.commonDialog(mContext,"Please Check Your Internet Connection !");
                Common.showDialog();
            }


            /*
             final SignUpEmailModel signUpEmailModel = new SignUpEmailModel(editTextUserName.getText().toString(),
                    editTextEmail.getText().toString(),
                    editTextNumber.getText().toString(),
                    editTextPassword.getText().toString(),
                    EditTextRetypePassword.getText().toString());

            signupemaildatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(signUpEmailModel.getUsername()).exists())
                        Toast.makeText(EmailActivity.this, "The Username is Alerdy Exist", Toast.LENGTH_SHORT).show();
                    else {
                        signupemaildatabase.child(signUpEmailModel.getUsername()).setValue(signUpEmailModel);
                        Toast.makeText(EmailActivity.this, "Success Register", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(EmailActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            });*/
            // registerUser();

            // startActivity(new Intent(EmailActivity.this,DashboardActivity.class));

        }

        if (view == imgProfile)

        {
            agreedialog();
           // startActivity(new Intent(EmailActivity.this,ImageUploadActivity.class));
        }
    }

    private void signupnow() {

        final SignUpEmailModel signUpEmailModel = new SignUpEmailModel(editTextUserName.getText().toString(),
                editTextEmail.getText().toString(),
                editTextNumber.getText().toString(),
                editTextPassword.getText().toString(),
                EditTextRetypePassword.getText().toString());

        signupemaildatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(signUpEmailModel.getUsername()).exists())
                    Toast.makeText(EmailActivity.this, "The Username is Alerdy Exist", Toast.LENGTH_SHORT).show();
                else {
                    signupemaildatabase.child(signUpEmailModel.getUsername()).setValue(signUpEmailModel);
                    Toast.makeText(EmailActivity.this, "Success Register", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EmailActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();

           // "imagess/"+
            // Create a reference to "mountains.jpg"
            StorageReference mountainsRef = storageRef.child("mountains.jpg");

            // Create a reference to 'images/mountains.jpg'
            StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");


            // While the file names are the same, the references point to different files
            mountainsRef.getName().equals(mountainImagesRef.getName());    // true
            mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false


            StorageReference ref = storageRef.child( UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }


    private void agreedialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        // Set Custom Title
        TextView title = new TextView(this);
        // Title Properties
        title.setText("Add Photo");
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        alertDialog.setCustomTitle(title);

        // Set Button
        // you can more buttons
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"Choose file", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
                showFileChooser();
            }
        });

       /* alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Upload", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
            }
        });*/

        //new Dialog(getApplicationContext());
        alertDialog.show();

        // Set Properties for OK Button
        final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        okBT.setPadding(50, 10, 10, 10);   // Set Position
        okBT.setTextColor(Color.BLUE);
        okBT.setLayoutParams(neutralBtnLP);

        final Button cancelBT = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams negBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        negBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        cancelBT.setTextColor(Color.RED);
        cancelBT.setLayoutParams(negBtnLP);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgProfile.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}