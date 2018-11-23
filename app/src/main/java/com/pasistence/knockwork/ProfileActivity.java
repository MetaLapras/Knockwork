package com.pasistence.knockwork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Freelancer.Activities.FreeLancerDashboardActivity;
import com.pasistence.knockwork.Freelancer.Activities.FreelancerProfileActivity;
import com.pasistence.knockwork.Freelancer.Fragments.FreeLancerProfileFragment;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterClient;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Remote.MyApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;
import static com.pasistence.knockwork.Common.PreferenceUtils.getUserType;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "profile" ;
    Context mContext;
    TextView txtEmail,txtGender,txtMobile,txtName,txtProfessionalTitle,txtAvailabilty,txtPaymentType,txtSelfIntro;
    public  String email,gender,mobile,name,title,available,payment,intro,image;
    CircleImageView imgProfile;
    ImageView imgEdit;
    MyApi mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mInit();
    }

    private void mInit() {

        mContext = ProfileActivity.this;
        txtName = (TextView)findViewById(R.id.txt_name);
        txtEmail = (TextView)findViewById(R.id.txt_email_id);
        txtProfessionalTitle = (TextView)findViewById(R.id.txt_user_type);
        txtGender = (TextView)findViewById(R.id.txt_gender);
        txtMobile = (TextView)findViewById(R.id.txt_mobile_no);
        txtPaymentType = (TextView)findViewById(R.id.txt_payment_type);
        txtSelfIntro = (TextView)findViewById(R.id.txt_receipt_id);
        txtAvailabilty = (TextView)findViewById(R.id.txt_puid_profile);

        imgEdit = (ImageView) findViewById(R.id.edit);

        imgProfile = (CircleImageView)findViewById(R.id.profile);

        //Init retrofit
        mService = Common.getApi();

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

        loadAlldata();
    }

    private void editProfile() {
        if(getUserType(mContext).equals(Common.Lancer)){
            Intent intent = new Intent(mContext,FreelancerProfileActivity.class);
            intent.putExtra("edit",Common.update);
            startActivity(intent);
        }else{
            Common.commonDialog(mContext,"Under Maintenance's");
        }
    }

    private void loadAlldata() {
        Log.e(TAG, getUserType(mContext));
        if(getUserType(mContext).equals(Common.Lancer)){
            mService.checkLancerexist(getUid(mContext)).enqueue(new Callback<ApiResponseRegisterLancer>() {
                @Override
                public void onResponse(Call<ApiResponseRegisterLancer> call, Response<ApiResponseRegisterLancer> response) {
                    ApiResponseRegisterLancer result = response.body();
                    Log.e(TAG, result.toString());

                    if(!result.getError()){
                        ArrayList<ApiResponseRegisterLancer.Lancer> lancers = (ArrayList<ApiResponseRegisterLancer.Lancer>) result.getLancer();

                        for(ApiResponseRegisterLancer.Lancer lan : lancers){
                           // PreferenceUtils.setLid(mContext,lan.getLancerId());

                            email = lan.getLancerEmail();
                            mobile = lan.getLancerMobileNo();
                            name = lan.getLancerName();
                            title = lan.getLancerProfessionalTitle();
                            available = lan.getLancerAvailability();
                            image=lan.getLancerImage();
                            gender = lan.getLancerGender();
                            payment = lan.getLancerMinHourRate();
                            intro = lan.getLancerSelfIntro();

                            txtName.setText(name);
                            txtEmail.setText(email);
                            txtGender.setText(gender);
                            txtProfessionalTitle.setText(title);
                            txtAvailabilty.setText(available);
                            txtPaymentType.setText(payment);
                            txtSelfIntro.setText(intro);

                            Picasso.with(mContext).load(image).into(imgProfile);
                        }


                    }else {
                        Common.commonDialog(mContext,result.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseRegisterLancer> call, Throwable t) {
                    t.printStackTrace();
                    Log.e(TAG, t.getMessage());
                }
            });
        }else {
            Log.e(TAG, getUid(mContext));
            mService.checkClientexist(getUid(mContext)).enqueue(new Callback<ApiResponseRegisterClient>() {
                @Override
                public void onResponse(Call<ApiResponseRegisterClient> call, Response<ApiResponseRegisterClient> response) {
                    ApiResponseRegisterClient result = response.body();
                    Log.e(TAG, result.toString());

                    if(!result.getError()){
                        ArrayList<ApiResponseRegisterClient.Client> lancers = (ArrayList<ApiResponseRegisterClient.Client>) result.getClient();

                        for(ApiResponseRegisterClient.Client lan : lancers){

                            //PreferenceUtils.setLid(mContext,lan.getClientId());

                            email = lan.getClientEmail();
                            mobile = "";
                            name = lan.getClientName();
                            title = lan.getClientName();
                            available = lan.getClientProvider();
                            image = lan.getClientImage();
                            gender = "";
                            payment = "";
                            intro = "";
                            Log.e(TAG+"det", email+mobile+name+title+available+image);

                            txtName.setText(name);
                            txtEmail.setText(email);
                            txtGender.setText(gender);
                            txtProfessionalTitle.setText(title);
                            txtAvailabilty.setText(available);
                            txtPaymentType.setText(payment);
                            txtSelfIntro.setText(intro);

                            Picasso.with(mContext).load(image).into(imgProfile);
                        }


                    }else {
                        Common.commonDialog(mContext,result.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseRegisterClient> call, Throwable t) {
                    t.printStackTrace();
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }
}
