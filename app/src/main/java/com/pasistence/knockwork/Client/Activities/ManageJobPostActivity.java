package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.ManageJobPostingAdapter;
import com.pasistence.knockwork.Model.ManageJobPostingModel;
import com.pasistence.knockwork.R;

import java.util.ArrayList;
import java.util.List;

public class ManageJobPostActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context mContext;
    MaterialSearchBar searchBar;
    RecyclerView recyclerLancer;
    RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "search";

    ArrayList<ManageJobPostingModel> manageJobPostingModels = new ArrayList<ManageJobPostingModel>();
    ManageJobPostingAdapter manageJobPostingAdapter;
    ManageJobPostingAdapter searchAdapter;
    List<String> suggestList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_job_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*--------------------------------------------------------------*/


        mInit();


        ManageJobPostingModel jobModel1 = new ManageJobPostingModel("1","Web Development","Fixed Price","$5k - $7k","Posted 2 days ago","85 Quots","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        ManageJobPostingModel jobMoel2 = new ManageJobPostingModel("2","Professional Designer needed for Tshirt and other Products, WebProjects.","Fixed Price","$5k - $7k","Posted 2 days ago","85 Quots","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        ManageJobPostingModel jobModel3 = new ManageJobPostingModel("3","App Developer for creating a custome water sports application","Fixed Price","$5k - $7k","Posted 2 days ago","85 Quots","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");

        manageJobPostingModels.add(jobModel1);
        manageJobPostingModels.add(jobMoel2);
        manageJobPostingModels.add(jobModel3);
        manageJobPostingModels.add(jobModel1);
        manageJobPostingModels.add(jobMoel2);
        manageJobPostingModels.add(jobModel3);
        manageJobPostingModels.add(jobModel1);
        manageJobPostingModels.add(jobMoel2);
        manageJobPostingModels.add(jobModel3);
        manageJobPostingModels.add(jobModel1);
        manageJobPostingModels.add(jobMoel2);
        manageJobPostingModels.add(jobModel3);
        manageJobPostingModels.add(jobModel1);
        manageJobPostingModels.add(jobMoel2);
        manageJobPostingModels.add(jobModel3);


        manageJobPostingAdapter = new ManageJobPostingAdapter(mContext, manageJobPostingModels);
        recyclerLancer.setAdapter(manageJobPostingAdapter);
        manageJobPostingAdapter.notifyDataSetChanged();

        /*--------------------------------------------------------------*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manage_job_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {
            //Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Home", Snackbar.LENGTH_LONG)
              //      .setAction("Action", null).show();
              startActivity(new Intent(mContext,DashboardActivity.class));

        } else if (id == R.id.nav_inbox) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Inbox", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            startActivity(new Intent(mContext,InboxActivity.class));


        } else if (id == R.id.nav_notification) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Notification", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        } else if (id == R.id.nav_manage) {
//                       Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Manage", Snackbar.LENGTH_LONG)
//                             .setAction("Action", null).show();
//            //startActivity(new Intent(mContext,ManageJobPostActivity.class));

        } else if (id == R.id.nav_posting) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Posting", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        } else if (id == R.id.nav_contest) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Contest", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        }else if (id == R.id.nav_settings) {

 //           startActivity(new Intent(mContext,SettingActivity.class));
//                         Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Settings", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();

        }else if (id == R.id.nav_support) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Support", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void mInit() {
        mContext = ManageJobPostActivity.this;
        recyclerLancer = (RecyclerView)findViewById(R.id.jobposting_recycler_view);
        recyclerLancer.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerLancer.setLayoutManager(layoutManager);
    }

}
