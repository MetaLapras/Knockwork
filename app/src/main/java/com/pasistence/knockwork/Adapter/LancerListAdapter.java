package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pasistence.knockwork.Client.Activities.ChattingActivity;
import com.pasistence.knockwork.Client.Activities.ClientJobRequest;
import com.pasistence.knockwork.Interface.ILoadMore;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderFreeLancerList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LancerListAdapter extends RecyclerView.Adapter<ViewHolderFreeLancerList> {

    private static final String TAG = "lanceradapter";
    public Context mContext;
    ArrayList<ApiResponseRegisterLancer.Lancer> lancerArraylist ;

    public LancerListAdapter(Context mContext, ArrayList<ApiResponseRegisterLancer.Lancer> workerList) {
        this.mContext = mContext;
        this.lancerArraylist = workerList;
    }

    @NonNull
    @Override
    public ViewHolderFreeLancerList onCreateViewHolder(@NonNull ViewGroup parent, int i) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custome_member_templets,parent,false);
            return new ViewHolderFreeLancerList(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFreeLancerList holder, int position) {


            final ApiResponseRegisterLancer.Lancer lancers = lancerArraylist.get(position);

            Picasso.with(mContext).load(lancers.getLancerImage())
                    .into(holder.CircularImageViewProfile);

            holder.txtLancerName.setText(lancers.getLancerName());
            holder.txtLancerState.setText(lancers.getLancerGender());
            holder.txtLancerDescription.setText(lancers.getLancerSelfIntro());
                //   holder.txtLancerLike.setText(lancers.getLike());
            holder.txtLancerEarned.setText(lancers.getLancerMinHourRate());

    }

    @Override
    public int getItemCount() {
        return lancerArraylist.size();
    }

}
