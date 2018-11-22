package com.pasistence.knockwork.Client.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.pasistence.knockwork.Adapter.ClientPopularServiceAdapter;
import com.pasistence.knockwork.Adapter.ClientTopServiceAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Model.PopularServicesModel;
import com.pasistence.knockwork.Model.ResponseTopService;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.SelectionActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.supercharge.shimmerlayout.ShimmerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivityClient extends ClientBaseActivity {


    private static final String TAG = "cdash-->";
    Boolean isLancer=false;
    Boolean isClient=false;

    Context mContext;
    RecyclerView recyclerPopularServices,recyclerTopServices;
    RecyclerView.LayoutManager GridlayoutManager,LinearlayoutManager ;
    SwipeRefreshLayout refreshLayout;
    CardView SearchBar;
    ShimmerLayout shimmerlayout;
    RelativeLayout AfterLoad;

    //set up popular services adapter
    MyApi mService;
    ClientPopularServiceAdapter popularServiceAdapter;
    ClientTopServiceAdapter topServiceAdapter;
    List<PopularServicesModel> popularServicesModelList = new ArrayList<PopularServicesModel>();
    List<ResponseTopService> topServicesModelList = new ArrayList<ResponseTopService>();

    TextView txtUserName,txtUserEmail;
    CircleImageView imgUserProfile;

    //Sliders
    HashMap<String,String> image_list;
    SliderLayout mslider;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_dashboard, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        mInit();

        //loadTopServices();
        loadPopularServices();

        refreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //to Load menu from Firebase
                if(Common.isConnectedToInterNet(getBaseContext())) {
                    loadPopularServices();
                    //loadTopServices();
                }else
                {
                    Common.commonDialog(mContext,"Please Check Your Internet Connection !");
                    return;
                }
            }
        });
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(Common.isConnectedToInterNet(getBaseContext())) {
                    loadPopularServices();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setVisibility(View.VISIBLE);
                            shimmerlayout.stopShimmerAnimation();
                            AfterLoad.setVisibility(View.GONE);

                        }
                    },1200);
                    //loadTopServices();
                }else
                {
                    Common.commonDialog(mContext,"Please Check Your Internet Connection !");
                    return;
                }

            }
        });

        //Slider setup
       // setupSlider();
        SearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,LancersActivity.class));
                overridePendingTransition(R.anim.fade_in_left,R.anim.fade_in_right);
            }
        });
    }

    private void mInit() {

        mContext                = DashboardActivityClient.this;
        refreshLayout           = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
      //  mslider                 = (SliderLayout)findViewById(R.id.slider);

        SearchBar               = (CardView) findViewById(R.id.search_bar);

        recyclerTopServices     = (RecyclerView)findViewById(R.id.recycler_top_services);
        GridlayoutManager       =  new GridLayoutManager(this,2);

        recyclerTopServices.setHasFixedSize(false);
        recyclerTopServices.setLayoutManager(GridlayoutManager);
        recyclerTopServices.setNestedScrollingEnabled(false);


        recyclerPopularServices =(RecyclerView)findViewById(R.id.recycler_popular_services);
        recyclerPopularServices.setHasFixedSize(true);
        LinearlayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerPopularServices.setLayoutManager(LinearlayoutManager);
        recyclerPopularServices.setNestedScrollingEnabled(false);


        //Init Service
        mService = Common.getApi();
        //Init Preference Values for Navigation Drawer
        Common.getUserPreference(mContext);

        mAuth = FirebaseAuth.getInstance();

        shimmerlayout = (ShimmerLayout) findViewById(R.id.shimmer_layout);
        shimmerlayout.startShimmerAnimation();

        AfterLoad = (RelativeLayout)findViewById(R.id.relative_afterload);
        refreshLayout.setVisibility(View.GONE);

    }

    private void loadTopServices() {
      /*  TopServiceAdapter.startListening();
        recyclerTopServices.setAdapter(TopServiceAdapter);
        recyclerTopServices.scheduleLayoutAnimation();
        refreshLayout.setRefreshing(false);*/

       /* mService.getTopServices().enqueue(new Callback<List<ResponseTopService>>() {
            @Override
            public void onResponse(Call<List<ResponseTopService>> call, Response<List<ResponseTopService>> response) {
                Log.e(TAG, response.body().toString());

                topServiceAdapter = new ClientTopServiceAdapter(DashboardActivityClient.this,topServicesModelList);
                recyclerTopServices.setAdapter(topServiceAdapter);
                recyclerTopServices.scheduleLayoutAnimation();

                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<ResponseTopService>> call, Throwable t) {
                t.printStackTrace();
            }
        });*/

    }

    private void loadPopularServices() {
      /*  popularAdapter.notifyDataSetChanged();
        popularAdapter.startListening();
        recyclerPopularServices.setAdapter(popularAdapter);
        recyclerPopularServices.scheduleLayoutAnimation();
        refreshLayout.setRefreshing(false);*/

      mService.getTopServices().enqueue(new Callback<List<ResponseTopService>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<List<ResponseTopService>> call, Response<List<ResponseTopService>> response) {
                //Log.e(TAG, response.body().toString());

                topServicesModelList = response.body();
                topServiceAdapter = new ClientTopServiceAdapter(DashboardActivityClient.this,topServicesModelList);
                recyclerTopServices.setAdapter(topServiceAdapter);
                topServiceAdapter.notifyDataSetChanged();

                refreshLayout.setRefreshing(false);

                /*refreshLayout.setVisibility(View.VISIBLE);
                shimmerlayout.stopShimmerAnimation();
                AfterLoad.setVisibility(View.GONE);*/
            }

            @Override
            public void onFailure(Call<List<ResponseTopService>> call, Throwable t) {
                t.printStackTrace();
            }
        });

      mService.getPopularServices().enqueue(new Callback<List<PopularServicesModel>>() {
            @Override
            public void onResponse(Call<List<PopularServicesModel>> call, Response<List<PopularServicesModel>> response) {
                //Log.e(TAG, response.body().toString());

                popularServicesModelList = response.body();
                popularServiceAdapter = new ClientPopularServiceAdapter(DashboardActivityClient.this,popularServicesModelList);
                recyclerPopularServices.setAdapter(popularServiceAdapter);
                popularServiceAdapter.notifyDataSetChanged();

                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<PopularServicesModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
