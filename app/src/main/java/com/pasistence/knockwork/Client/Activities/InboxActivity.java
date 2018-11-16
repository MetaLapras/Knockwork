package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.format.DateFormat;
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
import com.pasistence.knockwork.Freelancer.Activities.JobPoastingActivity;
import com.pasistence.knockwork.R;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.Date;

import io.supercharge.shimmerlayout.ShimmerLayout;

import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

public class InboxActivity extends DemoDialogsActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DialogsList dialogsList;
    Context mContext;
    private static final String TAG = "clientInbox";

    ArrayList<Dialog> dialogs = new ArrayList<Dialog>();

    FirebaseDatabase mDatabse;
    DatabaseReference mReference;


    ShimmerLayout shimmerlayout;
    RelativeLayout AfterLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        mContext = InboxActivity.this;

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
                    FirebaseUidModel model = ds.getValue(FirebaseUidModel.class);
                    Log.e(TAG+"get", model.toString());

                    User user = new User(model.getLancer_id(),model.getLancer_name(),model.getLancer_url(),true);
                  //  Log.e(TAG, user.toString());
                    Date date = new Date();
                    Message message = new Message(model.getLancer_id(),user,model.getLast_message(),date);

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

    }

    /********-----------------------------------------------*********/

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
        super.dialogsAdapter.setItems(dialogs);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inbox, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Home", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            startActivity(new Intent(mContext,DashboardActivity.class));

        } else if (id == R.id.nav_inbox) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Inbox", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
         //   startActivity(new Intent(mContext,InboxActivity.class));


        } else if (id == R.id.nav_notification) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Notification", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        } else if (id == R.id.nav_manage) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Manage", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            startActivity(new Intent(mContext,ManageJobPostActivity.class));

        } else if (id == R.id.nav_posting) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Posting", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            startActivity(new Intent(mContext,JobPoastingActivity.class));

        } else if (id == R.id.nav_contest) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Contest", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        }else if (id == R.id.nav_settings) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Settings", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

            startActivity(new Intent(mContext,SettingActivity.class));

        }else if (id == R.id.nav_support) {
//            Snackbar.make(findViewById(R.id.swipe_refresh_layout), "Support", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
