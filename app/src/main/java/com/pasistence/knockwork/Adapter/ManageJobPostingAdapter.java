package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.pasistence.knockwork.Client.Activities.ClientJobPostingActivity;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.Model.ManageJobPostingModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderMnageJobPosting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManageJobPostingAdapter extends RecyclerView.Adapter<ViewHolderMnageJobPosting> {


    Context mContext;
    ArrayList<ApiPostJobResponse.Result> manageJobPostingModels ;


    public ManageJobPostingAdapter(Context mContext, ArrayList<ApiPostJobResponse.Result> workerList) {
        this.mContext = mContext;
        this.manageJobPostingModels = workerList;
    }


    @NonNull
    @Override
    public ViewHolderMnageJobPosting onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_manage_job_posting,parent,false);
        return new ViewHolderMnageJobPosting(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMnageJobPosting holder, int position) {

        final ApiPostJobResponse.Result job = manageJobPostingModels.get(position);

       /* Picasso.with(mContext).load(lancers.getImage())
                .into(holder.CircularImageViewProfile);*/

        holder.txtjobName.setText(job.getTitle());
        holder.txtfixedPrice.setText(job.getType());
        holder.txtpriceRange.setText(job.getRate());
        holder.txtpoastedDays.setText(job.getUpdatedAt());
        holder.txtjobQuotes.setText(job.getDuration());
        holder.txtjobDescription.setText(job.getDetails());

        final ArrayList<ApiPostJobResponse.Result> intentList = new ArrayList<ApiPostJobResponse.Result>();
        intentList.add(job);


        holder.btnJobEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ClientJobPostingActivity.class);
                intent.putExtra("Jobs", intentList);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return manageJobPostingModels.size();
    }
}
