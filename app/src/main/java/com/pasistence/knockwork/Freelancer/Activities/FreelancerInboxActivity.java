package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pasistence.knockwork.ChatBox.ChatModel.Dialog;
import com.pasistence.knockwork.ChatBox.ChatModel.FirebaseUidModel;
import com.pasistence.knockwork.ChatBox.ChatModel.Message;
import com.pasistence.knockwork.ChatBox.ChatModel.User;
import com.pasistence.knockwork.ChatBox.CustomLayoutMessagesActivity;
import com.pasistence.knockwork.ChatBox.DemoDialogsActivity;
import com.pasistence.knockwork.ChatBox.DummyData.DialogsFixtures;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.ProfileActivity;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.SelectionActivity;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import io.supercharge.shimmerlayout.ShimmerLayout;

import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

public class FreelancerInboxActivity extends DemoDialogsActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private static final String TAG = "freeInbox";
    ShimmerLayout shimmerlayout;
    RelativeLayout AfterLoad;
    TextView txtUserName,txtUserEmail;
    CircleImageView imgUserProfile;

    public FirebaseAuth mAuth;

    //CustomlayoutDialogsActivity
    public static void open(Context context) {
        context.startActivity(new Intent(context, FreelancerInboxActivity.class));
    }

    private DialogsList dialogsList;
    Context mContext;
    ArrayList<Dialog> dialogs = new ArrayList<Dialog>();

    FirebaseDatabase mDatabse;
    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freelancer_inbox);

        mContext=FreelancerInboxActivity.this;
        mAuth = FirebaseAuth.getInstance();

        shimmerlayout = (ShimmerLayout) findViewById(R.id.shimmer_layout);
        shimmerlayout.startShimmerAnimation();

        AfterLoad = (RelativeLayout)findViewById(R.id.relative_afterload);

        /********-----------------------------------------------*********/
        dialogsList = (DialogsList)  findViewById(R.id.dialogsList);

        /********-----------------------------------------------*********/


        mDatabse = FirebaseDatabase.getInstance();
        mReference = mDatabse.getReference(Common.chatbox);

        Query query = mReference.orderByChild("lancer_id").equalTo(getUid(mContext));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, dataSnapshot.toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    FirebaseUidModel model = ds.getValue(FirebaseUidModel.class);
                    Log.e(TAG+"get", model.toString());

                    User user = new User(model.getClient_id(),model.getClient_name(),model.getClient_url(),true);
                    Date date = new Date(model.getLast_date());
                    Message message = new Message(model.getClient_id(),user,model.getLast_message(),date);

                    ArrayList<User> userlist = new ArrayList<>();
                    userlist.add(user);

                    Dialog d = new Dialog(
                            model.getClient_id(),
                            model.getClient_name(),
                            model.getClient_url(),
                            userlist,
                            message,
                            0
                    );

                    dialogs.add(d);
               }

                initAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, databaseError.toString());
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        txtUserName = (TextView)header.findViewById(R.id.txt_user_name);
        txtUserEmail = (TextView)header.findViewById(R.id.txt_user_emailid);
        imgUserProfile = (CircleImageView)header.findViewById(R.id.user_profile_image) ;

        try {

            txtUserName.setText(Common.UserName);
            txtUserEmail.setText(Common.UserEmail);
            //txtUserEmail.setText(getUid(mContext));
            Picasso.with(mContext).load(Common.UserPhoto).into(imgUserProfile);

            imgUserProfile.setOnClickListener(this);
            txtUserName.setOnClickListener(this);
            txtUserEmail.setOnClickListener(this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /********-----------------------------------------------*********/

    @Override
    public void onDialogClick(Dialog dialog) {
        super.onDialogClick(dialog);
        mContext.startActivity(new Intent(mContext,CustomLayoutMessagesActivity.class)
                .putExtra("lancerUid",getUid(mContext))
                .putExtra("clientUid",dialog.getId())
                .putExtra("name",dialog.getDialogName()));

        //CustomLayoutMessagesActivity.open(this);
    }

    private void initAdapter() {
        super.dialogsAdapter=new DialogsListAdapter<>(R.layout.item_custom_chat_dialog,super.imageLoader);
        super.dialogsAdapter.setItems(dialogs);
        super.dialogsAdapter.setOnDialogClickListener(this);
        super.dialogsAdapter.setOnDialogLongClickListener(this);
        super.dialogsAdapter.notifyDataSetChanged();
        dialogsList.setAdapter(super.dialogsAdapter);

        AfterLoad.setVisibility(View.GONE);
        shimmerlayout.stopShimmerAnimation();


    }
    /********-----------------------------------------------*********/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(mContext,FreeLancerDashboardActivity.class));

        } else if (id == R.id.nav_inbox) {
            startActivity(new Intent(mContext,FreelancerInboxActivity.class));

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(mContext,ManageBidsActivity.class));

        } else if (id == R.id.nav_active) {

        } else if (id == R.id.nav_manage_jobs) {
            //    startActivity(new Intent(FreelancerInboxActivity.this,JobPoastingActivity.class));

        } else if (id == R.id.nav_proposal) {

        }else if (id == R.id.nav_settings) {
            startActivity(new Intent(mContext,FreelancerSettingActivity.class));


        }else if (id == R.id.nav_support) {


        }else if (id == R.id.nav_logout) {
            onLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onLogout() {

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

                        Intent signin = new Intent(mContext,SelectionActivity.class);
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


    }

    @Override
    public void onClick(View v) {
        if(v == txtUserName || v == txtUserEmail || v == imgUserProfile){
            startActivity(new Intent(mContext,ProfileActivity.class));
        }
    }
}
