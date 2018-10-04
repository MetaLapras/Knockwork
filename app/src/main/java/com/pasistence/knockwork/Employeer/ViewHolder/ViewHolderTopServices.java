package com.pasistence.knockwork.Employeer.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pasistence.knockwork.Employeer.Interfaces.ItemClickListener;
import com.pasistence.knockwork.R;

public class ViewHolderTopServices extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtHead,txtContent;
    public ImageView imgLogo,imgTopServices;

    private ItemClickListener itemClickListener;


    public ViewHolderTopServices(@NonNull View itemView) {
        super(itemView);
        imgLogo = (ImageView)itemView.findViewById(R.id.img_top_service_logo);
        imgTopServices = (ImageView)itemView.findViewById(R.id.img_top_services);
        txtHead = (TextView)itemView.findViewById(R.id.txt_head);
        txtContent = (TextView)itemView.findViewById(R.id.txt_content);

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
