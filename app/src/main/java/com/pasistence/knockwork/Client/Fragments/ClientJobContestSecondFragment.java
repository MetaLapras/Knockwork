package com.pasistence.knockwork.Client.Fragments;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.ManageContestAdapter;
import com.pasistence.knockwork.Adapter.ManageJobPostingAdapter;
import com.pasistence.knockwork.Client.Activities.ClientJobContestActivity;
import com.pasistence.knockwork.Client.Activities.ClientJobRequest;
import com.pasistence.knockwork.Client.Activities.ManageJobPostActivity;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostContestResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClientJobContestSecondFragment extends Fragment {

    private static final String TAG = "contest";
    Context mContext;
    Activity mActivity;
    RecyclerView recyclerContest;
    LinearLayoutManager layoutManager;
    ProgressBar progressBar;
    ManageContestAdapter manageContestAdapter;

    ApiPostContestResponse.Result contestResult;
    ArrayList<ApiPostContestResponse.Result> contestResultList = new ArrayList<>();
    ArrayList<ApiPostContestResponse.Result> tryout = new ArrayList<>();

    MyApi mServices;
    int PageNo =  1;
    int TotalElementsCount=0;
    int currentItems;
    int totalItems;
    int scrollOutItems;

    Toolbar toolbar;


    public ClientJobContestSecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_client_job_contest_second, container, false);
        mInit(view);
       // return inflater.inflate(R.layout.fragment_client_job_contest_second, container, false);
        return view;
    }

    private void mInit(View view) {
        mActivity = getActivity();
        mContext = getContext();


        recyclerContest = (RecyclerView)view.findViewById(R.id.contest_recycler_view);
        recyclerContest.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(mContext);
        recyclerContest.setLayoutManager(layoutManager);

        progressBar = (ProgressBar)view.findViewById(R.id.progress);

        mServices = Common.getApi();

        readAllContest(PageNo);

    }

    private void readAllContest(int PageNo) {
        try {
            mServices.ClientPostContestRead(PageNo).enqueue(new Callback<ApiPostContestResponse>() {
                @Override
                public void onResponse(Call<ApiPostContestResponse> call, Response<ApiPostContestResponse> response) {
                    ApiPostContestResponse result = response.body();
                    Log.e(TAG, result.toString());

                    contestResultList = result.getResult();
                    Log.e(TAG+"SizeM",contestResultList.size()+"");

                    TotalElementsCount = Integer.parseInt(result.getTotalcount());

                    for (ApiPostContestResponse.Result res : result.getResult()){
                        tryout.add(res);
                    }

                    Log.e(TAG,tryout.toString());
                    Log.e(TAG+"Size",tryout.size()+"");

                    manageContestAdapter = new ManageContestAdapter(mContext,mActivity,getFragmentManager(), contestResultList);
                    recyclerContest.setAdapter(manageContestAdapter);
                    recyclerContest.setLayoutManager(layoutManager);
                    manageContestAdapter.notifyDataSetChanged();

                    // manageJobPostingAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<ApiPostContestResponse> call, Throwable t) {
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


}
