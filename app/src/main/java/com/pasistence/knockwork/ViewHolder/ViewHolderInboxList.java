package com.pasistence.knockwork.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pasistence.knockwork.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderInboxList extends RecyclerView.ViewHolder  {

    public CircleImageView CircularImageViewProfile;
    public TextView txtInboxListName,txtLancerDescription,txtICanDo;


    public ViewHolderInboxList(@NonNull View itemView) {
        super(itemView);

        CircularImageViewProfile = (CircleImageView)itemView.findViewById(R.id.inbox_image_view);
        txtInboxListName            = (TextView)itemView.findViewById(R.id.inbox_member_name);
        txtLancerDescription           = (TextView)itemView.findViewById(R.id.inbx_member_description);
        txtICanDo          = (TextView)itemView.findViewById(R.id.inbox_i_can_do);

    }
}

