package com.pasistence.knockwork.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pasistence.knockwork.Client.Activities.ClientJobPostingActivity;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Freelancer.Activities.SubmitProposalActivity;
import com.pasistence.knockwork.Interface.ItemClickListener;
import com.pasistence.knockwork.Model.ApiResponse.ApiGetProposals;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.ViewHolder.ViewHolderMnageJobPosting;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageProposalAdapter extends RecyclerView.Adapter<ViewHolderMnageJobPosting> implements ItemClickListener {


    private static final String TAG = "managePostingAdapter";
    Context mContext;
    ArrayList<ApiGetProposals> proposalsArrayList ;
    ApiGetProposals proposals;
    MyApi mServices;
    String updatedat;

    public ManageProposalAdapter(Context mContext, ArrayList<ApiGetProposals> mList) {
        this.mContext = mContext;
        this.proposalsArrayList = mList;
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

        proposals = proposalsArrayList.get(position);

        holder.txtjobName.setText(proposals.getTitle());
        holder.txtfixedPrice.setText(proposals.getDuration());
        holder.txtpriceRange.setText(proposals.getRate());
        updatedat = proposals.getUpdatedat();


        try {

            if(proposals.getUpdatedat()!= null){

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                long time = sdf.parse(updatedat).getTime();
                long now = System.currentTimeMillis();

                CharSequence result =
                        DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);

                holder.txtpoastedDays.setText(result);
            }else {

            }


        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }
        catch (Exception ex) {
            Log.v("Exception", ex.getMessage());
        }

        holder.txtjobQuotes.setText(proposals.getType());
        holder.txtjobDescription.setText(proposals.getDetails());

        final ArrayList<ApiGetProposals> intentList = new ArrayList<ApiGetProposals>();
        intentList.add(proposals);


        holder.btnJobEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,SubmitProposalActivity.class);
                intent.putExtra("proposal",Common.PROPOSAL);
                intent.putExtra("status",Common.update);
                intent.putExtra("jobs",proposalsArrayList.get(position));
                mContext.startActivity(intent);


               /* Intent intent = new Intent(mContext,ClientJobPostingActivity.class);
                intent.putExtra("type",Common.update);
                intent.putExtra("proposal", (Serializable) proposalsArrayList.get(position));
                mContext.startActivity(intent);*/
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
        if(!proposals.getId().equals(null) && !proposals.getJobid().equals(null))
        {
            try {
                //Common.dismissSpotDilogue();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                alertDialogBuilder.setMessage("Are you Sure Want to Delete")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

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
        return proposalsArrayList.size();
    }


    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        if (view.getId() == R.id.btn_job_remove){

            Toast.makeText(mContext, "Removed,....", Toast.LENGTH_SHORT).show();

        }
    }
}