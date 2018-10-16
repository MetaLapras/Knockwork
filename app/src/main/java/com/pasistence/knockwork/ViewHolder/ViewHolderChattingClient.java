package com.pasistence.knockwork.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.pasistence.knockwork.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderChattingClient extends RecyclerView.ViewHolder  {

    public CircleImageView CircularImageViewProfile;
    public TextView txtName,txtmessage;
    public View chattingview;
    public LinearLayout linearLayout;


    public ViewHolderChattingClient(@NonNull View itemView) {
        super(itemView);

        CircularImageViewProfile = (CircleImageView)itemView.findViewById(R.id.chatting_image_view);
        txtName            = (TextView)itemView.findViewById(R.id.Chatting_member_name);
        txtmessage           = (TextView)itemView.findViewById(R.id.chatting_message);
        chattingview          = (View) itemView.findViewById(R.id.chatting_view);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.chatting_linear_layout);

    }
}
