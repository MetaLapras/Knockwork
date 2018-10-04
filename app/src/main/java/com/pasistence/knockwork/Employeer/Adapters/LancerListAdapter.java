package com.pasistence.knockwork.Employeer.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Employeer.Models.LancerList;
import com.pasistence.knockwork.Employeer.ViewHolder.ViewHolderFreeLancerList;
import com.pasistence.knockwork.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LancerListAdapter extends RecyclerView.Adapter<ViewHolderFreeLancerList> {

    Context mContext;
    Activity activity;
    List<LancerList> lancerArraylist ;


    public LancerListAdapter(Activity activity, List<LancerList> workerList) {
        this.activity = activity;
        this.lancerArraylist = workerList;
    }

    @NonNull
    @Override
    public ViewHolderFreeLancerList onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_member_templets,parent,false);
        mContext = activity;
        return new ViewHolderFreeLancerList(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFreeLancerList holder, int position) {

        final LancerList lancers = lancerArraylist.get(position);

        Picasso.with(mContext).load(lancers.getImage())
                .into(holder.CircularImageViewProfile);

        holder.txtLancerName.setText(lancers.getName());
        holder.txtLancerState.setText(lancers.getCountry());
        holder.txtLancerDescription.setText(lancers.getDescription());
        holder.txtLancerLike.setText(lancers.getLike());
        holder.txtLancerEarned.setText(lancers.getEarning());

    }

    @Override
    public int getItemCount() {
        return lancerArraylist.size();
    }
}
