package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Freelancer.Activities.JobDescriptionActivity;
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
    public void onBindViewHolder(@NonNull ViewHolderSearchPageFreelancer holder, int position) {

       /* final JobList lancers = jobLists.get(position);

        holder.txtjobName.setText(lancers.getJd_title());
        holder.txtfixedPrice.setText(lancers.getJd_price_type());
        holder.txtpriceRange.setText(lancers.getJd_price());
        //holder.txtpoastedDays.setText(lancers.getPoasteddays());
        holder.txtjobQuotes.setText(lancers.getJd_quotes());
        holder.txtjobDescription.setText(lancers.getJd_description());
        holder.txtnamefreelancer.setText((CharSequence) lancers.getJd_client());*/

       /* final SearchPageListModel searchModel = searchPageListModel;


        page = searchModel.getPage();
        count = searchModel.getCount();
        totalcount = searchModel.getTotal_count();*/
       // joblist = String.valueOf(searchModel.getJob_list());


        /*mMyServices = Common.getApi();
        mMyServices = RetrofitClient.getClient("").create(MyApi.class);
        Call<List<SearchPageListModel>> call = mMyServices.getIP();


        ///call.enqueue(new Callback<List<SearchPageListModel>>() {


      call.enqueue(new Callback<List<SearchPageListModel>>() {
            @Override
            public void onResponse(Call<List<SearchPageListModel>> call, Response<List<SearchPageListModel>> response) {
               SearchPageListModel result = (SearchPageListModel) response.body();
                if (result.isError())
                {
                    Toast.makeText(mContext, "not done", Toast.LENGTH_SHORT).show();


                    Toast.makeText(mContext, "done", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<List<SearchPageListModel>> call, Throwable t) {
                Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();

            }


        });*/

        //searchModel.set(workerList.get(position).getId());

        // holder.txtfreelancerstate.setText(lancers.getState());

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
        //return jobLists.size();
        //return searchPageListModel;
    }
}


