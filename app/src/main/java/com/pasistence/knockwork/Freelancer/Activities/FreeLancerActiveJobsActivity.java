package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.pasistence.knockwork.Adapter.HireJobsAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiHirejobsReponse;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pasistence.knockwork.Common.PreferenceUtils.getLid;

public class FreeLancerActiveJobsActivity extends FreeLancerBaseActivity {

    private static final String TAG = "HireJobs : ";
    MyApi mService;
    Context mContext;
    RecyclerView recyclerHirejobs;
    LinearLayoutManager layoutManager;

    public FirebaseAuth mAuth;
    HireJobsAdapter hireJobsAdapter;
    List<ApiHirejobsReponse> hireJobsList=new ArrayList<ApiHirejobsReponse>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_free_lancer_active_jobs);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_free_lancer_active_jobs, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(4).setChecked(true);

        mInit();
        loadHireJob();


    }


    private void mInit() {
        mContext = FreeLancerActiveJobsActivity.this;
        recyclerHirejobs = (RecyclerView)findViewById(R.id.recycler_hire_jobs);
        recyclerHirejobs.setHasFixedSize(true);
        recyclerHirejobs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerHirejobs.setNestedScrollingEnabled(false);


        mService=Common.getApi();
        Common.getUserPreference(mContext);
//        mAuth = FirebaseAuth.getInstance();
    }


    private void loadHireJob() {

        mService.LancerHireJobs(getLid(mContext)).enqueue(new Callback<List<ApiHirejobsReponse>>() {
            @Override
            public void onResponse(Call<List<ApiHirejobsReponse>> call, Response<List<ApiHirejobsReponse>> response) {
                Log.e(TAG,response.body().toString());
                hireJobsList=response.body();
//                Log.e(TAG,hireJobsList.get(0).toString());

                hireJobsAdapter = new HireJobsAdapter(mContext,hireJobsList);
                recyclerHirejobs.setAdapter(hireJobsAdapter);
                hireJobsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ApiHirejobsReponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
