package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choota.dev.ctimeago.TimeAgo;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Freelancer.Activities.FreelancerJobDescriptionActivity;
import com.pasistence.knockwork.Freelancer.Activities.SubmitProposalActivity;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.Model.SearchPageFreelancerModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderSearchPageFreelancer;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class SearchPageFreelancerAdapter  extends RecyclerView.Adapter<ViewHolderSearchPageFreelancer> {

    /*private Context mContext;
    private List<JobList> jobLists;

    public SearchPageFreelancerAdapter(Context mContext, List<JobList> searchList) {
        this.mContext = mContext;
        this.jobLists = searchList;
    }*/

    String updatedat;

    Context mContext;
    ArrayList<ApiPostJobResponse.Result> searchPageFreelancerModels ;


    public SearchPageFreelancerAdapter(Context mContext,  ArrayList<ApiPostJobResponse.Result> searchpagelist) {
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
    public void onBindViewHolder(@NonNull final ViewHolderSearchPageFreelancer holder, final int position) {

        final ApiPostJobResponse.Result search = searchPageFreelancerModels.get(position);

        Picasso.with(mContext).load(search.getProfileImage())
                .into(holder.img);

        holder.txtjobName.setText(search.getTitle());
        holder.txtfixedPrice.setText(search.getType());
        holder.txtpriceRange.setText(search.getRate());
        //holder.txtpoastedDays.setText(search.getUpdatedAt());
        updatedat = search.getUpdatedAt();
        holder.txtjobQuotes.setText("quotes");
        holder.txtjobDescription.setText(search.getDetails());
        holder.txtnamefreelancer.setText(search.getCname());
        holder.txtfreelancerstate.setText("state");
        holder.txtprojectrange.setText("spending");
        holder.txtfeedback.setText("review");


        holder.btnapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SubmitProposalActivity.class);
                intent.putExtra("proposal",Common.PROPOSAL);
                intent.putExtra("jobs",searchPageFreelancerModels.get(position));
                mContext.startActivity(intent);
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FreelancerJobDescriptionActivity.class);
                intent.putExtra("details",Common.JOB);
                intent.putExtra("jobs",searchPageFreelancerModels.get(position));
                mContext.startActivity(intent);

            }
        });

       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            if(search.getUpdatedAt()!= null){
              /*  Date d = sdf.parse(search.getUpdatedAt());

                TimeAgo timeAgo = new TimeAgo();
                String result = timeAgo.getTimeAgo(d);*/

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                long time = sdf.parse(updatedat).getTime();
                long now = System.currentTimeMillis();

                CharSequence result =
                        DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);


                holder.txtpoastedDays.setText(result);
            }else {

            }

            // holder.txtpoastedDays.setText(result);

        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }
        catch (Exception ex) {
            Log.v("Exception", ex.getMessage());
        }


    }

   @Override
    public int getItemCount() {
       return searchPageFreelancerModels.size();
        //return jobLists.size();
        //return searchPageListModel;
    }
}


