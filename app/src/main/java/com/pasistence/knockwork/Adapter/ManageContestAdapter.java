package com.pasistence.knockwork.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.choota.dev.ctimeago.TimeAgo;
import com.pasistence.knockwork.Client.Activities.ClientJobPostingActivity;
import com.pasistence.knockwork.Client.Fragments.ClientJobContestFragment;
import com.pasistence.knockwork.Client.Fragments.ClientJobContestSecondFragment;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Freelancer.Fragments.FreelancerSkillsFragment;
import com.pasistence.knockwork.Interface.ItemClickListener;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostContestResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.ViewHolder.ViewHolderMnageJobPosting;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

public class ManageContestAdapter extends RecyclerView.Adapter<ViewHolderMnageJobPosting> implements ItemClickListener {


    private static final String TAG = "managePostingAdapter";
    Context mContext;
    Activity activity;
    ArrayList<ApiPostContestResponse.Result> manageJobPostingModels ;
    ApiPostContestResponse.Result job;
    MyApi mServices;
    FragmentManager fragmentManager;

    public ManageContestAdapter(Context mContext, Activity activity, FragmentManager fragmentManager, ArrayList<ApiPostContestResponse.Result> workerList) {
        this.mContext = mContext;
        this.manageJobPostingModels = workerList;
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        mServices = Common.getApi();
    }

    @NonNull
    @Override
    public ViewHolderMnageJobPosting onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_manage_job_posting,parent,false);
        return new ViewHolderMnageJobPosting(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMnageJobPosting holder, final int position) {

        job = manageJobPostingModels.get(position);

       /* Picasso.with(mContext).load(lancers.getImage())
                .into(holder.CircularImageViewProfile);*/

        holder.txtjobName.setText(job.getContestTitle());
        holder.txtfixedPrice.setText(job.getContestPrizeMoney());
        holder.txtpriceRange.setText(job.getContestDuration());
        //holder.txtpoastedDays.setText(job.getUpdatedAt());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            if(job.getUpdatedAt()!= null){
                Date d = sdf.parse(job.getUpdatedAt());

                TimeAgo timeAgo = new TimeAgo();
                String result = timeAgo.getTimeAgo(d);
            /*TimeAgo timeAgo = new TimeAgo();
            String result = timeAgo.getTimeAgo(d);*/

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

        holder.txtjobQuotes.setText(job.getContestType());
        holder.txtjobDescription.setText(job.getContestDescrtion());

        final ArrayList<ApiPostContestResponse.Result> intentList = new ArrayList<ApiPostContestResponse.Result>();
        intentList.add(job);

        if(!job.getUid().equals(getUid(mContext))){
            holder.btnJobEdit.setVisibility(View.GONE);
            holder.btnJobRemove.setVisibility(View.GONE);
        }else {
            holder.btnJobEdit.setVisibility(View.VISIBLE);
            holder.btnJobRemove.setVisibility(View.VISIBLE);
        }

        holder.btnJobEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

      /*          FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.contest_frame,new ClientJobContestFragment());
                ft.addToBackStack(null);
                ft.commit();*/


                try
                {

                    ClientJobContestFragment fragment = new ClientJobContestFragment();

                    Bundle args = new Bundle();

                    args.putString("type",Common.update);
                    args.putString("pid",manageJobPostingModels.get(position).getContestid());
                    args.putString("uid",manageJobPostingModels.get(position).getUid());
                    args.putString("cid",manageJobPostingModels.get(position).getCid());
                    args.putString("title",manageJobPostingModels.get(position).getContestTitle());
                    args.putString("description",manageJobPostingModels.get(position).getContestDescrtion());
                    args.putString("duration",manageJobPostingModels.get(position).getContestDuration());
                    args.putString("prizemoney",manageJobPostingModels.get(position).getContestPrizeMoney());
                    args.putString("mode",manageJobPostingModels.get(position).getContestMode());
                    args.putString("types",manageJobPostingModels.get(position).getContestType());



                    fragment.setArguments(args);


                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.contest_frame,fragment);
                    ft.addToBackStack(null);
                    ft.commit();

                }catch (Exception e){
                    e.printStackTrace();
                    e.getMessage();
                }

            }
        });

        holder.btnJobRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAt(position);
            }
        });
    }

    private void removeAt(final int position) {
        if(!manageJobPostingModels.get(position).getContestid().equals(null) && !manageJobPostingModels.get(position).getCid().equals(null) && !manageJobPostingModels.get(position).getUid().equals(null))
        {
            try {
                //Common.dismissSpotDilogue();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setMessage("Are you Sure Want to Delete")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                mServices.ClientPostContestDelete(
                                        manageJobPostingModels.get(position).getContestid(),
                                        manageJobPostingModels.get(position).getUid(),
                                        manageJobPostingModels.get(position).getCid()).enqueue(new Callback<ApiPostContestResponse>() {
                                    @Override
                                    public void onResponse(Call<ApiPostContestResponse> call, Response<ApiPostContestResponse> response) {
                                        response.body().getMessage();
                                        final ApiPostContestResponse result = response.body();
                                        Log.e(TAG, result.toString());
                                        if(!result.getError()){

                                            manageJobPostingModels.remove(position);
                                            notifyItemRemoved(position);
                                            notifyItemRangeChanged(position,manageJobPostingModels.size());
                                            notifyDataSetChanged();


                                        }else if(result.getError()){
                                            Common.commonDialog(mContext,result.getMessage());
                                            //Common.dismissSpotDilogue();
                                        }else {
                                            Common.commonDialog(mContext,"Sever not found..");
                                            //Common.dismissSpotDilogue();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ApiPostContestResponse> call, Throwable t) {
                                        Log.e(TAG, t.getMessage() );
                                        t.printStackTrace();
                                        //Common.dismissSpotDilogue();
                                        Common.commonDialog(mContext,"Something went, wrong please try again");
                                    }
                                });

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



            }catch (Exception e)
            {
                Log.e(TAG, e.getMessage() );
                e.printStackTrace();
                Common.commonDialog(mContext,"Sever not found..");
            }
        }
    }

    @Override
    public int getItemCount() {
        return manageJobPostingModels.size();
    }


    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        if (view.getId() == R.id.btn_job_remove){

            Toast.makeText(mContext, "Removed,....", Toast.LENGTH_SHORT).show();

        }
    }
}