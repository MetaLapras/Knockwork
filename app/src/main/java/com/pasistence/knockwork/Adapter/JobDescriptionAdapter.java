package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Model.InboxDataModel;
import com.pasistence.knockwork.Model.JobDescriptionModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderInboxList;
import com.pasistence.knockwork.ViewHolder.ViewHolderJobDescription;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JobDescriptionAdapter extends RecyclerView.Adapter<ViewHolderJobDescription> {

    Context mContext;
    List<JobDescriptionModel> jobdescriptionlist ;


    public JobDescriptionAdapter(Context mContext, List<JobDescriptionModel> workerList) {
        this.mContext = mContext;
        this.jobdescriptionlist = workerList;
    }

    @NonNull
    @Override
    public ViewHolderJobDescription onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.description_freelancer_template,parent,false);

        return new ViewHolderJobDescription(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderJobDescription holder, int position) {

        final JobDescriptionModel lancers = jobdescriptionlist.get(position);

        Picasso.with(mContext).load(lancers.getDurationimage())
                .into(holder.CircularDurationImage);

        Picasso.with(mContext).load(lancers.getBudgetimage())
                .into(holder.CircularBudgetImage);
        Picasso.with(mContext).load(lancers.getJobtypeimage())
                .into(holder.CircularJobTypeImage);

        holder.txtname.setText(lancers.getName());
        holder.txtpoasted.setText(lancers.getPoasted());
        holder.txtquotes.setText(lancers.getQuotes());
        holder.txtduration.setText(lancers.getDuration());
        holder.txtDurationmonths.setText(lancers.getDurationrange());
        holder.txtbudget.setText(lancers.getBudget());
        holder.txtDurationmonths.setText(lancers.getBudgetrange());
        holder.txtbudgetjobtype.setText(lancers.getJobtype());
        holder.txtpoasted.setText(lancers.getPoastedtype());
        holder.txtdescription.setText(lancers.getDescription());
        holder.txtdescription1.setText(lancers.getDescription1());
        holder.txtdescription2.setText(lancers.getDescription2());
        holder.txtdescription3.setText(lancers.getDescription3());
        holder.txtdescription4.setText(lancers.getDescription4());
        holder.txtrequirdskills.setText(lancers.getRequiredskills());
        holder.txtskill1.setText(lancers.getSkill1());
        holder.txtskill2.setText(lancers.getSkill2());
        holder.txtskill3.setText(lancers.getSkill3());
        holder.txtskill4.setText(lancers.getSkill4());
        /*holder.CircularImageViewProfile.setText(lancers.getLike());
        holder.txtLancerEarned.setText(lancers.getEarning());*/

    }

    @Override
    public int getItemCount() {
        return jobdescriptionlist.size();
    }
}

