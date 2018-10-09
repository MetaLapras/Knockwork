package com.pasistence.knockwork.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pasistence.knockwork.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolderJobDescription extends RecyclerView.ViewHolder  {

    public CircleImageView CircularDurationImage,CircularBudgetImage,CircularJobTypeImage;
    public TextView txtname,txtpoasted,txtquotes,txtduration,txtDurationmonths,txtbudget,txtbudgetrange,txtbudgetjobtype,
            txtpoastedjob,txtdescription,txtdescription1,txtdescription2,txtdescription3,txtdescription4,txtrequirdskills,txtskill1,txtskill2,txtskill3,txtskill4;


    public ViewHolderJobDescription(@NonNull View itemView) {
        super(itemView);

        CircularDurationImage = (CircleImageView)itemView.findViewById(R.id.img_duration);
        CircularBudgetImage = (CircleImageView)itemView.findViewById(R.id.img_budget);
        CircularJobTypeImage = (CircleImageView)itemView.findViewById(R.id.img_jobtype);
        txtname            = (TextView)itemView.findViewById(R.id.txt_job_name);
        txtpoasted           = (TextView)itemView.findViewById(R.id.txt_job_posted);
        txtquotes          = (TextView)itemView.findViewById(R.id.txt_quotes);
        txtduration          = (TextView)itemView.findViewById(R.id.txt_dutation);
        txtDurationmonths          = (TextView)itemView.findViewById(R.id.txt_duration_range);
        txtbudget          = (TextView)itemView.findViewById(R.id.txt_budget);
        txtbudgetrange          = (TextView)itemView.findViewById(R.id.txt_budget_range);
        txtbudgetjobtype          = (TextView)itemView.findViewById(R.id.txt_job_type);
        txtpoastedjob          = (TextView)itemView.findViewById(R.id.txt_job_project_based);
        txtdescription          = (TextView)itemView.findViewById(R.id.txt_description);
        txtdescription1          = (TextView)itemView.findViewById(R.id.txt_description1);
        txtdescription2          = (TextView)itemView.findViewById(R.id.txt_description2);
        txtdescription3          = (TextView)itemView.findViewById(R.id.txt_description3);
        txtdescription4          = (TextView)itemView.findViewById(R.id.txt_description4);
        txtrequirdskills          = (TextView)itemView.findViewById(R.id.txt_required_skill);
        txtskill1          = (TextView)itemView.findViewById(R.id.txt_skill1);
        txtskill2          = (TextView)itemView.findViewById(R.id.txt_skill2);
        txtskill3          = (TextView)itemView.findViewById(R.id.txt_skill3);
        txtskill4          = (TextView)itemView.findViewById(R.id.txt_skill4);

    }
}

