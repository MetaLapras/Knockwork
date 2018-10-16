package com.pasistence.knockwork.ViewHolder;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pasistence.knockwork.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderSearchPageFreelancer    extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtjobName,txtfixedPrice,txtpriceRange,txtpoastedDays,txtjobQuotes,txtjobDescription,txtnamefreelancer,txtfreelancerstate,txtprojectrange,txtfeedback;
    public Button btnapply;
    public CircleImageView img;
   public LinearLayout linearLayout;

    public ViewHolderSearchPageFreelancer(@NonNull View itemView) {
        super(itemView);


        txtjobName           = (TextView)itemView.findViewById(R.id.freelancer_job_name);
        txtfixedPrice        = (TextView)itemView.findViewById(R.id.txt_freelancer_fixed_price);
        txtpriceRange        = (TextView)itemView.findViewById(R.id.txt_freelancer_price_range);
        txtpoastedDays       = (TextView)itemView.findViewById(R.id.txt_freelancer_job_posting);
        txtjobQuotes         = (TextView)itemView.findViewById(R.id.txt_freelancer_quotes);
        txtjobDescription    = (TextView)itemView.findViewById(R.id.txt_freelancer_job_description);
        txtnamefreelancer    = (TextView)itemView.findViewById(R.id.txt_name_freelancer);
        txtfreelancerstate    = (TextView)itemView.findViewById(R.id.txt_freelancer_state);
        txtprojectrange    = (TextView)itemView.findViewById(R.id.txt_freelancer_earing);
        txtfeedback    = (TextView)itemView.findViewById(R.id.txt_freelancer_per);
        img = (CircleImageView)itemView.findViewById(R.id.freelancer_image_profile);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.linear_search);

        btnapply           = (Button)itemView.findViewById(R.id.btn_apply);

        btnapply.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnapply){
            Snackbar.make(v, "Edit Job", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        /*if(v == btnJobRemove){
            Snackbar.make(v, "Message Remove", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }*/
    }
}

