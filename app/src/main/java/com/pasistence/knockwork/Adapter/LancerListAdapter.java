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
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderFreeLancerList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LancerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "lanceradapter";
    public Context mContext;
    ArrayList<ApiResponseLancer.Result> lancerArraylist ;

    public LancerListAdapter(Context mContext, ArrayList<ApiResponseLancer.Result> workerList) {
        this.mContext = mContext;
        this.lancerArraylist = workerList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custome_member_templets,parent,false);

            return new ViewHolderFreeLancerList(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            ViewHolderFreeLancerList viewHolder = (ViewHolderFreeLancerList) holder;
            final ApiResponseLancer.Result lancers = lancerArraylist.get(position);

            Picasso.with(mContext).load(lancers.getImageUrl())
                    .into(viewHolder.CircularImageViewProfile);

            viewHolder.txtLancerName.setText(lancers.getFirstName()+" "+lancers.getLastName());
            viewHolder.txtLancerState.setText(lancers.getCountry());
            viewHolder.txtLancerDescription.setText(lancers.getDescription());
            //   holder.txtLancerLike.setText(lancers.getLike());
            viewHolder.txtLancerEarned.setText(lancers.getEarning());

    }

    @Override
    public int getItemCount() {
        return lancerArraylist.size();
    }

    public void addLancers(ArrayList<ApiResponseLancer.Result>results){
        int oldsize = lancerArraylist.size();
        int newsize = 0;
        for(int i=0;i<results.size();i++){
            lancerArraylist.add(results.get(i));
            newsize = lancerArraylist.size();
        }
        notifyItemInserted(newsize-oldsize+1);
        Log.e(TAG, lancerArraylist.toString() );
        Log.e(TAG, lancerArraylist.size()+"" );
    }

}
