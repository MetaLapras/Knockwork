package com.pasistence.knockwork.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Interface.ItemClickListener;
import com.pasistence.knockwork.Model.PopularServicesModel;
import com.pasistence.knockwork.Model.ResponseTopService;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.ViewHolder.ViewHolderPopularServices;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClientPopularServiceAdapter extends RecyclerView.Adapter<ViewHolderPopularServices> {

    Context mContext;
    Activity activity;
    List<PopularServicesModel> popularServicesList ;
    public static String TAG = "adaper -->";
    String workerid,perAddId,curAddId,bankId;
    MyApi mService;

    public ClientPopularServiceAdapter(Activity activity, List<PopularServicesModel> popularServices) {
        this.activity = activity;
        this.popularServicesList = popularServices;
        mContext = activity;
    }

    @NonNull
    @Override
    public ViewHolderPopularServices onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_template_popularservices,parent,false);
        mContext = activity;
        return new ViewHolderPopularServices(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPopularServices viewHolder, int position) {

        final PopularServicesModel model = popularServicesList.get(position);

        viewHolder.txtPopularservice.setText(model.getTitle());
        Picasso.with(activity).load(model.getImage_url())
                .into(viewHolder.imgPopularservice);



        final PopularServicesModel clickitem = model;
       // Log.e("popular", model.toString() );

        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //Toast.makeText(DashboardActivity.this, "", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, ""+clickitem.getTitle(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.e("popular", model.toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return popularServicesList.size();
    }
}
