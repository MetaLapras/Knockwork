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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

public class SearchFreelancerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context mContext;
    MyApi mMyServices;
    RetrofitClient retrofit;
    //MaterialSearchBar inboxsearchBar;
    RecyclerView searchFreelancerrecyclerview;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<SearchPageFreelancerModel> searchPageFreelancerModels = new ArrayList<SearchPageFreelancerModel>();
    SearchPageFreelancerAdapter searchPageFreelancerAdapter;

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



        mInit();
        mMyServices = Common.getApi();
       // mContext = SearchFreelancerActivity.this;
        /** Create handle for the RetrofitInstance interface*/
        mMyServices = RetrofitClient.getClient("").create(MyApi.class);
        /** Call the method with parameter in the interface to get the notice data*/
        Call<List<SearchPageListModel>> call = mMyServices.getIP();



        /** Call the method with parameter in the interface to get the notice data*/
      //  Call<SearchPageFreelancerModel> call = mMyServices.getIP();

        //Call<List<SearchPageListModel>> listcall = RetrofitClient.getClient("").getIP();
        call.enqueue(new Callback<List<SearchPageListModel>>() {
            @Override
            public void onResponse(Call<List<SearchPageListModel>> call, Response<List<SearchPageListModel>> response) {
                SearchPageListModel result= (SearchPageListModel) response.body();
                generateSearchList(((SearchPageListModel) response.body()).getJob_list());
                Toast.makeText(SearchFreelancerActivity.this, "Done ", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<SearchPageListModel>> call, Throwable t) {
                Toast.makeText(SearchFreelancerActivity.this, "Faield", Toast.LENGTH_SHORT).show();

            }
        });

        /*call.enqueue(new Callback<SearchPageListModel>() {
            @Override
            public void onResponse(Call<SearchPageListModel> call, Response<SearchPageListModel> response) {
                Toast.makeText(SearchFreelancerActivity.this, "URl get Connected", Toast.LENGTH_SHORT).show();
                //SearchPageListModel result = response.body();
                generateSearchList((ArrayList<SearchPageFreelancerModel>) response.body().getCustomer());
            }

            @Override
            public void onFailure(Call<SearchPageListModel> call, Throwable t) {
                Toast.makeText(SearchFreelancerActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
  // }


        /*SearchPageFreelancerModel lancers = new SearchPageFreelancerModel("Professional Designer", "Fixed Price","5k-7k","Poasted 2 days ago","85 Quotes", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s ", "https://content-static.upwork.com/uploads/2014/10/01073427/profilephoto1.jpg","Jaime Lindsey","United State","25k Spent","80%");

        SearchPageFreelancerModel lancers2 = new SearchPageFreelancerModel("Professional Designer", "Fixed Price", "5k-7k","Poasted 2 days ago","85 Quotes","Technology is making online work similar to local work, with added speed, cost, and quality advantages.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDdFKBrWfM9lqmjc_Cvg4n4BebNmgNt7OWQ59W0SjTU0TcfoubA","carname thondia","Australia","45k Spent","70%");

        searchPageFreelancerModels.add(lancers);
        searchPageFreelancerModels.add(lancers2);
        searchPageFreelancerModels.add(lancers);
        searchPageFreelancerModels.add(lancers2);
        searchPageFreelancerModels.add(lancers);
        searchPageFreelancerModels.add(lancers2);
        searchPageFreelancerModels.add(lancers);
        searchPageFreelancerModels.add(lancers2);
        searchPageFreelancerModels.add(lancers);
        searchPageFreelancerModels.add(lancers2);
        searchPageFreelancerModels.add(lancers);
        searchPageFreelancerModels.add(lancers2);
*/

        searchPageFreelancerAdapter = new SearchPageFreelancerAdapter(mContext, searchPageFreelancerModels);
        searchFreelancerrecyclerview.setAdapter(searchPageFreelancerAdapter);
        searchPageFreelancerAdapter.notifyDataSetChanged();


        loadSuggestList();

    }

    private void generateSearchList(List<JobList> jobLists) {
        searchFreelancerrecyclerview = findViewById(R.id.search_recycler_view);
        searchPageFreelancerAdapter = new SearchPageFreelancerAdapter(jobLists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchFreelancerActivity.this);
        searchFreelancerrecyclerview.setLayoutManager(layoutManager);
        searchFreelancerrecyclerview.setAdapter(searchPageFreelancerAdapter);
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
