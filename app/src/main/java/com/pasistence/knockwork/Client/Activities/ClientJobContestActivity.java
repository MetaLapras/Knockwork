package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.pasistence.knockwork.Client.Fragments.ClientJobContestFragment;
import com.pasistence.knockwork.Client.Fragments.ClientJobContestSecondFragment;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Freelancer.Fragments.FreeLancerProfileFragment;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.SelectionActivity;

public class ClientJobContestActivity extends ClientBaseActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_client_job_contest, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        mContext = ClientJobContestActivity.this;

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.contest_frame,new ClientJobContestSecondFragment());
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manage_job_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {

          /*  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contest_frame,new ClientJobContestFragment());
            ft.addToBackStack(null);
            ft.commit();*/

            ClientJobContestFragment fragment = new ClientJobContestFragment();

            Bundle args = new Bundle();

            args.putString("type",Common.register);

            fragment.setArguments(args);


            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.contest_frame,fragment);
            ft.addToBackStack(null);
            ft.commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
