package com.pasistence.knockwork.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Interfaces.ItemClickListener;
import com.pasistence.knockwork.Models.PopularServices;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.ViewHolder.ViewHolderPopularServices;
import com.squareup.picasso.Picasso;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context mContext;
    RecyclerView recyclerPopularServices,recyclerTopServices;
    RecyclerView.LayoutManager GridlayoutManager,LinearLayoutManager ;
    SwipeRefreshLayout refreshLayout;

    public FirebaseDatabase database;
    public DatabaseReference databaseReference ;


    FirebaseRecyclerAdapter<PopularServices,ViewHolderPopularServices> popularAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mInit();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //init Fire base
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("popular");


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
        //Default Load
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
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


        Common.InitFirebase("popular");
        FirebaseRecyclerOptions<PopularServices> options = new FirebaseRecyclerOptions.Builder<PopularServices>()
                .setQuery(databaseReference,PopularServices.class)
                .build();

        popularAdapter = new FirebaseRecyclerAdapter<PopularServices, ViewHolderPopularServices>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderPopularServices viewHolder, int position, @NonNull PopularServices model) {
                viewHolder.txtPopularservice.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imgPopularservice);

                final PopularServices clickitem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                       /* Intent intent = new Intent(Home.this, FoodMenu.class);
                        intent.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(intent);*/
                        Toast.makeText(mContext,""+clickitem.getName(),Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public ViewHolderPopularServices onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.custome_template_popularservices,parent,false);
                return new ViewHolderPopularServices(itemView);

            }
        };



    }

    private void loadTopServices() {

    }

    private void loadPopularServices() {
        popularAdapter.notifyDataSetChanged();
        popularAdapter.startListening();
        recyclerPopularServices.setAdapter(popularAdapter);
        recyclerPopularServices.scheduleLayoutAnimation();
        refreshLayout.setRefreshing(false);

    }

    private void mInit() {
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);

        recyclerPopularServices =(RecyclerView)findViewById(R.id.recycler_popular_services);
        recyclerPopularServices.setHasFixedSize(true);
        LinearLayoutManager     = new LinearLayoutManager(this);
        recyclerPopularServices.setLayoutManager(GridlayoutManager);

        recyclerTopServices     =(RecyclerView)findViewById(R.id.recycler_top_services);
        recyclerTopServices.setHasFixedSize(true);
        GridlayoutManager       = new GridLayoutManager(this,2);
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
            // Handle the camera action
        } else if (id == R.id.nav_inbox) {

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_posting) {

        } else if (id == R.id.nav_contest) {

        }else if (id == R.id.nav_settings) {

        }else if (id == R.id.nav_support) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
