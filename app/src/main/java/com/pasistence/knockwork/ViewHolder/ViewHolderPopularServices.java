package com.pasistence.knockwork.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pasistence.knockwork.Interfaces.ItemClickListener;
import com.pasistence.knockwork.R;

public class ViewHolderPopularServices extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtPopularservice;
    public ImageView imgPopularservice;

    private ItemClickListener itemClickListener;

    public ViewHolderPopularServices(View itemView) {
        super(itemView);

        imgPopularservice = (ImageView)itemView.findViewById(R.id.img_popular_service);
        txtPopularservice = (TextView)itemView.findViewById(R.id.txt_popular_service);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
