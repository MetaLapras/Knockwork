package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Freelancer.Fragments.FreeLancerProfileFragment;
import com.pasistence.knockwork.Freelancer.Fragments.FreelancerSkillsFragment;
import com.pasistence.knockwork.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FreelancerProfileActivity extends AppCompatActivity {

CircleImageView imgProfile;
TextView txtProfileName,txtprofileEmail;
FrameLayout fragmentProfiles;
FragmentManager fragmentManager;
FragmentTransaction fragmentTransaction;
Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_profile);
        mInit();
    }

    private void mInit() {
        mContext = FreelancerProfileActivity.this;
        imgProfile = (CircleImageView)findViewById(R.id.freelancer_profile_img);
        txtProfileName = (TextView)findViewById(R.id.freelancer_profile_name);
        txtprofileEmail = (TextView)findViewById(R.id.freelancer_profile_state);

        //fragmentProfiles = (Fragment)findViewById(R.id.fragment_profile);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

/*        fragmentTransaction.add(R.id.fragment_profile,new FreeLancerProfileFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit()*/;

        fragmentTransaction.add(R.id.fragment_profile,new FreeLancerProfileFragment());
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        txtProfileName.setText(Common.UserName);
        txtprofileEmail.setText(Common.UserEmail);
        Picasso.with(mContext).load(Common.UserPhoto).into(imgProfile);


    }

}
