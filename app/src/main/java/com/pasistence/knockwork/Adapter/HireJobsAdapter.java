package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Model.ApiResponse.ApiHirejobsReponse;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderHireJobs;

import java.util.List;

public class HireJobsAdapter extends RecyclerView.Adapter<ViewHolderHireJobs> {

    Context mContext;
    List<ApiHirejobsReponse> hireJobsList;


    public HireJobsAdapter(Context mContext,List<ApiHirejobsReponse> hireJobsList){
        this.mContext=mContext;
        this.hireJobsList=hireJobsList;
    }


    @NonNull
    @Override
    public ViewHolderHireJobs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_holder_hire_jobs,viewGroup,false);

        return new ViewHolderHireJobs(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHireJobs viewHolderHireJobs, int i) {
        final ApiHirejobsReponse apiHirejobsReponse = hireJobsList.get(i);
        viewHolderHireJobs.txtJobTitle.setText(apiHirejobsReponse.getTitle());
        viewHolderHireJobs.txtJobDescription.setText(apiHirejobsReponse.getDetails());
//        viewHolderHireJobs.txtHire.setText("hire");
    }

    @Override
    public int getItemCount() {
        return hireJobsList.size();
    }
}
