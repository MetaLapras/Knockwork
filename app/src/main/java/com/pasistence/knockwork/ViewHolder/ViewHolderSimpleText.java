package com.pasistence.knockwork.ViewHolder;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pasistence.knockwork.Interface.ItemClickListener;
import com.pasistence.knockwork.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderSimpleText extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView txtSmallSuggestion;
    private ItemClickListener itemClickListener;

    public ViewHolderSimpleText(@NonNull View itemView) {
        super(itemView);

        txtSmallSuggestion           = (TextView)itemView.findViewById(R.id.txt_sub_cat);
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