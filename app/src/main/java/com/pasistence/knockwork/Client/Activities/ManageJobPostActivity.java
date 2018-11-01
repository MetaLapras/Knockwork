package com.pasistence.knockwork.Client.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.ManageJobPostingAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Freelancer.Activities.JobPoastingActivity;
import com.pasistence.knockwork.Interface.ItemClickListener;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.Model.ManageJobPostingModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.ViewHolder.ViewHolderMnageJobPosting;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageJobPostActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context mContext;
    MaterialSearchBar searchBar;
    RecyclerView recyclerLancer;
   // RecyclerView.LayoutManager layoutManager;
    LinearLayoutManager layoutManager;
    private static final String TAG = "search";
    Button btnMore;
    Boolean isScrolling = false;
    int currentItems;
    int totalItems;
    int scrollOutItems;
    ProgressBar progressBar;
    MyApi mServices;
    int PageNo =  1;
    int TotalElementsCount=0;


    ArrayList<ApiPostJobResponse.Result> manageJobPostingModels = new ArrayList<ApiPostJobResponse.Result>();
    ArrayList<ApiPostJobResponse.Result> tryout = new ArrayList<ApiPostJobResponse.Result>();
    public ManageJobPostingAdapter manageJobPostingAdapter;
    ManageJobPostingAdapter searchAdapter;
    List<String> suggestList = new ArrayList<>();


    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_job_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activity = ManageJobPostActivity.this;
        /*--------------------------------------------------------------*/

        mInit();
        // mOnclick();
        mServices = Common.getApi();
        readAllJobs(PageNo);

     /*   manageJobPostingAdapter = new ManageJobPostingAdapter(mContext,ManageJobPostActivity.this, manageJobPostingModels);
        recyclerLancer.setAdapter(manageJobPostingAdapter);
        recyclerLancer.setLayoutManager(layoutManager);*/
        // manageJobPostingAdapter.notifyDataSetChanged();



        recyclerLancer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstCompletelyVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    fetchData();
                }
              //  isScrolling = true;
            }
        });

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

    private void readAllJobs(int PageNo) {
        try {
            mServices.ClientPostAJobRead(PageNo).enqueue(new Callback<ApiPostJobResponse>() {
                @Override
                public void onResponse(Call<ApiPostJobResponse> call, Response<ApiPostJobResponse> response) {
                    ApiPostJobResponse result = response.body();
                    Log.e(TAG, result.toString());

                    manageJobPostingModels = result.getResult();
                    Log.e(TAG+"SizeM",manageJobPostingModels.size()+"");

                    TotalElementsCount = Integer.parseInt(result.getTotalcount());

                    for (ApiPostJobResponse.Result res : result.getResult()){
                        tryout.add(res);
                    }

                    Log.e(TAG,tryout.toString());
                    Log.e(TAG+"Size",tryout.size()+"");

                    manageJobPostingAdapter = new ManageJobPostingAdapter(mContext,ManageJobPostActivity.this, manageJobPostingModels);
                    recyclerLancer.setAdapter(manageJobPostingAdapter);
                    recyclerLancer.setLayoutManager(layoutManager);
                    manageJobPostingAdapter.notifyDataSetChanged();

                   // manageJobPostingAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<ApiPostJobResponse> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                    t.printStackTrace();

                }
            });


        }catch (Exception e)
        {
            Log.e(TAG, e.getMessage() );
            e.printStackTrace();
            Common.commonDialog(mContext,"Sever not found..");
        }
    }

    /* private void mOnclick() {
         btnMore.setOnClickListener(this);
     }
 */
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
            startActivity(new Intent(mContext,JobPoastingActivity.class));

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
        progressBar = (ProgressBar)findViewById(R.id.progress);

        //btnMore = (Button)findViewById(R.id.btn_more);
     /*   btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==btnMore) {
                    btnMore.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    recyclerLancer.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                isScrolling = true;
                            }
                        }

                        @Override
                        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            currentItems = layoutManager.getChildCount();
                            totalItems = layoutManager.getItemCount();
                            scrollOutItems = layoutManager.findFirstCompletelyVisibleItemPosition();

                            if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                                isScrolling = false;
                                fetchData();
                            }
                        }
                    });
                    progressBar.setVisibility(View.INVISIBLE);

                }

            }
        });*/



    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              // progressBar.setVisibility(View.VISIBLE);
                for (int i=0; i<1; i++)
                {
                    try {
                        if(TotalElementsCount!= manageJobPostingModels.size()){
                            PageNo++;
                            mServices.ClientPostAJobRead(PageNo).enqueue(new Callback<ApiPostJobResponse>() {
                                @Override
                                public void onResponse(Call<ApiPostJobResponse> call, Response<ApiPostJobResponse> response) {
                                    ApiPostJobResponse result = response.body();
                                    Log.e(TAG, result.toString());

                                    //manageJobPostingModels = result.getResult();
                                    Log.e(TAG+"SizeM",manageJobPostingModels.size()+"");
                                    for (ApiPostJobResponse.Result res : result.getResult()){
                                        manageJobPostingModels.add(res);
                                        tryout.add(res);
                                    }


                                    Log.e(TAG,tryout.toString());
                                    Log.e(TAG+"Size",tryout.size()+"");

                                    //manageJobPostingAdapter = new ManageJobPostingAdapter(mContext,ManageJobPostActivity.this, manageJobPostingModels);
                                    //recyclerLancer.setAdapter(manageJobPostingAdapter);
                                    //recyclerLancer.setLayoutManager(layoutManager);
                                    manageJobPostingAdapter.notifyDataSetChanged();

                                    // manageJobPostingAdapter.notifyDataSetChanged();

                                }

                                @Override
                                public void onFailure(Call<ApiPostJobResponse> call, Throwable t) {
                                    Log.e(TAG, t.getMessage());
                                    t.printStackTrace();

                                }
                            });
                        }else
                        {
                            Toast.makeText(mContext, "Nothing to Display", Toast.LENGTH_SHORT).show();
                        }



                    }catch (Exception e)
                    {
                        Log.e(TAG, e.getMessage() );
                        e.printStackTrace();
                        Common.commonDialog(mContext,"Sever not found..");
                    }
                    manageJobPostingAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }

            }
        }, 5000);
    }

}

