package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.pasistence.knockwork.Adapter.LancerListAdapter;
import com.pasistence.knockwork.Freelancer.Activities.JobPoastingActivity;
import com.pasistence.knockwork.Model.LancerListModel;
import com.pasistence.knockwork.R;

import java.util.ArrayList;
import java.util.List;

public class LancersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "search";
    Context mContext;
    MaterialSearchBar searchBar;
    RecyclerView recyclerLancer;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<LancerListModel> lancerList = new ArrayList<LancerListModel>();
    LancerListAdapter lancerListAdapter;
    LancerListAdapter searchAdapter;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*----------------------------------------------------------------------------*/

        mInit();




        LancerListModel lancers = new LancerListModel("1","Lorem Ipsum","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.","United State"," $ 25","80%","https://content-static.upwork.com/uploads/2014/10/01073427/profilephoto1.jpg","Web Developer");
        LancerListModel lancers2 = new LancerListModel("2","Cupcake","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.","United State"," $ 55","70%","https://cdn.cultofmac.com/wp-content/uploads/2011/10/97571564a70014ca5658b67f64f2ce23_1253524914.jpeg","Android Developer");
        LancerListModel lancers3 = new LancerListModel("3","Jelly Bean","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ","United State"," $ 55","70%","https://i.cricketcb.com/stats/img/faceImages/8364.jpg","Software Developer");

        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers3);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers3);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers3);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers3);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers3);

        loadSuggestList();

        //Setup search bar
        searchBar.setHint("Search");
        searchBar.setLastSuggestions(suggestList);
        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                List<String>suggest = new ArrayList<String>();
                for(String search : suggestList)
                {
                    if(search.toLowerCase().contains(searchBar.getText().toLowerCase()))
                        suggest.add(search);
                }

                searchBar.setLastSuggestions(suggest);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                {
                    recyclerLancer.setAdapter(lancerListAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });


        lancerListAdapter = new LancerListAdapter(mContext, lancerList);
        recyclerLancer.setAdapter(lancerListAdapter);
        lancerListAdapter.notifyDataSetChanged();



        /*----------------------------------------------------------------------------*/
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
        getMenuInflater().inflate(R.menu.lancers, menu);
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
   //         Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Home", Snackbar.LENGTH_LONG)
     //               .setAction("Action", null).show();
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
            startActivity(new Intent(mContext,JobPoastingActivity.class));

        } else if (id == R.id.nav_contest) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Contest", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        }else if (id == R.id.nav_settings) {

            startActivity(new Intent(mContext,SettingActivity.class));
            //             Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Settings", Snackbar.LENGTH_LONG)
            //                .setAction("Action", null).show();

        }else if (id == R.id.nav_support) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Support", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    private void mInit() {
        mContext = LancersActivity.this;

        searchBar = (MaterialSearchBar)findViewById(R.id.search_bar_lancer);
        recyclerLancer = (RecyclerView)findViewById(R.id.recycler_lancer_list);
        recyclerLancer.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerLancer.setLayoutManager(layoutManager);
    }

    private void startSearch(CharSequence text) {
        ArrayList<LancerListModel> searchLancer = new ArrayList<LancerListModel>();
        for(LancerListModel list : lancerList){
            if(list.getCategory().equals(text.toString())){

                searchLancer.add(list);
                Log.e(TAG, searchLancer.toString());

                searchAdapter = new LancerListAdapter(mContext,searchLancer);
                recyclerLancer.setAdapter(searchAdapter);
            }
        }

    }

    private void loadSuggestList() {
        //get Data into the list
        /*for(LancerListModel list : lancerList){
            suggestList.add(list.getCategory());
        }*/
        suggestList.add("Software Developer");
        suggestList.add("Web Developer");
        suggestList.add("Android Developer");
        searchBar.setLastSuggestions(suggestList);
    }


}
