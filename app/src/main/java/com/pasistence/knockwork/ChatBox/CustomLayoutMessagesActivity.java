package com.pasistence.knockwork.ChatBox;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Measure;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.pasistence.knockwork.ChatBox.ChatModel.FirebaseChatMessage;
import com.pasistence.knockwork.ChatBox.ChatModel.FirebaseChatMessagePost;
import com.pasistence.knockwork.ChatBox.ChatModel.FirebaseUidModel;
import com.pasistence.knockwork.ChatBox.ChatModel.Message;
import com.pasistence.knockwork.ChatBox.ChatModel.User;
import com.pasistence.knockwork.ChatBox.DummyData.MessagesFixtures;
import com.pasistence.knockwork.Common.AppUtils;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.R;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;
import static com.pasistence.knockwork.Common.PreferenceUtils.getUserType;

public class CustomLayoutMessagesActivity extends DemoMessagesActivity
        implements MessagesListAdapter.OnMessageLongClickListener<Message>,
        MessageInput.InputListener,
        MessageInput.AttachmentsListener{
    private static final String TAG = "chatFire";
    String LUID,CUID,chatBoxId,cImage,lImage,name;
        FirebaseDatabase mDatabase;
        DatabaseReference mReference;

        ArrayList<Message> messageArrayList = new ArrayList<Message>();

        User user;


    public static void open(Context context){
        context.startActivity(new Intent(context, CustomLayoutMessagesActivity.class));
    }


    private MessagesList messagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout_messages);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LUID = getIntent().getStringExtra("lancerUid");
        CUID = getIntent().getStringExtra("clientUid");


        //Image = getIntent().getStringExtra("image");
        chatBoxId = CUID+"_"+LUID;

        messagesList = (MessagesList) findViewById(R.id.messagesList);
        initAdapter();
        MessageInput input = (MessageInput)findViewById(R.id.input);
        input.setInputListener(this);
        input.setAttachmentsListener(this);

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("chatbox");


        mReference.child(chatBoxId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG+"get", dataSnapshot.toString());
                final FirebaseUidModel model = dataSnapshot.getValue(FirebaseUidModel.class);
       //         Log.e(TAG+"get", model.toString());

                name = model.getLancer_name();
                lImage = model.getLancer_url();
                cImage = model.getClient_url();
                getSupportActionBar().setTitle(name);
                Query query = mReference.child(chatBoxId).child("chat").orderByChild("created");
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Log.e(TAG+"add", dataSnapshot.toString());
                        FirebaseChatMessage message = dataSnapshot.getValue(FirebaseChatMessage.class);
                        Log.e(TAG+"m",message.toString());

                        String id=getUid(CustomLayoutMessagesActivity.this).equals(message.getUid())?"0":"1";
                        user=new User(id,name,
                                getUserType(CustomLayoutMessagesActivity.this).equals(Common.Lancer)?cImage:lImage
                                ,true);


                        Date date = new Date(message.getCreated());

                        Message m = new Message(id,user,message.getText(),date);

                        messagesAdapter.addToStart(m,true);
                        messagesAdapter.notifyDataSetChanged();

                        mReference.child(chatBoxId).child("last_message").setValue(message.getText());
                        mReference.child(chatBoxId).child("last_date").setValue(message.getCreated());

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Log.e(TAG+"chang", dataSnapshot.toString() );
                        Log.e(TAG+"add", dataSnapshot.toString());
                        FirebaseChatMessage message = dataSnapshot.getValue(FirebaseChatMessage.class);
                        Log.e(TAG+"m",message.toString());

                        if(!getUid(CustomLayoutMessagesActivity.this).equals(message.getUid())) {
                            String id = getUid(CustomLayoutMessagesActivity.this).equals(message.getUid()) ? "0" : "1";
                            User user = new User(id, "",
                                    getUserType(CustomLayoutMessagesActivity.this).equals(Common.Lancer) ? cImage : lImage
                                    , true);
                            //Date date = message.getCreated();
                            Date date = new Date(message.getCreated() * 1000);
                            Message m = new Message(id, user, message.getText(), date);
                            messagesAdapter.addToStart(m, true);
                            messagesAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                        Log.e(TAG+"rem", dataSnapshot.toString() );
                        messagesAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Log.e(TAG+"mov", dataSnapshot.toString() );
                        messagesAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, databaseError.toString() );
                        messagesAdapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG+"get", databaseError.toString());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSubmit(CharSequence input) {
        //Adding data to firebase Chatbox -> id -> Chat
//        FirebaseChatMessage chat = new FirebaseChatMessage(input.toString(),getUid(CustomLayoutMessagesActivity.this));
        FirebaseChatMessagePost chatPost = new FirebaseChatMessagePost(ServerValue.TIMESTAMP,input.toString(),getUid(CustomLayoutMessagesActivity.this));

        mReference.child(chatBoxId)
                  .child("chat")
                .push().setValue(chatPost);

        /*messagesAdapter.addToStart(
                MessagesFixtures.getTextMessage(input.toString()),true);
        AppUtils.showToast(this,input.toString(),true);*/
        return true;
    }

    @Override
    public void onAddAttachments() {
        messagesAdapter.addToStart(
                MessagesFixtures.getImageMessage(),true
        );
    }

    @Override
    public void onMessageLongClick(Message message) {
        AppUtils.showToast(this,"Your custom long click handler",true);
    }

    private void initAdapter() {
        MessageHolders holdersConfig = new MessageHolders()
                .setIncomingTextLayout(R.layout.item_custom_incoming_text_message)
                .setOutcomingTextLayout(R.layout.item_custom_outcoming_text_message)
                .setIncomingImageLayout(R.layout.item_custom_incoming_image_message)
                .setOutcomingImageLayout(R.layout.item_custom_outcoming_image_message);

        super.messagesAdapter = new MessagesListAdapter<>(super.senderId, holdersConfig, super.imageLoader);
        super.messagesAdapter.setOnMessageLongClickListener(this);
        super.messagesAdapter.setLoadMoreListener(this);
        messagesList.setAdapter(super.messagesAdapter);
    }
}
