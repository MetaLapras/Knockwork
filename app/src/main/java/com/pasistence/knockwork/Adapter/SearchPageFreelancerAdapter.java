package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Freelancer.Activities.FreelancerJobDescriptionActivity;
import com.pasistence.knockwork.Freelancer.Activities.SubmitProposalActivity;
import com.pasistence.knockwork.Model.SearchPageFreelancerModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderSearchPageFreelancer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchPageFreelancerAdapter  extends RecyclerView.Adapter<ViewHolderSearchPageFreelancer> {

    /*private Context mContext;
    private List<JobList> jobLists;

    public SearchPageFreelancerAdapter(Context mContext, List<JobList> searchList) {
        this.mContext = mContext;
        this.jobLists = searchList;
    }*/


    Context mContext;
    List<SearchPageFreelancerModel> searchPageFreelancerModels ;


    public SearchPageFreelancerAdapter(Context mContext, List<SearchPageFreelancerModel> searchpagelist) {
        this.mContext = mContext;
        this.searchPageFreelancerModels = searchpagelist;
    }

   /* public SearchPageFreelancerAdapter(Context mContext, ArrayList<SearchPageFreelancerActivity> searchPageListModels) {
    }

*/
    @NonNull
    @Override
    public ViewHolderSearchPageFreelancer onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_search_page_freelancer_template,parent,false);

        return new ViewHolderSearchPageFreelancer(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderSearchPageFreelancer holder, int position) {

        final SearchPageFreelancerModel search = searchPageFreelancerModels.get(position);
        Picasso.with(mContext).load(search.getImage())
                .into(holder.img);


        holder.txtjobName.setText(search.getJobname());
        holder.txtfixedPrice.setText(search.getFixedprice());
        holder.txtpriceRange.setText(search.getPricerange());
        holder.txtpoastedDays.setText(search.getPoasteddays());
        holder.txtjobQuotes.setText(search.getQuotes());
        holder.txtjobDescription.setText(search.getDescription());
        holder.txtnamefreelancer.setText(search.getPersonname());
        holder.txtfreelancerstate.setText(search.getState());
        holder.txtprojectrange.setText(search.getProjectrange());
        holder.txtfeedback.setText(search.getFeedback());

        holder.btnapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(mContext,SubmitProposalActivity.class);
                intent.putExtra("title",holder.txtjobName.getText());
                mContext.startActivity(intent);
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent workerDetails = new Intent(mContext, FreelancerJobDescriptionActivity.class);
                mContext.startActivity(workerDetails);

            }
        });


    }

   @Override
    public int getItemCount() {
       return searchPageFreelancerModels.size();
        //return jobLists.size();
        //return searchPageListModel;
    }
}


