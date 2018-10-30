package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;

import com.facebook.login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.ClientPopularServiceAdapter;
import com.pasistence.knockwork.Adapter.ClientTopServiceAdapter;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Freelancer.Activities.JobPoastingActivity;
import com.pasistence.knockwork.LoginActivity;
import com.pasistence.knockwork.Model.ApiResponse.ApiResponseLancer;
import com.pasistence.knockwork.Model.PopularServicesModel;
import com.pasistence.knockwork.Model.ResponseTopService;
import com.pasistence.knockwork.Model.UserData;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "cdash-->";
    Boolean isLancer=false;
    Boolean isClient=false;

    Context mContext;
    RecyclerView recyclerPopularServices,recyclerTopServices;
    RecyclerView.LayoutManager GridlayoutManager,LinearlayoutManager ;
    SwipeRefreshLayout refreshLayout;
    CardView SearchBar;

    //set up popular services adapter
    MyApi mService;
    ClientPopularServiceAdapter popularServiceAdapter;
    ClientTopServiceAdapter topServiceAdapter;
    List<PopularServicesModel> popularServicesModelList = new ArrayList<PopularServicesModel>();
    List<ResponseTopService> topServicesModelList = new ArrayList<ResponseTopService>();

    TextView txtUserName,txtUserEmail;
    CircleImageView imgUserProfile;

//    public FirebaseDatabase database;
//    public DatabaseReference popular_dataReference ;
//    public DatabaseReference Top_dataReference ;
//    FirebaseRecyclerAdapter<TopServicesModel,ViewHolderTopServices> TopServiceAdapter;

    //Sliders
    HashMap<String,String> image_list;
    SliderLayout mslider;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mInit();

      //  isLancer=getIntent().getBooleanExtra("isLancer",false);
        //isClient=getIntent().getBooleanExtra("isClient",false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        txtUserName = (TextView)header.findViewById(R.id.txt_user_name);
        txtUserEmail = (TextView)header.findViewById(R.id.txt_user_emailid);
        imgUserProfile = (CircleImageView)header.findViewById(R.id.user_profile_image) ;

       /* UserData data = new Common().getUserData();

        if(data!=null){
            txtUserEmail.setText(data.getEmail());
            txtUserName.setText(data.getDisplayName());

            Picasso.with(mContext).load(data.getPhotoUrl()).into(imgUserProfile);
        }
*/

    try {
       /* FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();
        Log.e(TAG,auth.getDisplayName());

        Log.e(TAG, auth.getDisplayName()+"");
        Log.e(TAG, auth.getUid()+"");
        Log.e(TAG, auth.getEmail()+"");
        Log.e(TAG, auth.getPhotoUrl().toString());
        Log.e(TAG, auth.getProviders().toString()+"");
        Log.e(TAG, auth.getPhoneNumber()+"");*/

        txtUserName.setText(Common.UserName);
        txtUserEmail.setText(Common.UserEmail);
        Picasso.with(mContext).load(Common.UserPhoto).into(imgUserProfile);


    }catch (Exception e){
        e.printStackTrace();
    }


        //init Fire base
//        database = FirebaseDatabase.getInstance();
//        popular_dataReference = database.getReference("popular");
//        Top_dataReference = database.getReference("top");


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
                    //loadTopServices();
                }else
                {
                    Common.commonDialog(mContext,"Please Check Your Internet Connection !");
                    return;
                }

            }
        });



     /*   FirebaseRecyclerOptions<TopServicesModel> topOptions = new FirebaseRecyclerOptions.Builder<TopServicesModel>()
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
//                        Snackbar.make(view, ""+clickitem.getHead(), Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();

                    startActivity(new Intent(mContext,LancersActivity.class));
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

        TopServiceAdapter.startListening();*/


       /* refreshLayout.setColorSchemeResources(R.color.colorPrimary,
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
*/
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

        mContext                = DashboardActivity.this;
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

                topServiceAdapter = new ClientTopServiceAdapter(DashboardActivity.this,topServicesModelList);
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
            @Override
            public void onResponse(Call<List<ResponseTopService>> call, Response<List<ResponseTopService>> response) {
                //Log.e(TAG, response.body().toString());

                topServicesModelList = response.body();
                topServiceAdapter = new ClientTopServiceAdapter(DashboardActivity.this,topServicesModelList);
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
                popularServiceAdapter = new ClientPopularServiceAdapter(DashboardActivity.this,popularServicesModelList);
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

        if (id == R.id.action_logout) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
            alertDialogBuilder.setMessage("Are you Sure Want to Logout")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                            PreferenceUtils.setSignIn(mContext,false);
                            //Setting Shared Preference
                            PreferenceUtils.setDisplayName(mContext,"");
                            PreferenceUtils.setUid(mContext,"");
                            PreferenceUtils.setEmail(mContext,"");
                            PreferenceUtils.setPhotoUrl(mContext,"");
                            PreferenceUtils.setProvider(mContext,"");
                            PreferenceUtils.setPhoneNumber(mContext,"");

                            //FirebaseAuth LogOut
                            mAuth.signOut();

                            Intent signin = new Intent(mContext,LoginActivity.class);
                            signin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(signin);
                            finish();

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();




            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Home", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
//       //     startActivity(new Intent(mContext,DashboardActivity.class));

        } else if (id == R.id.nav_inbox) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Inbox", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            startActivity(new Intent(mContext,InboxActivity.class));


        } else if (id == R.id.nav_notification) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Notification", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        } else if (id == R.id.nav_manage) {
 //           Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Manage", Snackbar.LENGTH_LONG)
   //                 .setAction("Action", null).show();
            startActivity(new Intent(mContext,ManageJobPostActivity.class));

        } else if (id == R.id.nav_posting) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Posting", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            startActivity(new Intent(mContext,ClientJobPostingActivity.class));

        } else if (id == R.id.nav_contest) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Contest", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        }else if (id == R.id.nav_settings) {

            startActivity(new Intent(mContext,SettingActivity.class));
  //             Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Settings", Snackbar.LENGTH_LONG)
    //                .setAction("Action", null).show();

        }else if (id == R.id.nav_support) {
//                Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Support", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

/*    private void setupSlider() {
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

    }*/

}
