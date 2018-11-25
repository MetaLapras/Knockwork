package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.LancerListAdapter;
import com.pasistence.knockwork.Adapter.ManageJobPostingAdapter;
import com.pasistence.knockwork.Adapter.SearchPageFreelancerAdapter;
import com.pasistence.knockwork.Client.Activities.ManageJobPostActivity;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Model.ManageJobPostingModel;
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
    LinearLayoutManager layoutManager;
    MyApi mService;
    List<ResponseSuggestionList> suggestionLists;
    List<String> suggestList = new ArrayList<String>();
    MaterialSearchBar searchBar;
    public int PageNo = 1;
    int TotalElementsCount=0;
    int currentItems;
    int totalItems;
    int scrollOutItems;
    ProgressBar progressBar;
    private boolean isScrolling = false;
    private boolean isSearching = false;
    public CharSequence searchText;


    //ArrayList<ApiPostJobResponse.Result> manageJobPostingModels = new ArrayList<ApiPostJobResponse.Result>();
    ArrayList<ApiPostJobResponse.Result> tryout = new ArrayList<ApiPostJobResponse.Result>();
    ArrayList<ApiPostJobResponse.Result> resultList = new ArrayList<ApiPostJobResponse.Result>();


    private final static String API_KEY = "";


    SearchPageFreelancerAdapter searchPageFreelancerAdapter;
    SearchPageFreelancerAdapter searchAdapter;
    public ArrayList<SearchPageFreelancerModel> searchPageListModels = new ArrayList<SearchPageFreelancerModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_search_freelancer, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        mInit();
        readAllJobs(PageNo);

        /*************************demo******************/
        //Setup search bar
        searchBar.setHint("Search");
        //searchBar.setLastSuggestions(suggestList);
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
                    searchFreelancerrecyclerview.setAdapter(searchPageFreelancerAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                searchText = text;
                startSearch(text,PageNo);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
        /*************************demo******************/


        loadSuggestList();

     /*   searchFreelancerrecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstCompletelyVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    if(isSearching){
                        performPaginationWithSerach();
                    }else {
                        performPagination();
                    }

                }
                //  isScrolling = true;
            }
        });*/

    }

    private void readAllJobs(int PageNo) {
        try {
            mService.withClientJobRead(PageNo).enqueue(new Callback<ApiPostJobResponse>() {
                @Override
                public void onResponse(Call<ApiPostJobResponse> call, Response<ApiPostJobResponse> response) {
                    ApiPostJobResponse result = response.body();
                    Log.e(TAG, result.toString());

                    resultList = result.getResult();
                    Log.e(TAG+"SizeM",resultList.size()+"");

                    TotalElementsCount = Integer.parseInt(result.getTotalcount());

                    for (ApiPostJobResponse.Result res : result.getResult()){
                        tryout.add(res);
                    }

                    Log.e(TAG,tryout.toString());
                    Log.e(TAG+"Size",tryout.size()+"");

                    searchPageFreelancerAdapter = new SearchPageFreelancerAdapter(mContext, resultList);
                    searchFreelancerrecyclerview.setAdapter(searchPageFreelancerAdapter);
                    searchFreelancerrecyclerview.setLayoutManager(layoutManager);
                    searchPageFreelancerAdapter.notifyDataSetChanged();

                    // manageJobPostingAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<ApiPostJobResponse> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                    t.printStackTrace();

                }
            });

        }catch (Exception e)
        {
            Log.e(TAG, e.getMessage() );
            e.printStackTrace();
            Common.commonDialog(mContext,"Sever not found..");
        }
    }

    private void mInit() {

        mContext = SearchFreelancerActivity.this;

        searchBar     = (MaterialSearchBar) findViewById(R.id.search_bar_lancer);
        searchFreelancerrecyclerview  = (RecyclerView) findViewById(R.id.search_recycler_view);
        searchFreelancerrecyclerview.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        searchFreelancerrecyclerview.setLayoutManager(layoutManager);

        progressBar = (ProgressBar)findViewById(R.id.progress);

        mService = Common.getApi();
    }

    private void startSearch(CharSequence text,int pageno) {

        isSearching = true;
        mService.JobsSearch(pageno,text).enqueue(new Callback<ApiPostJobResponse>() {
            @Override
            public void onResponse(Call<ApiPostJobResponse> call, Response<ApiPostJobResponse> response) {

                response.message();
                ApiPostJobResponse result = response.body();

                Log.e(TAG, result.toString());
                if(!result.getError()){

                    resultList = result.getResult();

                    Log.e(TAG+"++++", result.getResult().toString());

                    searchAdapter = new SearchPageFreelancerAdapter(mContext,resultList);
                    searchFreelancerrecyclerview.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();

                    TotalElementsCount = Integer.parseInt(response.body().getTotalcount());
                }else if(result.getError()){
                    Common.commonDialog(mContext,result.getMessage());
                }
            }
            @Override
            public void onFailure(Call<ApiPostJobResponse> call, Throwable t) {
                t.printStackTrace();
            }

        });

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

    private void performPagination(){
        //perform call statment

        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // progressBar.setVisibility(View.VISIBLE);
                for (int i=0; i<1; i++)
                {
                    try {
                        if(TotalElementsCount == resultList.size()){
                            //Toast.makeText(mContext, "Nothing to Display", Toast.LENGTH_SHORT).show();
                            Snackbar.make(findViewById(R.id.search_freelancer), "Nothing to Display", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }else{
                            PageNo++;
                            mService.withClientJobRead(PageNo).enqueue(new Callback<ApiPostJobResponse>() {
                                @Override
                                public void onResponse(Call<ApiPostJobResponse> call, Response<ApiPostJobResponse> response) {
                                    ApiPostJobResponse result = response.body();
                                    Log.e(TAG, result.toString());

                                    if(!result.getError()){

                                        Log.e(TAG+"SizeM",resultList.size()+"");
                                        for (ApiPostJobResponse.Result res : result.getResult()){
                                            resultList.add(res);
                                        }

                                        searchPageFreelancerAdapter.notifyDataSetChanged();

                                    }else if(result.getError()){
                                        Common.commonDialog(mContext,result.getMessage());
                                    }else {
                                        Common.commonDialog(mContext,"Server Not Found!");
                                    }


                                }

                                @Override
                                public void onFailure(Call<ApiPostJobResponse> call, Throwable t) {
                                    Log.e(TAG, t.getMessage());
                                    t.printStackTrace();

                                }
                            });
                        }
                    }catch (Exception e)
                    {
                        Log.e(TAG, e.getMessage() );
                        e.printStackTrace();
                        Common.commonDialog(mContext,"Sever not found..");
                    }
                    searchPageFreelancerAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }

            }
        }, 3000);
    }

    private void performPaginationWithSerach(){
        //perform call statment

        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // progressBar.setVisibility(View.VISIBLE);
                for (int i=0; i<1; i++)
                {
                    try {
                        if(TotalElementsCount == resultList.size()){
                            //Toast.makeText(mContext, "Nothing to Display", Toast.LENGTH_SHORT).show();
                            Snackbar.make(findViewById(R.id.search_freelancer), "Nothing to Display", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }else{
                            PageNo++;
                            mService.JobsSearch(PageNo,searchText).enqueue(new Callback<ApiPostJobResponse>() {
                                @Override
                                public void onResponse(Call<ApiPostJobResponse> call, Response<ApiPostJobResponse> response) {
                                    ApiPostJobResponse result = response.body();
                                    Log.e(TAG, result.toString());

                                    if(!result.getError()){

                                        Log.e(TAG+"SizeM",resultList.size()+"");
                                        for (ApiPostJobResponse.Result res : result.getResult()){
                                            resultList.add(res);
                                        }

                                        searchAdapter.notifyDataSetChanged();

                                    }else if(result.getError()) {

                                        Common.commonDialog(mContext,result.getMessage());

                                    }else {

                                        Common.commonDialog(mContext,"Server Not Found");
                                    }

                                }

                                @Override
                                public void onFailure(Call<ApiPostJobResponse> call, Throwable t) {
                                    Log.e(TAG, t.getMessage());
                                    t.printStackTrace();

                                }
                            });
                        }
                    }catch (Exception e)
                    {
                        Log.e(TAG, e.getMessage() );
                        e.printStackTrace();
                        Common.commonDialog(mContext,"Sever not found..");
                    }
                    //searchAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }

            }
        }, 3000);
    }

}
