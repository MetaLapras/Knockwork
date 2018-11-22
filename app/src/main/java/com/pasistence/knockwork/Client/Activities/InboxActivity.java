package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Common.PreferenceUtils;
import com.pasistence.knockwork.Freelancer.Activities.JobPoastingActivity;
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

public class InboxActivity extends DemoDialogsActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DialogsList dialogsList;
    Context mContext;
    private static final String TAG = "clientInbox";
    public FirebaseAuth mAuth;

    ArrayList<Dialog> dialogs = new ArrayList<Dialog>();

    FirebaseDatabase mDatabse;
    DatabaseReference mReference;


    ShimmerLayout shimmerlayout;
    RelativeLayout AfterLoad;
    TextView txtUserName,txtUserEmail;
    CircleImageView imgUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        mContext = InboxActivity.this;
        mAuth = FirebaseAuth.getInstance();


        shimmerlayout = (ShimmerLayout) findViewById(R.id.shimmer_layout);
        shimmerlayout.startShimmerAnimation();

        AfterLoad = (RelativeLayout)findViewById(R.id.relative_afterload);

        /********-----------------------------------------------*********/
        dialogsList = (DialogsList)  findViewById(R.id.dialogsList);
        /********-----------------------------------------------*********/


        mDatabse = FirebaseDatabase.getInstance();
        mReference = mDatabse.getReference(Common.chatbox);


        Query query = mReference.orderByChild("client_id").equalTo(getUid(mContext));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, dataSnapshot.toString());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("chat").exists()) {
                        FirebaseUidModel model = ds.getValue(FirebaseUidModel.class);
                        Log.e(TAG + "get", model.toString());

                        User user = new User(model.getLancer_id(), model.getLancer_name(), model.getLancer_url(), true);
                        //  Log.e(TAG, user.toString());
                        Date date = null;
                        try {
                            date = model.getLast_date() == null ? new Date() : new Date(model.getLast_date());
                        } catch (Exception e) {
                            date = new Date();
                        }
                        Message message = new Message(model.getLancer_id(), user, model.getLast_message(), date);

                        ArrayList<User> userlist = new ArrayList<>();
                        userlist.add(user);

                        Dialog d = new Dialog(
                                model.getLancer_id(),
                                model.getLancer_name(),
                                model.getLancer_url(),
                                userlist,
                                message,
                                0
                        );
                            dialogs.add(d);


                    }
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
        View header = navigationView.getHeaderView(0);

        txtUserName = (TextView)header.findViewById(R.id.txt_user_name);
        txtUserEmail = (TextView)header.findViewById(R.id.txt_user_emailid);
        imgUserProfile = (CircleImageView)header.findViewById(R.id.user_profile_image) ;

        try {

            txtUserName.setText(Common.UserName);
            txtUserEmail.setText(Common.UserEmail);
            //txtUserEmail.setText(getUid(mContext));
            Picasso.with(mContext).load(Common.UserPhoto).into(imgUserProfile);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /********-----------------------------------------------*********/
//    public static ArrayList<Dialog> doSelectionSort(int[] arr) {
//        for (int i = 0; i < arr.length - 1; i++) {
//            int pos = i;
//            // find position of smallest num between (i + 1)th element and last element
//            for (int j = i + 1; j <= arr.length; j++) {
//                if (arr[j] < arr[pos])
//                    pos = j;
//
//                // Swap min (smallest num) to current position on array
//                int min = arr[pos];
//                arr[pos] = arr[i];
//                arr[i] = min;
//            }
//        }
//        return arr;
//    }


    public ArrayList<Dialog> sort(ArrayList<Dialog> dialogs)
    {
        int n = dialogs.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (dialogs.get(j).getLastMessage().getCreatedAt().after(dialogs.get(min_idx).getLastMessage().getCreatedAt()))
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            Dialog temp = dialogs.get(min_idx);
            dialogs.set(min_idx,dialogs.get(i));
            dialogs.set(i,temp);
            //arr[min_idx] = arr[i];
            //arr[i] = temp;
        }
        return dialogs;
    }
    /*******************/

    @Override
    public void onDialogClick(Dialog dialog) {
        super.onDialogClick(dialog);
       // CustomLayoutMessagesActivity.open(this);
        mContext.startActivity(new Intent(mContext,CustomLayoutMessagesActivity.class)
                .putExtra("lancerUid",dialog.getId())
                .putExtra("clientUid",getUid(mContext)));
    }

    private void initAdapter() {
        super.dialogsAdapter=new DialogsListAdapter<>(R.layout.item_custom_chat_dialog,super.imageLoader);
        super.dialogsAdapter.setItems(sort(dialogs));
        super.dialogsAdapter.setOnDialogClickListener(this);
        super.dialogsAdapter.setOnDialogLongClickListener(this);
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
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            startActivity(new Intent(mContext,DashboardActivityClient.class));
            finish();

        } else if (id == R.id.nav_inbox) {

            startActivity(new Intent(mContext,InboxActivity.class));
            finish();

        } else if (id == R.id.nav_notification) {


        } else if (id == R.id.nav_manage) {

            startActivity(new Intent(mContext,ManageJobPostActivity.class));
            finish();

        } else if (id == R.id.nav_posting) {

            startActivity(new Intent(mContext,ClientJobPostingActivity.class));
            finish();

        } else if (id == R.id.nav_contest) {
            startActivity(new Intent(mContext,ClientJobContestActivity.class));
            finish();

        }else if (id == R.id.nav_settings) {

            startActivity(new Intent(mContext,SettingActivity.class));
            finish();

        }else if (id == R.id.nav_support) {


        }
        else if (id == R.id.nav_logout) {
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
}
