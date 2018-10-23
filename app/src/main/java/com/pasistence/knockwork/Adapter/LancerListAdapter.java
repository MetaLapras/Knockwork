package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Model.ApiResponse.ApiResponseLancer;
import com.pasistence.knockwork.Model.LancerListModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderFreeLancerList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LancerListAdapter extends RecyclerView.Adapter<ViewHolderFreeLancerList> {

    public Context mContext;
    List<ApiResponseLancer.Result> lancerArraylist ;


    public LancerListAdapter(Context mContext, List<ApiResponseLancer.Result> workerList) {
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

        final ApiResponseLancer.Result lancers = lancerArraylist.get(position);

        Picasso.with(mContext).load(lancers.getImageUrl())
                .into(holder.CircularImageViewProfile);

        holder.txtLancerName.setText(lancers.getFirstName()+lancers.getLastName());
        holder.txtLancerState.setText(lancers.getCountry());
        holder.txtLancerDescription.setText(lancers.getDescription());
     //   holder.txtLancerLike.setText(lancers.getLike());
        holder.txtLancerEarned.setText(lancers.getEarning());

    }

    @Override
    public int getItemCount() {
        return lancerArraylist.size();
    }
}
