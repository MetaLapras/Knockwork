package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.LancerListAdapter;
import com.pasistence.knockwork.Adapter.SmallSuggestionAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiSkillsResponse;
import com.pasistence.knockwork.Model.LancerListModel;
import com.pasistence.knockwork.Model.ResponseSuggestionList;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LancersActivity extends ClientBaseActivity {
    private static final String TAG = "search";
    Context mContext;
    MaterialSearchBar searchBar;
    RecyclerView recyclerLancer;
    LinearLayoutManager layoutManager;
    //Variable for pagination
    private boolean isScrolling = false;
    private boolean isSearching = false;
    int PageNo =  1;
    int TotalElementsCount=0;
    int currentItems;
    int totalItems;
    int scrollOutItems;
    ProgressBar progressBar;
    public CharSequence searchText;

    ArrayList<LancerListModel> lancerList = new ArrayList<LancerListModel>();
    ArrayList<ApiResponseRegisterLancer.Lancer> resultList = new ArrayList<ApiResponseRegisterLancer.Lancer>();
    LancerListAdapter lancerListAdapter;
    LancerListAdapter searchAdapter;
    List<String> suggestList = new ArrayList<String>();
    String CatId,subCatId;
    CharSequence subCatTitle;
    MyApi mService;
    List<ResponseSuggestionList> suggestionLists;
    List<ApiSkillsResponse> smallSuggestions;
    RelativeLayout rootLayout;
    RecyclerView lstSmallSugest;
    SmallSuggestionAdapter smallSuggestionAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_lancers, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        //setContentView(R.layout.activity_lancers);


        /*----------------------------------------------------------------------------*/
        mInit();

        if(!getIntent().getStringExtra("title").equals(null)||!getIntent().getStringExtra("title").equals("")){
            CharSequence charSequence = getIntent().getStringExtra("title");
            startSearch(charSequence,1);
        }else {
            getAllLancers(PageNo);
        }


        loadSuggestList();

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
                    recyclerLancer.setAdapter(lancerListAdapter);
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

        /*----------------------------------------------------------------------------*/

        recyclerLancer.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        //performPaginationWithSerach();

                    }else {
                        performPagination();
                    }

                }
                //  isScrolling = true;
            }
        });

        //getSmallSuggestion("Mobile");
    }

    private void getAllLancers(int PageNo) {

        if(subCatTitle!=null){
            startSearch(subCatTitle,PageNo);
        }else {
        mService.getLancers(PageNo).enqueue(new Callback<ApiResponseRegisterLancer>() {
            @Override
            public void onResponse(Call<ApiResponseRegisterLancer> call, Response<ApiResponseRegisterLancer> response) {

                response.message();
                ApiResponseRegisterLancer result = response.body();
                Log.e(TAG+"+", result.toString());

                resultList = result.getLancer();
                Log.e(TAG+"++", resultList.toString());
                TotalElementsCount = Integer.parseInt(response.body().getTotalCount());


                lancerListAdapter = new LancerListAdapter(mContext, resultList);
                recyclerLancer.setAdapter(lancerListAdapter);
                lancerListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponseRegisterLancer> call, Throwable t) {
                t.printStackTrace();
            }
        });
         /* */
        }
    }

    private void mInit() {
        mContext = LancersActivity.this;
        progressBar = (ProgressBar)findViewById(R.id.progress);

        rootLayout = (RelativeLayout)findViewById(R.id.lancer_activity);
        searchBar = (MaterialSearchBar)findViewById(R.id.search_bar_lancer);
        recyclerLancer = (RecyclerView)findViewById(R.id.recycler_lancer_list);
        recyclerLancer.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerLancer.setLayoutManager(layoutManager);

        lstSmallSugest = (RecyclerView)findViewById(R.id.list_subcategory);
        lstSmallSugest.setHasFixedSize(false);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        lstSmallSugest.setLayoutManager(layoutManager);

        try{

            if(getIntent()!= null){

                CatId = getIntent().getStringExtra("catId");
                subCatId = getIntent().getStringExtra("subcatId") ;
                subCatTitle = getIntent().getStringExtra("subcatTitle") ;

                Log.e(TAG, CatId+"-"+subCatId+"-"+subCatTitle);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //Init Api
        mService = Common.getApi();

    }

    private void startSearch(CharSequence text,int PageNo) {
        isSearching = true;
        mService.LancerSearch(PageNo,text).enqueue(new Callback<ApiResponseRegisterLancer>() {
            @Override
            public void onResponse(Call<ApiResponseRegisterLancer> call, Response<ApiResponseRegisterLancer> response) {

                response.message();
                ApiResponseRegisterLancer result = response.body();

                Log.e(TAG, result.toString());
                if(!result.getError()){

                    resultList = result.getLancer();

                    Log.e(TAG+"++++", result.getLancer().toString());

                    searchAdapter = new LancerListAdapter(mContext,resultList);
                    recyclerLancer.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();

                    TotalElementsCount = Integer.parseInt(response.body().getTotalCount());
                }else if(result.getError()){
                    Common.commonDialog(mContext,result.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ApiResponseRegisterLancer> call, Throwable t) {
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
                        Snackbar.make(findViewById(R.id.lancer_activity), "Nothing to Display", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }else{
                        PageNo++;
                        mService.getLancers(PageNo).enqueue(new Callback<ApiResponseRegisterLancer>() {
                            @Override
                            public void onResponse(Call<ApiResponseRegisterLancer> call, Response<ApiResponseRegisterLancer> response) {
                                ApiResponseRegisterLancer result = response.body();
                                Log.e(TAG, result.toString());

                                if(!result.getError()){

                                    Log.e(TAG+"SizeM",resultList.size()+"");
                                    for (ApiResponseRegisterLancer.Lancer res : result.getLancer()){
                                        resultList.add(res);
                                    }

                                    lancerListAdapter.notifyDataSetChanged();

                                }else if(result.getError()){
                                    Common.commonDialog(mContext,result.getMessage());
                                }else {
                                    Common.commonDialog(mContext,"Server Not Found!");
                                }


                            }

                            @Override
                            public void onFailure(Call<ApiResponseRegisterLancer> call, Throwable t) {
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
                lancerListAdapter.notifyDataSetChanged();
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
                            Snackbar.make(findViewById(R.id.lancer_activity), "Nothing to Display", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }else{
                            PageNo++;
                            mService.LancerSearch(PageNo,searchText).enqueue(new Callback<ApiResponseRegisterLancer>() {
                                @Override
                                public void onResponse(Call<ApiResponseRegisterLancer> call, Response<ApiResponseRegisterLancer> response) {
                                    ApiResponseRegisterLancer result = response.body();
                                    Log.e(TAG+"==>", result.toString());

                                    if(!result.getError()){

                                        Log.e(TAG+"SizeM===>",resultList.size()+"");
                                        for (ApiResponseRegisterLancer.Lancer res : result.getLancer()){
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
                                public void onFailure(Call<ApiResponseRegisterLancer> call, Throwable t) {
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

    private void getSmallSuggestion(CharSequence str) {

        try {

            mService.getSmallSuggestion("mobile").enqueue(new Callback<List<ApiSkillsResponse>>() {
                @Override
                public void onResponse(Call<List<ApiSkillsResponse>> call, Response<List<ApiSkillsResponse>> response) {

                    Log.e(TAG, response.body().toString());
                    smallSuggestions = response.body();

                    smallSuggestionAdapter = new SmallSuggestionAdapter(LancersActivity.this,smallSuggestions);
                    lstSmallSugest.setAdapter(smallSuggestionAdapter);
                    smallSuggestionAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<List<ApiSkillsResponse>> call, Throwable t) {

                }
            });


        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            Common.commonDialog(mContext, "Sever not found..");
        }
    }
}
