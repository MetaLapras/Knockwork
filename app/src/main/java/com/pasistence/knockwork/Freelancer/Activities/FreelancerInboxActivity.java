package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
import com.pasistence.knockwork.R;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;
import java.util.Date;

import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

public class FreelancerInboxActivity extends DemoDialogsActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "freeInbox";

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
                    Date date = new Date();
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

    }
    /********-----------------------------------------------*********/

    @Override
    public void onDialogClick(Dialog dialog) {
        super.onDialogClick(dialog);
        mContext.startActivity(new Intent(mContext,CustomLayoutMessagesActivity.class)
                .putExtra("lancerUid",getUid(mContext))
                .putExtra("clientUid",dialog.getId()));

        //CustomLayoutMessagesActivity.open(this);
    }

    private void initAdapter() {
        super.dialogsAdapter=new DialogsListAdapter<>(R.layout.item_custom_chat_dialog,super.imageLoader);
        super.dialogsAdapter.setItems(dialogs);
        super.dialogsAdapter.setOnDialogClickListener(this);
        super.dialogsAdapter.setOnDialogLongClickListener(this);
        super.dialogsAdapter.notifyDataSetChanged();
        dialogsList.setAdapter(super.dialogsAdapter);

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
        getMenuInflater().inflate(R.menu.freelancer_inbox, menu);
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
            startActivity(new Intent(FreelancerInboxActivity.this,FreeLancerDashboardActivity.class));

        } else if (id == R.id.nav_inbox) {


        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(FreelancerInboxActivity.this,ManageBidsActivity.class));

        } else if (id == R.id.nav_active) {

        } else if (id == R.id.nav_manage_jobs) {
        //    startActivity(new Intent(FreelancerInboxActivity.this,JobPoastingActivity.class));

        } else if (id == R.id.nav_proposal) {

        }else if (id == R.id.nav_settings) {
            startActivity(new Intent(FreelancerInboxActivity.this,FreelancerSettingActivity.class));


        }else if (id == R.id.nav_support) {


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
