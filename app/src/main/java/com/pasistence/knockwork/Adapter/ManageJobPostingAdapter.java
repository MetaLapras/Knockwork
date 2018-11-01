package com.pasistence.knockwork.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.choota.dev.ctimeago.TimeAgo;
import com.pasistence.knockwork.Client.Activities.ClientJobPostingActivity;
import com.pasistence.knockwork.Client.Activities.ManageJobPostActivity;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Interface.ItemClickListener;
import com.pasistence.knockwork.LoginActivity;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.Model.ManageJobPostingModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.ViewHolder.ViewHolderMnageJobPosting;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageJobPostingAdapter extends RecyclerView.Adapter<ViewHolderMnageJobPosting> implements ItemClickListener {


    private static final String TAG = "managePostingAdapter";
    Context mContext;
    Activity activity;
    ArrayList<ApiPostJobResponse.Result> manageJobPostingModels ;
    ApiPostJobResponse.Result job;
    MyApi mServices;

    public ManageJobPostingAdapter(Context mContext,Activity activity, ArrayList<ApiPostJobResponse.Result> workerList) {
        this.mContext = mContext;
        this.manageJobPostingModels = workerList;
        this.activity = activity;
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

        holder.txtjobName.setText(job.getTitle());
        holder.txtfixedPrice.setText(job.getType());
        holder.txtpriceRange.setText(job.getRate());
        //holder.txtpoastedDays.setText(job.getUpdatedAt());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sdf.parse(job.getUpdatedAt());

            TimeAgo timeAgo = new TimeAgo();
            String result = timeAgo.getTimeAgo(d);


            holder.txtpoastedDays.setText(result);

        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }




        holder.txtjobQuotes.setText(job.getDuration());
        holder.txtjobDescription.setText(job.getDetails());

        final ArrayList<ApiPostJobResponse.Result> intentList = new ArrayList<ApiPostJobResponse.Result>();
        intentList.add(job);


        holder.btnJobEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ClientJobPostingActivity.class);
                intent.putExtra("type",Common.update);
                intent.putExtra("Jobs",manageJobPostingModels.get(position));
                mContext.startActivity(intent);
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
        if(!job.getPId().equals(null) && !job.getCid().equals(null) && !job.getUid().equals(null))
        {
            try {
                //Common.dismissSpotDilogue();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setMessage("Are you Sure Want to Delete")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mServices.ClientPostAJobDelete(job.getPId(),job.getUid(),job.getCid()).enqueue(new Callback<ApiPostJobResponse>() {
                                    @Override
                                    public void onResponse(Call<ApiPostJobResponse> call, Response<ApiPostJobResponse> response) {
                                        response.body().getMessage();
                                        final ApiPostJobResponse result = response.body();
                                        Log.e(TAG, result.toString());
                                        if(!result.getError()){
                                            manageJobPostingModels.remove(position);
                                            notifyItemRemoved(position);
                                            notifyItemRangeChanged(position,manageJobPostingModels.size());
                                            notifyDataSetChanged();
                                            //Intent intent = new Intent(mContext,ManageJobPostActivity.class);
                                            //ActivityOptions options = ActivityOptions.makeCustomAnimation(mContext,android.R.anim.fade_in,android.R.anim.fade_out);
                                            //mContext.startActivity(intent,options.toBundle());
                                            //activity.finish();
                                            //mContext.startActivity(new Intent(mContext,ManageJobPostActivity.class));

                                        }else if(result.getError()){
                                            Common.commonDialog(mContext,result.getMessage());
                                            Common.dismissSpotDilogue();
                                        }else {
                                            Common.commonDialog(mContext,"Sever not found..");
                                            Common.dismissSpotDilogue();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ApiPostJobResponse> call, Throwable t) {
                                        Log.e(TAG, t.getMessage() );
                                        t.printStackTrace();
                                        Common.dismissSpotDilogue();
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
