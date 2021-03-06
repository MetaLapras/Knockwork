package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.daimajia.slider.library.SliderLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pasistence.knockwork.Adapter.ClientPopularServiceAdapter;
import com.pasistence.knockwork.Adapter.ClientTopServiceAdapter;
import com.pasistence.knockwork.Common.Common;

import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Model.ApiResponse.ApiProfileStatus;
import com.pasistence.knockwork.Model.PopularServicesModel;
import com.pasistence.knockwork.Model.ResponseTopService;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.pasistence.knockwork.SelectionActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

public class FreeLancerDashboardActivity extends FreeLancerBaseActivity{

    private static final String TAG = "cdash-->";
    Boolean isLancer=false;
    Boolean isClient=false;
    public FirebaseAuth mAuth;

    Context mContext;

    RecyclerView recyclerPopularServices,recyclerTopServices;
    RecyclerView.LayoutManager GridlayoutManager,LinearlayoutManager ;
    SwipeRefreshLayout refreshLayout;
    CardView SearchBar;
    LinearLayout navlinearlayout;
    ImageView imgEdit;

    MyApi mService;
    ClientPopularServiceAdapter popularServiceAdapter;
    ClientTopServiceAdapter topServiceAdapter;
    List<PopularServicesModel> popularServicesModelList = new ArrayList<PopularServicesModel>();
    List<ResponseTopService> topServicesModelList = new ArrayList<ResponseTopService>();

    TextView txtUserName,txtUserEmail;
    CircleImageView imgUserProfile;

    RoundCornerProgressBar progressBar;

    //Sliders
    HashMap<String,String> image_list;
    SliderLayout mslider;
    Float ProfileCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_free_lancer_dashboard, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);


