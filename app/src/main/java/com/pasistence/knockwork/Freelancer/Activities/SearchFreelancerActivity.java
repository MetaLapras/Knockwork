package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.auth.ErrorCodes;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.InboxListAdapter;
import com.pasistence.knockwork.Adapter.SearchPageFreelancerAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Employeer.Activities.InboxActivity;
import com.pasistence.knockwork.Model.InboxDataModel;
import com.pasistence.knockwork.Model.JobList;
import com.pasistence.knockwork.Model.SearchPageFreelancerModel;
import com.pasistence.knockwork.Model.SearchPageListModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.Remote.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFreelancerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG ="aaa" ;
    Context mContext;
    RecyclerView searchFreelancerrecyclerview;
    RecyclerView.LayoutManager layoutManager;

    private final static String API_KEY = "";


    SearchPageFreelancerAdapter searchPageFreelancerAdapter;
    public ArrayList<SearchPageFreelancerModel> searchPageListModels = new ArrayList<SearchPageFreelancerModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_freelancer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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



        /*if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }*/
       /* MyApi myApiService =
                RetrofitClient.getClient("").create(MyApi.class);

        Call<SearchPageListModel> call = myApiService.getIP(API_KEY);

        call.enqueue(new Callback<SearchPageListModel>() {
            @Override
            public void onResponse(Call<SearchPageListModel> call, Response<SearchPageListModel> response) {
                List<JobList> movies = response.body().getJob_list();
                Toast.makeText(SearchFreelancerActivity.this, "Number of movies received:" + movies.size(), Toast.LENGTH_SHORT).show();
                // Log.d(TAG, "Number of movies received: " + movies.size());
            }

            @Override
            public void onFailure(Call<SearchPageListModel> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(SearchFreelancerActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.toString());
            }
        });
    }
*/

        mInit();


        SearchPageFreelancerModel searchPage1 = new SearchPageFreelancerModel("Web designing", "Fixed Price","5k-7k","Poasted 2 days ago","85 Quots","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s ", "https://content-static.upwork.com/uploads/2014/10/01073427/profilephoto1.jpg","Jimey Lindsy","United State","25k spent","80%");

       // SearchPageFreelancerModel searchPage2 = new SearchPageFreelancerModel("create logo for brand t-shirt Company", "Fixed Price","5k-7k","Poasted 2 days ago","85 Quots", "Technology is making online work similar to local work, with added speed, cost, and quality advantages.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDdFKBrWfM9lqmjc_Cvg4n4BebNmgNt7OWQ59W0SjTU0TcfoubA","Carmne uhanre","Austrelia","50k spent","95%");

        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);


        searchPageFreelancerAdapter = new SearchPageFreelancerAdapter(mContext, searchPageListModels);
        searchFreelancerrecyclerview.setAdapter(searchPageFreelancerAdapter);
        searchPageFreelancerAdapter.notifyDataSetChanged();


        loadSuggestList();

    }

    private void mInit() {
        mContext = SearchFreelancerActivity.this;

        // inboxsearchBar     = (MaterialSearchBar) findViewById(R.id.inbox_search_bar);
        searchFreelancerrecyclerview  = (RecyclerView) findViewById(R.id.search_recycler_view);
        searchFreelancerrecyclerview.setHasFixedSize(false);
        layoutManager      = new LinearLayoutManager(this);
        searchFreelancerrecyclerview.setLayoutManager(layoutManager);
    }

    private void startSearch(String text) {
        // adapter = new SearchAdapter(WorkerDisplayList.this, this, database.getWorkerName(text));
        // WorkerListRecyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        // suggestList = database.getNames();
        // materialSearchBar.setLastSuggestions(suggestList);
    }
//}






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
        getMenuInflater().inflate(R.menu.search_freelancer, menu);
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

        if (id == R.id.nav_camera) {
            startActivity(new Intent(SearchFreelancerActivity.this,JobDescriptionActivity.class));
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
