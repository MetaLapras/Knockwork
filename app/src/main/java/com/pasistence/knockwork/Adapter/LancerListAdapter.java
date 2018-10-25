package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class LoadingViewholder extends RecyclerView.ViewHolder{

    public ProgressBar progressBar;

    public LoadingViewholder(@NonNull View itemView) {
        super(itemView);
        progressBar = (ProgressBar)itemView.findViewById(R.id.progressbar);
    }
}
 /*class ViewHolderFreeLancerList extends RecyclerView.ViewHolder implements View.OnClickListener {

    public CircleImageView CircularImageViewProfile;
    public TextView txtLancerName,txtLancerState,txtLancerEarned,txtLancerLike,txtLancerDescription;
    public Button btnLancerMessage,btnLancerHire;

    public ViewHolderFreeLancerList(@NonNull View itemView) {
        super(itemView);

        CircularImageViewProfile = (CircleImageView)itemView.findViewById(R.id.circularImage_profile);
        txtLancerName            = (TextView)itemView.findViewById(R.id.txt_name_lancer);
        txtLancerState           = (TextView)itemView.findViewById(R.id.txt_lancer_state);
        txtLancerEarned          = (TextView)itemView.findViewById(R.id.txt_lancer_earing);
        txtLancerLike            = (TextView)itemView.findViewById(R.id.txt_lancer_per);
        txtLancerDescription     = (TextView)itemView.findViewById(R.id.txt_description_lancer);

        btnLancerHire            = (Button)itemView.findViewById(R.id.btn_hire_lancer);
        btnLancerMessage         = (Button)itemView.findViewById(R.id.btn_message_lancer);

        btnLancerHire.setOnClickListener(this);
        btnLancerMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLancerHire){
            *//*Snackbar.make(v, "Hire Lancer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*//*
            v.getContext().startActivity(new Intent(v.getContext(),ClientJobRequest.class));
        }
        if(v == btnLancerMessage){
            *//*Snackbar.make(v, "Message Lancer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*//*
            v.getContext().startActivity(new Intent(v.getContext(),ChattingActivity.class));
        }
    }
}*/


public class LancerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "lanceradapter";
    public Context mContext;
    List<ApiResponseLancer.Result> lancerArraylist ;
    int totalItemCount,lastVisibleItem;
    int visibleThreshold;
    boolean isLoading;

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    ILoadMore loadMore;


    public LancerListAdapter(Context mContext, List<ApiResponseLancer.Result> workerList) {
        this.mContext = mContext;
        this.lancerArraylist = workerList;
    }

    public LancerListAdapter(Context mContext, List<ApiResponseLancer.Result> workerList,RecyclerView recyclerView) {
        this.mContext = mContext;
        this.lancerArraylist = workerList;

        final LinearLayoutManager LayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleThreshold = LayoutManager.getChildCount();
                totalItemCount = LayoutManager.getItemCount();
                lastVisibleItem = LayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <=(lastVisibleItem+visibleThreshold)){
                    if(loadMore != null){
                        loadMore.onLoadMore();
                        isLoading = true;
                    }

                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return lancerArraylist.get(position)== null?VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if(i==VIEW_TYPE_ITEM){
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custome_member_templets,parent,false);

            return new ViewHolderFreeLancerList(itemView);
        }else if(i==VIEW_TYPE_LOADING) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_item_loading,parent,false);

            return new ViewHolderFreeLancerList(itemView);
        }


        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if(holder instanceof ViewHolderFreeLancerList){

            ViewHolderFreeLancerList viewHolder = (ViewHolderFreeLancerList) holder;
            final ApiResponseLancer.Result lancers = lancerArraylist.get(position);

            Picasso.with(mContext).load(lancers.getImageUrl())
                    .into(viewHolder.CircularImageViewProfile);

            viewHolder.txtLancerName.setText(lancers.getFirstName()+" "+lancers.getLastName());
            viewHolder.txtLancerState.setText(lancers.getCountry());
            viewHolder.txtLancerDescription.setText(lancers.getDescription());
            //   holder.txtLancerLike.setText(lancers.getLike());
            viewHolder.txtLancerEarned.setText(lancers.getEarning());
        }else if(holder instanceof LoadingViewholder){
            LoadingViewholder viewholder = (LoadingViewholder)holder;
            viewholder.progressBar.setIndeterminate(true);
        }



    }

    @Override
    public int getItemCount() {
        return lancerArraylist.size();
    }

    public void addLancers(){
        notifyDataSetChanged();
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    public void setLoaded() {
        isLoading = false;
    }
}
