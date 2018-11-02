package com.pasistence.knockwork.ChatBox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pasistence.knockwork.ChatBox.ChatModel.Message;
import com.pasistence.knockwork.ChatBox.DummyData.MessagesFixtures;
import com.pasistence.knockwork.Common.AppUtils;
import com.pasistence.knockwork.R;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

public class CustomLayoutMessagesActivity extends DemoMessagesActivity
        implements MessagesListAdapter.OnMessageLongClickListener<Message>,
        MessageInput.InputListener,
        MessageInput.AttachmentsListener{

    public static void open(Context context){
        context.startActivity(new Intent(context, CustomLayoutMessagesActivity.class));
    }
    private MessagesList messagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout_messages);
        messagesList = (MessagesList) findViewById(R.id.messagesList);
        initAdapter();
        MessageInput input = (MessageInput)findViewById(R.id.input);
        input.setInputListener(this);
        input.setAttachmentsListener(this);
    }


    @Override
    public boolean onSubmit(CharSequence input) {
        messagesAdapter.addToStart(
                MessagesFixtures.getTextMessage(input.toString()),true);
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
