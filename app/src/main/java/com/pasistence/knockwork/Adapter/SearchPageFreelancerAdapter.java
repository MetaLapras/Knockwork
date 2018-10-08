package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Freelancer.Activities.JobDescriptionActivity;
import com.pasistence.knockwork.Model.JobDescriptionModel;
import com.pasistence.knockwork.Model.SearchPageFreelancerModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderJobDescription;
import com.pasistence.knockwork.ViewHolder.ViewHolderSearchPageFreelancer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchPageFreelancerAdapter  extends RecyclerView.Adapter<ViewHolderSearchPageFreelancer> {

    Context mContext;
    List<SearchPageFreelancerModel> searchPageFreelancerModels ;


    public SearchPageFreelancerAdapter(Context mContext, List<SearchPageFreelancerModel> workerList) {
        this.mContext = mContext;
        this.searchPageFreelancerModels = workerList;
    }

    @NonNull
    @Override
    public ViewHolderSearchPageFreelancer onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_search_page_freelancer_template,parent,false);

        return new ViewHolderSearchPageFreelancer(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSearchPageFreelancer holder, int position) {

        final SearchPageFreelancerModel lancers = searchPageFreelancerModels.get(position);

        Picasso.with(mContext).load(lancers.getImage())
                .into(holder.img);


        holder.txtjobName.setText(lancers.getJobname());
        holder.txtfixedPrice.setText(lancers.getFixedprice());
        holder.txtpriceRange.setText(lancers.getPricerange());
        holder.txtpoastedDays.setText(lancers.getPoasteddays());
        holder.txtjobQuotes.setText(lancers.getQuotes());
        holder.txtjobDescription.setText(lancers.getDescription());
        holder.txtnamefreelancer.setText(lancers.getPersonname());
        holder.txtfreelancerstate.setText(lancers.getState());
        holder.txtprojectrange.setText(lancers.getProjectrange());
        holder.txtfeedback.setText(lancers.getFeedback());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent workerDetails = new Intent(mContext, JobDescriptionActivity.class);
                /*workerDetails.putExtra("id",workers.getId());
                workerDetails.putExtra("workers",workers);*/
                mContext.startActivity(workerDetails);

            }
        });


    }

    @Override
    public int getItemCount() {
        return searchPageFreelancerModels.size();
    }
}


