package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pasistence.knockwork.Client.Activities.ChattingMessageActivity;
import com.pasistence.knockwork.Freelancer.Activities.JobDescriptionActivity;
import com.pasistence.knockwork.Model.ChattingModel;
import com.pasistence.knockwork.Model.InboxDataModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderChattingClient;
import com.pasistence.knockwork.ViewHolder.ViewHolderInboxList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChattingAdapter  extends RecyclerView.Adapter<ViewHolderChattingClient> {

    Context mContext;
    List<ChattingModel> chattingModels ;



    public ChattingAdapter(Context mContext, List<ChattingModel> workerList) {
        this.mContext = mContext;
        this.chattingModels = workerList;
    }

    @NonNull
    @Override
    public ViewHolderChattingClient onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_chatting_template,parent,false);

        return new ViewHolderChattingClient(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderChattingClient holder, int position) {

        final ChattingModel lancers = chattingModels.get(position);

        Picasso.with(mContext).load(lancers.getImage())
                .into(holder.CircularImageViewProfile);

        holder.txtName.setText(lancers.getName());
        holder.txtmessage.setText(lancers.getMessage());
        holder.chattingview.setVisibility(View.VISIBLE);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chattingintent = new Intent(mContext, ChattingMessageActivity.class);
                mContext.startActivity(chattingintent);
            }
        });
        /*holder.CircularImageViewProfile.setText(lancers.getLike());
        holder.txtLancerEarned.setText(lancers.getEarning());*/

    }

    @Override
    public int getItemCount() {
        return chattingModels.size();
    }
}

