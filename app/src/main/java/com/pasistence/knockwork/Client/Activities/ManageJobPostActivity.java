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
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.ManageJobPostingAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

public class ManageJobPostActivity extends ClientBaseActivity {

    Context mContext;
    MaterialSearchBar searchBar;
    RecyclerView recyclerLancer;
   // RecyclerView.LayoutManager layoutManager;
    LinearLayoutManager layoutManager;
    private static final String TAG = "search";
    Button btnMore;
    Boolean isScrolling = false;

    ProgressBar progressBar;
    MyApi mServices;
    int PageNo =  1;
    int TotalElementsCount=0;
    int currentItems;
    int totalItems;
    int scrollOutItems;


    ArrayList<ApiPostJobResponse.Result> manageJobPostingModels = new ArrayList<ApiPostJobResponse.Result>();
    ArrayList<ApiPostJobResponse.Result> tryout = new ArrayList<ApiPostJobResponse.Result>();
    public ManageJobPostingAdapter manageJobPostingAdapter;
    ManageJobPostingAdapter searchAdapter;
    List<String> suggestList = new ArrayList<>();


    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_manage_job_post, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(3).setChecked(true);


        //setContentView(R.layout.activity_manage_job_post);

        activity = ManageJobPostActivity.this;
        /*--------------------------------------------------------------*/

        mInit();

        // mOnclick();
        mServices = Common.getApi();
        readAllJobs();

        /*recyclerLancer.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        });*/

        /*--------------------------------------------------------------*/

    }

    private void readAllJobs() {
        try {
            mServices.ClientPostAJobRead(getUid(mContext)).enqueue(new Callback<ApiPostJobResponse>() {
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


    private void mInit() {
        mContext = ManageJobPostActivity.this;

        recyclerLancer = (RecyclerView)findViewById(R.id.jobposting_recycler_view);
        recyclerLancer.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerLancer.setLayoutManager(layoutManager);

        progressBar = (ProgressBar)findViewById(R.id.progress);

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
/*
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
*/
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

