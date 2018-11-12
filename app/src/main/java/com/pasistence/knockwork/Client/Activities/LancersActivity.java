package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.LancerListAdapter;
import com.pasistence.knockwork.Adapter.SmallSuggestionAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Freelancer.Activities.JobPoastingActivity;
import com.pasistence.knockwork.Interface.ILoadMore;
import com.pasistence.knockwork.Model.ApiResponse.ApiPostJobResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseRegisterLancer;
import com.pasistence.knockwork.Model.ApiResponse.ApiSkillsResponse;
import com.pasistence.knockwork.Model.LancerListModel;
import com.pasistence.knockwork.Model.ResponseSuggestionList;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LancersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
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
    MyApi mService;
    List<ResponseSuggestionList> suggestionLists;
    List<ApiSkillsResponse> smallSuggestions;
    RelativeLayout rootLayout;
    RecyclerView lstSmallSugest;
    SmallSuggestionAdapter smallSuggestionAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*----------------------------------------------------------------------------*/
        mInit();

        loadSuggestList();
        getAllLancers(PageNo);
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
                        performPaginationWithSerach();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lancers, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
   //         Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Home", Snackbar.LENGTH_LONG)
     //               .setAction("Action", null).show();
            startActivity(new Intent(mContext,DashboardActivity.class));

        } else if (id == R.id.nav_inbox) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Inbox", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            startActivity(new Intent(mContext,InboxActivity.class));


        } else if (id == R.id.nav_notification) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Notification", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        } else if (id == R.id.nav_manage) {
//                       Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Manage", Snackbar.LENGTH_LONG)
//                             .setAction("Action", null).show();
//            //startActivity(new Intent(mContext,ManageJobPostActivity.class));

        } else if (id == R.id.nav_posting) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Posting", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            startActivity(new Intent(mContext,JobPoastingActivity.class));

        } else if (id == R.id.nav_contest) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Contest", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        }else if (id == R.id.nav_settings) {

            startActivity(new Intent(mContext,SettingActivity.class));
            //             Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Settings", Snackbar.LENGTH_LONG)
            //                .setAction("Action", null).show();

        }else if (id == R.id.nav_support) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Support", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

            if(getIntent()!=null){

                CatId = getIntent().getStringExtra("catId");
                subCatId = getIntent().getStringExtra("subcatId") ;
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

                                Log.e(TAG+"SizeM",resultList.size()+"");
                                for (ApiResponseRegisterLancer.Lancer res : result.getLancer()){
                                    resultList.add(res);
                                }

                                lancerListAdapter.notifyDataSetChanged();

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
                                    Log.e(TAG, result.toString());

                                    Log.e(TAG+"SizeM",resultList.size()+"");
                                    for (ApiResponseRegisterLancer.Lancer res : result.getLancer()){
                                        resultList.add(res);
                                    }

                                    searchAdapter.notifyDataSetChanged();

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
                    searchAdapter.notifyDataSetChanged();
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
