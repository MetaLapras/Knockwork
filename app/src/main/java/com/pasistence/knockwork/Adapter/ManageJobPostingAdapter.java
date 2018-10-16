package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Model.ManageJobPostingModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderMnageJobPosting;

import java.util.List;

public class ManageJobPostingAdapter extends RecyclerView.Adapter<ViewHolderMnageJobPosting> {


    Context mContext;
    List<ManageJobPostingModel> manageJobPostingModels ;


    public ManageJobPostingAdapter(Context mContext, List<ManageJobPostingModel> workerList) {
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

        final ManageJobPostingModel job = manageJobPostingModels.get(position);

       /* Picasso.with(mContext).load(lancers.getImage())
                .into(holder.CircularImageViewProfile);*/

        holder.txtjobName.setText(job.getJobname());
        holder.txtfixedPrice.setText(job.getJobfixedprice());
        holder.txtpriceRange.setText(job.getJobpricerange());
        holder.txtpoastedDays.setText(job.getJobpasteddays());
        holder.txtjobQuotes.setText(job.getJobquots());
        holder.txtjobDescription.setText(job.getJobdescription());

    }

    @Override
    public int getItemCount() {
        return manageJobPostingModels.size();
    }
}
