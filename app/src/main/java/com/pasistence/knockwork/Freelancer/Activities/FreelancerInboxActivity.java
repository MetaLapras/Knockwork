package com.pasistence.knockwork.Freelancer.Activities;

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
import com.pasistence.knockwork.Adapter.InboxListAdapter;
import com.pasistence.knockwork.Model.InboxDataModel;
import com.pasistence.knockwork.R;

import java.util.ArrayList;

public class FreelancerInboxActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context mContext;
    MaterialSearchBar inboxsearchBar;
    RecyclerView inboxrecyclerview;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<InboxDataModel> inboxDataModels = new ArrayList<InboxDataModel>();
    InboxListAdapter inboxListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_inbox);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mInit();


        InboxDataModel lancers = new InboxDataModel("1", "Cristina Afeeba", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s ", "I Can do ", "https://content-static.upwork.com/uploads/2014/10/01073427/profilephoto1.jpg");

        InboxDataModel lancers2 = new InboxDataModel("2", "Afeeba Cristina", "Technology is making online work similar to local work, with added speed, cost, and quality advantages.", "I Can do", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDdFKBrWfM9lqmjc_Cvg4n4BebNmgNt7OWQ59W0SjTU0TcfoubA");

        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);
        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);
        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);
        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);
        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);
        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);


        inboxListAdapter = new InboxListAdapter(mContext, inboxDataModels);
        inboxrecyclerview.setAdapter(inboxListAdapter);
        inboxListAdapter.notifyDataSetChanged();


        loadSuggestList();

    }

    private void mInit() {
        mContext = FreelancerInboxActivity.this;

        // inboxsearchBar     = (MaterialSearchBar) findViewById(R.id.inbox_search_bar);
        inboxrecyclerview = (RecyclerView) findViewById(R.id.recycler_inbox_freelancer);
        inboxrecyclerview.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        inboxrecyclerview.setLayoutManager(layoutManager);
    }

    private void startSearch(String text) {
        // adapter = new SearchAdapter(WorkerDisplayList.this, this, database.getWorkerName(text));
        // WorkerListRecyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        // suggestList = database.getNames();
        // materialSearchBar.setLastSuggestions(suggestList);
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
        getMenuInflater().inflate(R.menu.freelancer_inbox, menu);
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
            startActivity(new Intent(FreelancerInboxActivity.this,FreeLancerDashboardActivity.class));

        } else if (id == R.id.nav_inbox) {


        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(FreelancerInboxActivity.this,ManageBidsActivity.class));

        } else if (id == R.id.nav_active) {

        } else if (id == R.id.nav_manage_jobs) {
            startActivity(new Intent(FreelancerInboxActivity.this,JobPoastingActivity.class));

        } else if (id == R.id.nav_proposal) {

        }else if (id == R.id.nav_settings) {
            startActivity(new Intent(FreelancerInboxActivity.this,FreelancerSettingActivity.class));


        }else if (id == R.id.nav_support) {


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
