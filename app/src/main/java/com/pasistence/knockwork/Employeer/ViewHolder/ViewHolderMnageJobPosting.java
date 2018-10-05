package com.pasistence.knockwork.Employeer.ViewHolder;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pasistence.knockwork.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderMnageJobPosting
        extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtjobName,txtfixedPrice,txtpriceRange,txtpoastedDays,txtjobQuotes,txtjobDescription;
    public Button btnJobEdit,btnJobRemove;

    public ViewHolderMnageJobPosting(@NonNull View itemView) {
        super(itemView);


        txtjobName           = (TextView)itemView.findViewById(R.id.job_name);
        txtfixedPrice        = (TextView)itemView.findViewById(R.id.txt_fixed_price);
        txtpriceRange        = (TextView)itemView.findViewById(R.id.txt_price_range);
        txtpoastedDays       = (TextView)itemView.findViewById(R.id.txt_job_posting);
        txtjobQuotes         = (TextView)itemView.findViewById(R.id.txt_quotes);
        txtjobDescription    = (TextView)itemView.findViewById(R.id.txt_job_description);

        btnJobEdit           = (Button)itemView.findViewById(R.id.btn_job_edit);
        btnJobRemove         = (Button)itemView.findViewById(R.id.btn_job_remove);

        btnJobEdit.setOnClickListener(this);
        btnJobRemove.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnJobEdit){
            Snackbar.make(v, "Edit Job", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        if(v == btnJobRemove){
            Snackbar.make(v, "Message Remove", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}

