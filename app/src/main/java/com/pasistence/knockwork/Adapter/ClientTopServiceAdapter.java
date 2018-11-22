package com.pasistence.knockwork.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pasistence.knockwork.Interface.ItemClickListener;
import com.pasistence.knockwork.Model.ResponseTopService;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.SubCategoryActivity;
import com.pasistence.knockwork.ViewHolder.ViewHolderPopularServices;
import com.pasistence.knockwork.ViewHolder.ViewHolderTopServices;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClientTopServiceAdapter extends RecyclerView.Adapter<ViewHolderTopServices> {

    Context mContext;
    Activity activity;
    List<ResponseTopService> popularServicesList ;
    public static String TAG = "adaper -->";
    String workerid,perAddId,curAddId,bankId;
    MyApi mService;

    public ClientTopServiceAdapter(Activity activity, List<ResponseTopService> popularServices) {
        this.activity = activity;
        this.popularServicesList = popularServices;
        mContext = activity;
    }

    @NonNull
    @Override
    public ViewHolderTopServices onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_template_topservices,parent,false);
        mContext = activity;
        return new ViewHolderTopServices(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTopServices viewHolder, int position) {

        final ResponseTopService model = popularServicesList.get(position);

        viewHolder.txtHead.setText(model.getCategories_title());
        viewHolder.txtContent.setText(model.getCategories_description());
        Picasso.with(activity).load(model.getCategories_image_url())
                .into(viewHolder.imgTopServices);

        final ResponseTopService clickitem = model;
        Log.e("top", model.toString() );

        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //Toast.makeText(DashboardActivityClient.this, "", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, ""+clickitem.getCategories_title(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.e("popular", model.toString());

                Intent intent = new Intent(activity,SubCategoryActivity.class);
                intent.putExtra("title",clickitem.getCategories_title());
                intent.putExtra("image",clickitem.getCategories_image_url());
                intent.putExtra("id",clickitem.getCategories_id());

                activity.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return popularServicesList.size();
    }
}