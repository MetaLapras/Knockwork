package com.pasistence.knockwork.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pasistence.knockwork.R;

public class ViewHolderHireJobs extends RecyclerView.ViewHolder{

    public TextView txtJobTitle,txtJobDescription,txtHire;

    public ViewHolderHireJobs(@NonNull View itemView){
        super(itemView);
        txtJobTitle            = (TextView)itemView.findViewById(R.id.job_title);
        txtJobDescription           = (TextView)itemView.findViewById(R.id.job_description);
        txtHire          = (TextView)itemView.findViewById(R.id.inbox_i_can_do);
    }
}
