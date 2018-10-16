package com.pasistence.knockwork.ViewHolder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pasistence.knockwork.Client.Activities.ChattingActivity;
import com.pasistence.knockwork.Client.Activities.ClientJobRequest;
import com.pasistence.knockwork.Client.Activities.LancersActivity;
import com.pasistence.knockwork.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderFreeLancerList extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            /*Snackbar.make(v, "Hire Lancer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
            v.getContext().startActivity(new Intent(v.getContext(),ClientJobRequest.class));
        }
        if(v == btnLancerMessage){
            /*Snackbar.make(v, "Message Lancer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
            v.getContext().startActivity(new Intent(v.getContext(),ChattingActivity.class));
        }
    }
}
