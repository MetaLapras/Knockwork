package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.pasistence.knockwork.Adapter.ManageProposalAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiGetProposals;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pasistence.knockwork.Common.PreferenceUtils.getLid;

public class ReportSubmittedProposalActivity extends FreeLancerBaseActivity {

    private static final String TAG = "proposal" ;
    Context mContext;
    RecyclerView proposalRecycler;
    LinearLayoutManager layoutManager;
    ProgressBar progressBar;
    MyApi mServices;
    public String lid;
    ArrayList<ApiGetProposals> mList = new ArrayList<ApiGetProposals>();
    ManageProposalAdapter manageProposalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_report_submitted_proposal, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        mInit();
        loadAllData();

    }

    private void loadAllData() {
        lid = getLid(mContext);
        mServices.getProposalbyLid(lid).enqueue(new Callback<List<ApiGetProposals>>() {
            @Override
            public void onResponse(Call<List<ApiGetProposals>> call, Response<List<ApiGetProposals>> response) {
                List<ApiGetProposals> result = response.body();
                Log.e(TAG, result.toString() );
                for(ApiGetProposals p : result){
                    mList.add(p);
                }
                manageProposalAdapter = new ManageProposalAdapter(mContext,mList);
                proposalRecycler.setAdapter(manageProposalAdapter);
                proposalRecycler.setLayoutManager(layoutManager);
                manageProposalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ApiGetProposals>> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, t.getMessage() );
            }
        });
    }

    private void mInit() {
        mContext = ReportSubmittedProposalActivity.this;

        proposalRecycler = (RecyclerView)findViewById(R.id.proposal_recycler_view);
        proposalRecycler.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        proposalRecycler.setLayoutManager(layoutManager);

        progressBar = (ProgressBar)findViewById(R.id.progress);

        mServices = Common.getApi();
    }

}
