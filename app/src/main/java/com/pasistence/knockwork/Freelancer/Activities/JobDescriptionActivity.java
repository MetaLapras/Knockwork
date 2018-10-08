package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.JobDescriptionAdapter;
import com.pasistence.knockwork.Adapter.ManageJobPostingAdapter;
import com.pasistence.knockwork.Employeer.Activities.ManageJobPostingActivity;
import com.pasistence.knockwork.Model.JobDescriptionModel;
import com.pasistence.knockwork.Model.ManageJobPostingModel;
import com.pasistence.knockwork.R;

import java.util.ArrayList;
import java.util.List;

public class JobDescriptionActivity extends AppCompatActivity {
   /* Context mContext;
    RecyclerView recyclerjobdescription;
    RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "search";

    ArrayList<JobDescriptionModel> jobDescriptionModels = new ArrayList<JobDescriptionModel>();
    JobDescriptionAdapter jobDescriptionAdapter;
    *//* ManageJobPostingAdapter searchAdapter;*//*
    List<String> suggestList = new ArrayList<>();*/


    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);

        relativeLayout = (RelativeLayout)findViewById(R.id.job_description_freelancer);
        View child = getLayoutInflater().inflate(R.layout.description_freelancer_template, null);
        relativeLayout.addView(child);


    /*    mInit();


        JobDescriptionModel jobModel1 = new JobDescriptionModel("Professional Designer","Poasted 2 days ago ","85 Quotes","https://content-static.upwork.com/uploads/2014/10/01073427/profilephoto1.jpg","Duration","1-3  Months","https://cdn.cultofmac.com/wp-content/uploads/2011/10/97571564a70014ca5658b67f64f2ce23_1253524914.jpeg","Budget","5k-7k","https://i.cricketcb.com/stats/img/faceImages/8364.jpg","Job Type","Project Based","Lorem ipsum dolor sit amet, consectetur adipiscing elit, send do eiusmod tempor incididunt ut labor et dolore magna aliqua.... ","-Morbi enim nunc faucibus a. Duis convallis convallis tellus id. Arcu..","-non sodales neque sodales ut etiam sit. sed viverra ipsum nunc","-aliquet bibendum enim facilisis. Quis enim lobortis scelerisque..","-fermentum. vel pretium lectus wuam id. Arcu cursus vitae congue..","Requird Skills","Photoshop","illustrator","Editor","Designer");
       *//* JobDescriptionModel jobMoel2 = new JobDescriptionModel("2","Professional Designer needed for Tshirt and other Products, WebProjects.","Fixed Price","$5k - $7k","Posted 2 days ago","85 Quots","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        JobDescriptionModel jobModel3 = new JobDescriptionModel("3","App Developer for creating a custome water sports application","Fixed Price","$5k - $7k","Posted 2 days ago","85 Quots","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
*//*
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);
        jobDescriptionModels.add(jobModel1);



        jobDescriptionAdapter = new JobDescriptionAdapter(JobDescriptionActivity.this, jobDescriptionModels);
        recyclerjobdescription.setAdapter(jobDescriptionAdapter);
        jobDescriptionAdapter.notifyDataSetChanged();


    }

    private void mInit() {
        mContext = JobDescriptionActivity.this;

        //searchBar      = (MaterialSearchBar)findViewById(R.id.search_bar_lancer);
        recyclerjobdescription = (RecyclerView)findViewById(R.id.jobdescription_recycler);
        recyclerjobdescription.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerjobdescription.setLayoutManager(layoutManager);
    }
*/

    }
}
