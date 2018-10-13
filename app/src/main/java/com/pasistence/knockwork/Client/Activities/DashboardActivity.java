package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Freelancer.Activities.SearchFreelancerActivity;
import com.pasistence.knockwork.Interface.ItemClickListener;
import com.pasistence.knockwork.Model.PopularServicesModel;
import com.pasistence.knockwork.Model.TopServicesModel;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderTopServices;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Boolean isLancer=false;
    Boolean isClient=false;

    Context mContext;
    RecyclerView recyclerPopularServices,recyclerTopServices;
    RecyclerView.LayoutManager GridlayoutManager,LinearLayoutManager ;
    SwipeRefreshLayout refreshLayout;

    public FirebaseDatabase database;
    public DatabaseReference popular_dataReference ;
    public DatabaseReference Top_dataReference ;
    public MaterialSearchBar SearchBar;

    FirebaseRecyclerAdapter<TopServicesModel,ViewHolderTopServices> TopServiceAdapter;

    //Sliders
    HashMap<String,String>image_list;
    SliderLayout mslider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mInit();
        isLancer=getIntent().getBooleanExtra("isLancer",false);
        isClient=getIntent().getBooleanExtra("isClient",false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //init Fire base
        database = FirebaseDatabase.getInstance();
        popular_dataReference = database.getReference("popular");
        Top_dataReference = database.getReference("top");


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
                    loadTopServices();
                }else
                {
                    Toast.makeText(getBaseContext(), "Check Your Internet Connection ! ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if(Common.isConnectedToInterNet(getBaseContext())) {
                    loadPopularServices();
                    loadTopServices();
                }else
                {
                    Toast.makeText(getBaseContext(), "Check Your Internet Connection ! ", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });



        FirebaseRecyclerOptions<TopServicesModel> topOptions = new FirebaseRecyclerOptions.Builder<TopServicesModel>()
                .setQuery(Top_dataReference,TopServicesModel.class)
                .build();

        TopServiceAdapter = new FirebaseRecyclerAdapter<TopServicesModel, ViewHolderTopServices>(topOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderTopServices viewHolder, int position, @NonNull TopServicesModel model) {

                viewHolder.txtHead.setText(model.getHead());
                viewHolder.txtContent.setText(model.getContent());

                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imgTopServices);

                Picasso.with(getBaseContext()).load(model.getLogo())
                        .into(viewHolder.imgLogo);


                final TopServicesModel clickitem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Snackbar.make(view, ""+clickitem.getHead(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
            }



            @Override
            public ViewHolderTopServices onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custom_template_topservices,parent,false);
                return new ViewHolderTopServices(itemView);

            }
        };
        recyclerTopServices.setAdapter(TopServiceAdapter);
        recyclerTopServices.scheduleLayoutAnimation();
        TopServiceAdapter.notifyDataSetChanged();

        TopServiceAdapter.startListening();


          loadTopServices();

        refreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //to Load menu from Firebase
                if(Common.isConnectedToInterNet(getBaseContext())) {
                    //loadPopularServices();
                    loadTopServices();
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
                    //loadPopularServices();
                    loadTopServices();
                }else
                {
                    Toast.makeText(getBaseContext(), "Check Your Internet Connection ! ", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        //Slider setup
        setupSlider();


        SearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,LancerListActivity.class));
                overridePendingTransition(R.anim.fade_in_left,R.anim.fade_in_right);
            }
        });

    }

    private void loadTopServices() {
        TopServiceAdapter.startListening();
        recyclerTopServices.setAdapter(TopServiceAdapter);
        recyclerTopServices.scheduleLayoutAnimation();
        refreshLayout.setRefreshing(false);
    }

    private void loadPopularServices() {
      /*  popularAdapter.notifyDataSetChanged();
        popularAdapter.startListening();
        recyclerPopularServices.setAdapter(popularAdapter);
        recyclerPopularServices.scheduleLayoutAnimation();
        refreshLayout.setRefreshing(false);*/
    }

    private void mInit() {

        mContext                = DashboardActivity.this;
        refreshLayout           = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        mslider                 = (SliderLayout)findViewById(R.id.slider);
        SearchBar               = (MaterialSearchBar)findViewById(R.id.search_bar);
        recyclerTopServices     = (RecyclerView)findViewById(R.id.recycler_top_services);
        GridlayoutManager       =  new GridLayoutManager(this,2);
        recyclerTopServices.setHasFixedSize(false);
        recyclerTopServices.setLayoutManager(GridlayoutManager);
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
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Home", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } else if (id == R.id.nav_inbox) {
            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Inbox", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            startActivity(new Intent(mContext,InboxActivity.class));


        } else if (id == R.id.nav_notification) {
            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Notification", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } else if (id == R.id.nav_manage) {
            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Manage", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            startActivity(new Intent(mContext,ManageJobPostingActivity.class));

        } else if (id == R.id.nav_posting) {
            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Posting", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } else if (id == R.id.nav_contest) {
            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Contest", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }else if (id == R.id.nav_settings) {

            startActivity(new Intent(mContext,SettingActivity.class));
               Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Settings", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }else if (id == R.id.nav_support) {
                Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Support", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(TopServiceAdapter!=null)
            TopServiceAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        TopServiceAdapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(TopServiceAdapter!=null)
            TopServiceAdapter.startListening();
    }

    private void setupSlider() {
        image_list = new HashMap<>();
        final DatabaseReference Fbanner = database.getReference("popular");
        Fbanner.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot postsnapshot : dataSnapshot.getChildren())
                {
                    PopularServicesModel banner = postsnapshot.getValue(PopularServicesModel.class);
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
        mslider.setDuration(4000);

    }

}
