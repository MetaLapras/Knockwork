package com.pasistence.knockwork.Client.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pasistence.knockwork.Client.Fragments.ClientJobContestFragment;
import com.pasistence.knockwork.Client.Fragments.ClientJobContestSecondFragment;
import com.pasistence.knockwork.Freelancer.Fragments.FreeLancerProfileFragment;
import com.pasistence.knockwork.R;

public class ClientJobContestActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_job_contest);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.contest_frame,new ClientJobContestFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
