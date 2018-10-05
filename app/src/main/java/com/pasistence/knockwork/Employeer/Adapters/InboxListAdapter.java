package com.pasistence.knockwork.Employeer.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Employeer.Models.InboxDataModel;
import com.pasistence.knockwork.Employeer.Models.LancerList;
import com.pasistence.knockwork.Employeer.ViewHolder.ViewHolderFreeLancerList;
import com.pasistence.knockwork.Employeer.ViewHolder.ViewHolderInboxList;
import com.pasistence.knockwork.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InboxListAdapter extends RecyclerView.Adapter<ViewHolderInboxList> {

    Context mContext;
    Activity activity;
    List<InboxDataModel> inboxArraylist ;


    public InboxListAdapter(Activity activity, List<InboxDataModel> workerList) {
        this.activity = activity;
        this.inboxArraylist = workerList;
    }

    @NonNull
    @Override
    public ViewHolderInboxList onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_inbox_member_list,parent,false);
        mContext = activity;
        return new ViewHolderInboxList(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInboxList holder, int position) {

        final InboxDataModel lancers = inboxArraylist.get(position);

        Picasso.with(mContext).load(lancers.getImage())
                .into(holder.CircularImageViewProfile);

        holder.txtInboxListName.setText(lancers.getName());
        holder.txtLancerDescription.setText(lancers.getDescription());
        holder.txtICanDo.setText(lancers.getCanDo());
        /*holder.CircularImageViewProfile.setText(lancers.getLike());
        holder.txtLancerEarned.setText(lancers.getEarning());*/

    }

    @Override
    public int getItemCount() {
        return inboxArraylist.size();
    }
}