//        FirebaseMessaging.getInstance().subscribeToTopic(getUid(mContext));

        //setContentView(R.layout.activity_free_lancer_dashboard);

        mInit();

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
                    checkProfile();
                    //loadTopServices();
                }else
                {
                    Toast.makeText(getBaseContext(), "Check Your Internet Connection ! ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        //Default Load
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                //to Load menu from Firebase
                if(Common.isConnectedToInterNet(getBaseContext())) {
                   // checkProfile();
                    loadPopularServices();
                    //loadTopServices();
                }else
                {
                    Toast.makeText(getBaseContext(), "Check Your Internet Connection ! ", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


        SearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,SearchFreelancerActivity.class));
                overridePendingTransition(R.anim.fade_in_left,R.anim.fade_in_right);
            }
        });

    }

    private void checkProfile() {

        try{
            mService.getProfileStatus(getUid(mContext)).enqueue(new Callback<ApiProfileStatus>() {
                @Override
                public void onResponse(Call<ApiProfileStatus> call, Response<ApiProfileStatus> response) {
                    ApiProfileStatus result = response.body();

                    Log.e(TAG,result.toString() );

                    ProfileCount = Float.valueOf(result.getTotal());

                    if(!result.getError()){
                        if(ProfileCount < 100){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                            alertDialogBuilder.setMessage("Please Complete Your Profile! First");
                            alertDialogBuilder.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            startActivity(new Intent(mContext,FreelancerProfileActivity.class));
                                        }
                                    });

                            alertDialogBuilder.setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface arg0, int arg1) {
                                            arg0.dismiss();
                                        }
                                    });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            //AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();
                        }

                        progressBar.setProgress(ProfileCount*10);
                        progressBar.setProgressColor(getResources().getColor(R.color.green));

                    }else if(result.getError()){
                       /* AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                        alertDialogBuilder.setMessage("Please Complete Your Registration! First");
                        alertDialogBuilder.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        startActivity(new Intent(mContext,SelectionActivity.class));
                                    }
                                });

                        alertDialogBuilder.setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        arg0.dismiss();
                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        //AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();*/
                    }else {
                        Common.commonDialog(mContext,"Server Not Found!");
                    }

                  }

                @Override
                public void onFailure(Call<ApiProfileStatus> call, Throwable t) {
                    t.printStackTrace();
                    t.getMessage();
                }


            });
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
    }

    private void mInit() {
        mContext = FreeLancerDashboardActivity.this;

        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);

         recyclerPopularServices =(RecyclerView)findViewById(R.id.recycler_popular_services);
         recyclerPopularServices.setHasFixedSize(true);
         recyclerPopularServices.setLayoutManager(new LinearLayoutManager(this, android.support.v7.widget.LinearLayoutManager.HORIZONTAL, false));
         recyclerPopularServices.setNestedScrollingEnabled(false);

        recyclerTopServices     =(RecyclerView)findViewById(R.id.recycler_top_services);
        recyclerTopServices.setHasFixedSize(false);
        GridlayoutManager       = new GridLayoutManager(this,2);
        recyclerTopServices.setLayoutManager(GridlayoutManager);
        recyclerTopServices.setNestedScrollingEnabled(false);

        //mslider = (SliderLayout)findViewById(R.id.slider);

        SearchBar = (CardView) findViewById(R.id.search_bar);

        //Init Service
        mService = Common.getApi();
        //Init Preference Values for Navigation Drawer
        Common.getUserPreference(mContext);

        //initFirebase
        mAuth = FirebaseAuth.getInstance();

        //Progress Bar
        progressBar = (RoundCornerProgressBar)findViewById(R.id.progressbar_profile);

    }

    private void loadPopularServices() {

        mService.getTopServices().enqueue(new Callback<List<ResponseTopService>>() {
            @Override
            public void onResponse(Call<List<ResponseTopService>> call, Response<List<ResponseTopService>> response) {
                Log.e(TAG, response.body().toString());

                topServicesModelList = response.body();
                topServiceAdapter = new ClientTopServiceAdapter(FreeLancerDashboardActivity.this,topServicesModelList);
                recyclerTopServices.setAdapter(topServiceAdapter);
                topServiceAdapter.notifyDataSetChanged();

                refreshLayout.setRefreshing(false);
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
                popularServiceAdapter = new ClientPopularServiceAdapter(FreeLancerDashboardActivity.this,popularServicesModelList);
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

    private void loadTopServices() {
       /* TopServiceAdapter.startListening();
        recyclerTopServices.setAdapter(TopServiceAdapter);
        recyclerTopServices.scheduleLayoutAnimation();
        refreshLayout.setRefreshing(false);*/

    }


  /*  private void setupSlider() {
        image_list = new HashMap<>();
        final DatabaseReference Fbanner = database.getReference("popular");
        Fbanner.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot postsnapshot : dataSnapshot.getChildren())
                {
                    PopularServicesModel banner = postsnapshot.getValue(PopularServicesModel.class);
                    //we will concat String name and Id
                    //PIZZA_01 => and we will use pizza for show description, 01 for food id to click

                    image_list.put(banner.getName()+"@@@"+banner.getId(),banner.getImage());
                    Log.e("banner", image_list.toString() );
                }
                for(String key : image_list.keySet())
                {
                    String[] keySplit = key.split("@@@");
                    final String NameofFood = keySplit[0];
                    String IdofFood   = keySplit[1];


                    //Create Slider
                    final TextSliderView textSliderView = new TextSliderView(getBaseContext());
                    textSliderView.description(NameofFood)
                            .image(image_list.get(key))
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView slider) {
                                   *//* Intent intent = new Intent(DashboardActivityClient.this,FoodDetails.class);
                                    //send food id to food details
                                    intent.putExtras(textSliderView.getBundle());
                                    startActivity(intent);*//*

                                    Snackbar.make(findViewById(R.id.swipe_refresh_layout), ""+NameofFood, Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();


                                }
                            });
                    //Add Extras to Bundle
                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle().putString("FoodId",IdofFood);

                    mslider.addSlider(textSliderView);

                    //Remove event after finish
                    Fbanner.removeEventListener(this);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mslider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mslider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mslider.setCustomAnimation(new DescriptionAnimation());
        mslider.setDuration(3000);

    }*/

    @Override
    protected void onResume() {
        super.onResume();
        checkProfile();
    }
}
