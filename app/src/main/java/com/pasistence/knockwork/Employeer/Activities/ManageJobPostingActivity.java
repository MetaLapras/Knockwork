package com.pasistence.knockwork.Employeer.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.mancj.materialsearchbar.MaterialSearchBar;

import com.pasistence.knockwork.Adapter.ManageJobPostingAdapter;
import com.pasistence.knockwork.Model.ManageJobPostingModel;
import com.pasistence.knockwork.R;

import java.util.ArrayList;
import java.util.List;

public class ManageJobPostingActivity extends AppCompatActivity {

    Context mContext;
    MaterialSearchBar searchBar;
    RecyclerView recyclerLancer;
    RecyclerView.LayoutManager layoutManager;
    private static final String TAG = "search";

    ArrayList<ManageJobPostingModel> manageJobPostingModels = new ArrayList<ManageJobPostingModel>();
    ManageJobPostingAdapter manageJobPostingAdapter;
    ManageJobPostingAdapter searchAdapter;
    List<String> suggestList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_job_posting);
        mInit();


        ManageJobPostingModel jobModel1 = new ManageJobPostingModel("1","Web Development","Fixed Price","$5k - $7k","Posted 2 days ago","85 Quots","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        ManageJobPostingModel jobMoel2 = new ManageJobPostingModel("2","Professional Designer needed for Tshirt and other Products, WebProjects.","Fixed Price","$5k - $7k","Posted 2 days ago","85 Quots","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        ManageJobPostingModel jobModel3 = new ManageJobPostingModel("3","App Developer for creating a custome water sports application","Fixed Price","$5k - $7k","Posted 2 days ago","85 Quots","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");

        manageJobPostingModels.add(jobModel1);
        manageJobPostingModels.add(jobMoel2);
        manageJobPostingModels.add(jobModel3);
        manageJobPostingModels.add(jobModel1);
        manageJobPostingModels.add(jobMoel2);
        manageJobPostingModels.add(jobModel3);
        manageJobPostingModels.add(jobModel1);
        manageJobPostingModels.add(jobMoel2);
        manageJobPostingModels.add(jobModel3);
        manageJobPostingModels.add(jobModel1);
        manageJobPostingModels.add(jobMoel2);
        manageJobPostingModels.add(jobModel3);
        manageJobPostingModels.add(jobModel1);
        manageJobPostingModels.add(jobMoel2);
        manageJobPostingModels.add(jobModel3);



 /*       //loadSuggestList();

        //Setup search bar
        searchBar.setHint("Search");
        searchBar.setLastSuggestions(suggestList);
        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                List<String>suggest = new ArrayList<String>();
                for(String search : suggestList)
                {
                    if(search.toLowerCase().contains(searchBar.getText().toLowerCase()))
                        suggest.add(search);
                }

                searchBar.setLastSuggestions(suggest);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                {
                    recyclerLancer.setAdapter(manageJobPostingAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });*/


        manageJobPostingAdapter = new ManageJobPostingAdapter(ManageJobPostingActivity.this, manageJobPostingModels);
        recyclerLancer.setAdapter(manageJobPostingAdapter);
        manageJobPostingAdapter.notifyDataSetChanged();


    }

    private void mInit() {
        mContext = ManageJobPostingActivity.this;

        //searchBar      = (MaterialSearchBar)findViewById(R.id.search_bar_lancer);
        recyclerLancer = (RecyclerView)findViewById(R.id.jobposting_recycler_view);
        recyclerLancer.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerLancer.setLayoutManager(layoutManager);
    }

    /*private void startSearch(CharSequence text) {
        ArrayList<ManageJobPostingModel> searchLancer = new ArrayList<ManageJobPostingModel>();
        for(ManageJobPostingModel list : manageJobPostingModels){
            if(list.getJobname().equals(text.toString())){

                searchLancer.add(list);
                Log.e(TAG, searchLancer.toString());

                searchAdapter = new ManageJobPostingAdapter(ManageJobPostingActivity.this,searchLancer);
                recyclerLancer.setAdapter(searchAdapter);
            }
        }

    }*/

  /*  private void loadSuggestList() {
        //get Data into the list
        *//*for(LancerListModel list : lancerList){
            suggestList.add(list.getCategory());
        }*//*
        suggestList.add("Software Developer");
        suggestList.add("Web Developer");
        suggestList.add("Android Developer");
        searchBar.setLastSuggestions(suggestList);
    }
*/
}

