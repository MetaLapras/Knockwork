package com.pasistence.knockwork.Employeer.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pasistence.knockwork.Employeer.Activities.InboxActivity;
import com.pasistence.knockwork.Employeer.Models.InboxDataModel;
import com.pasistence.knockwork.R;

import java.util.ArrayList;

public class InboxCustomeAdapter extends RecyclerView.Adapter<InboxCustomeAdapter.MyViewHolder> {

    private ArrayList<InboxDataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.inbox_member_name);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.inbx_member_description);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.inbox_image_view);
        }
    }

    public InboxCustomeAdapter(ArrayList<InboxDataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_inbox_member_list, parent, false);

        view.setOnClickListener(InboxActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
       // ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewVersion.setText(dataSet.get(listPosition).getVersion());
       // imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
