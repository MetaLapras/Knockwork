package com.pasistence.knockwork.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pasistence.knockwork.Interface.ItemClickListener;
import com.pasistence.knockwork.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderLancerProposal extends RecyclerView.ViewHolder implements View.OnClickListener {

    public CircleImageView CircularImageViewProfile;
    public TextView txtLancerName,txtLancerState,txtLancerEarned,txtLancerLike,txtLancerDescription,txtTitle,txtCoverletter;
    public Button btnLancerMessage,btnLancerHire;
    private ItemClickListener itemClickListener;


    public ViewHolderLancerProposal(@NonNull View itemView) {
        super(itemView);

        CircularImageViewProfile = (CircleImageView)itemView.findViewById(R.id.circularImage_profile);
        txtLancerName            = (TextView)itemView.findViewById(R.id.txt_name_lancer);
        txtLancerState           = (TextView)itemView.findViewById(R.id.txt_lancer_state);
        txtLancerEarned          = (TextView)itemView.findViewById(R.id.txt_lancer_earing);
        txtLancerLike            = (TextView)itemView.findViewById(R.id.txt_lancer_per);
        txtLancerDescription     = (TextView)itemView.findViewById(R.id.txt_description_lancer);
        txtTitle                 = (TextView)itemView.findViewById(R.id.job_title);
        txtCoverletter           = (TextView)itemView.findViewById(R.id.job_coverletter);

        btnLancerHire            = (Button)itemView.findViewById(R.id.btn_hire_lancer);
        btnLancerMessage         = (Button)itemView.findViewById(R.id.btn_message_lancer);

        btnLancerHire.setOnClickListener(this);
        btnLancerMessage.setOnClickListener(this);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnLancerHire){
            /*Snackbar.make(v, "Hire Lancer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
            //v.getContext().startActivity(new Intent(v.getContext(),ClientHireActivity.class));
        }
        if(v == btnLancerMessage){
            /*Snackbar.make(v, "Message Lancer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
           // v.getContext().startActivity(new Intent(v.getContext(),CustomLayoutMessagesActivity.class));
        }

        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
