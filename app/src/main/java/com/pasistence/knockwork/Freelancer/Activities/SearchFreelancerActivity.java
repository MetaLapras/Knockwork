package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.SearchPageFreelancerAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ResponseSuggestionList;
import com.pasistence.knockwork.Model.SearchPageFreelancerModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFreelancerActivity extends FreeLancerBaseActivity
{
    private static final String TAG ="aaa" ;
    Context mContext;
    RecyclerView searchFreelancerrecyclerview;
    RecyclerView.LayoutManager layoutManager;
    MyApi mService;
    List<ResponseSuggestionList> suggestionLists;
    List<String> suggestList = new ArrayList<String>();
    MaterialSearchBar searchBar;

    private final static String API_KEY = "";


    SearchPageFreelancerAdapter searchPageFreelancerAdapter;
    public ArrayList<SearchPageFreelancerModel> searchPageListModels = new ArrayList<SearchPageFreelancerModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_search_freelancer, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        mInit();

        /*************************demo******************/
        SearchPageFreelancerModel searchPage1 = new SearchPageFreelancerModel("Web designing", "Fixed Price","5k-7k","Poasted 2 days ago","85 Quots","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s ", "https://content-static.upwork.com/uploads/2014/10/01073427/profilephoto1.jpg","Jimey Lindsy","United State","25k spent","80%");
        SearchPageFreelancerModel searchPage2 = new SearchPageFreelancerModel("create logo for brand t-shirt Company", "Fixed Price","5k-7k","Poasted 2 days ago","85 Quots", "Technology is making online work similar to local work, with added speed, cost, and quality advantages.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDdFKBrWfM9lqmjc_Cvg4n4BebNmgNt7OWQ59W0SjTU0TcfoubA","Carmne uhanre","Austrelia","50k spent","95%");

        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage2);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage2);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage2);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage2);
        searchPageListModels.add(searchPage1);
        searchPageListModels.add(searchPage2);
        searchPageListModels.add(searchPage1);


        searchPageFreelancerAdapter = new SearchPageFreelancerAdapter(mContext, searchPageListModels);
        searchFreelancerrecyclerview.setAdapter(searchPageFreelancerAdapter);
        searchPageFreelancerAdapter.notifyDataSetChanged();

        /*************************demo******************/


        loadSuggestList();

    }

    private void mInit() {

        mContext = SearchFreelancerActivity.this;

        searchBar     = (MaterialSearchBar) findViewById(R.id.search_bar_lancer);
        searchFreelancerrecyclerview  = (RecyclerView) findViewById(R.id.search_recycler_view);
        searchFreelancerrecyclerview.setHasFixedSize(false);
        layoutManager      = new LinearLayoutManager(this);
        searchFreelancerrecyclerview.setLayoutManager(layoutManager);

        mService = Common.getApi();
    }

    private void startSearch(String text) {
        // adapter = new SearchAdapter(WorkerDisplayList.this, this, database.getWorkerName(text));
        // WorkerListRecyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        //get Data into the list
        try{
            mService.getSuggestionList()
                    .enqueue(new Callback<List<ResponseSuggestionList>>() {
                        @Override
                        public void onResponse(Call<List<ResponseSuggestionList>> call, Response<List<ResponseSuggestionList>> response) {
                            Log.e(TAG, response.body().toString());
                            suggestionLists = response.body();

                            //final List<String> labels = new ArrayList<String>();
                            for(int i = 0; i<suggestionLists.size(); i++)
                            {
                                suggestList.add(suggestionLists.get(i).getTitle());
                            }
                            searchBar.setLastSuggestions(suggestList);
                        }

                        @Override
                        public void onFailure(Call<List<ResponseSuggestionList>> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

        }catch (Exception e){
            e.printStackTrace();
        }
        //searchBar.setLastSuggestions(suggestList);
    }

}
