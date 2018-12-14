package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.pasistence.knockwork.Adapter.ClientProposalAdapter;
import com.pasistence.knockwork.Adapter.LancerListAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiClientProposalsResponse;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pasistence.knockwork.Common.PreferenceUtils.getCid;

public class ClientProposalActivity extends ClientBaseActivity {

    private static final String TAG = "clientproposal" ;
    Context mContext;
    RecyclerView proposalRecycler;
    LinearLayoutManager layoutManager;
    ProgressBar progressBar;
    MyApi mServices;
    ClientProposalAdapter proposalAdapter;
    ArrayList<ApiClientProposalsResponse> lancerArraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_client_proposal, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);


        mInit();
        loadAllProposals();
    }

    private void loadAllProposals() {

        mServices.getClientProposal(getCid(mContext)).enqueue(new Callback<List<ApiClientProposalsResponse>>() {
            @Override
            public void onResponse(Call<List<ApiClientProposalsResponse>> call, Response<List<ApiClientProposalsResponse>> response) {
                List<ApiClientProposalsResponse> result = response.body();
                Log.e(TAG, result.toString());
                for (ApiClientProposalsResponse rep: result
                     ) {
                    lancerArraylist.add(rep);
                }

                proposalAdapter = new ClientProposalAdapter(mContext,lancerArraylist);
                proposalRecycler.setAdapter(proposalAdapter);
                proposalAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ApiClientProposalsResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void mInit() {
        mContext = ClientProposalActivity.this;

        proposalRecycler = (RecyclerView)findViewById(R.id.proposal_recycler_view);
        proposalRecycler.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        proposalRecycler.setLayoutManager(layoutManager);

        progressBar = (ProgressBar)findViewById(R.id.progress);

        mServices = Common.getApi();
    }


}
