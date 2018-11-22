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
import com.pasistence.knockwork.Model.ApiResponse.ApiSkillsResponse;
import com.pasistence.knockwork.Model.PopularServicesModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.ViewHolder.ViewHolderPopularServices;
import com.pasistence.knockwork.ViewHolder.ViewHolderSimpleText;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SmallSuggestionAdapter extends RecyclerView.Adapter<ViewHolderSimpleText> {

    Context mContext;
    Activity activity;
    List<ApiSkillsResponse> smallSuggestions ;
    public static String TAG = "adaper -->";
    String workerid,perAddId,curAddId,bankId;
    MyApi mService;

    public SmallSuggestionAdapter(Activity activity, List<ApiSkillsResponse> smallSuggestions) {
        this.activity = activity;
        this.smallSuggestions = smallSuggestions;
        mContext = activity;
    }

    @NonNull
    @Override
    public ViewHolderSimpleText onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_template_simple_text,parent,false);
        mContext = activity;
        return new ViewHolderSimpleText(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSimpleText viewHolder, int position) {

        final ApiSkillsResponse model = smallSuggestions.get(position);

        viewHolder.txtSmallSuggestion.setText(model.getName());


        final ApiSkillsResponse clickitem = model;
        // Log.e("popular", model.toString() );

        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //Toast.makeText(DashboardActivityClient.this, "", Toast.LENGTH_SHORT).show();
                Snackbar.make(view, ""+clickitem.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.e("popular", model.toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return smallSuggestions.size();
    }
}