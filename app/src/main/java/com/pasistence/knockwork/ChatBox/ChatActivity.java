package com.pasistence.knockwork.ChatBox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pasistence.knockwork.ChatBox.ChatModel.Dialog;
import com.pasistence.knockwork.ChatBox.DummyData.DialogsFixtures;
import com.pasistence.knockwork.R;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

public class ChatActivity extends DemoDialogsActivity {

    //CustomlayoutDialogsActivity
    public static void open(Context context) {
        context.startActivity(new Intent(context, ChatActivity.class));
    }

    private DialogsList dialogsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        dialogsList = (DialogsList)  findViewById(R.id.dialogsList);
        initAdapter();

    }

    @Override
    public void onDialogClick(Dialog dialog) {
        super.onDialogClick(dialog);
        CustomLayoutMessagesActivity.open(this);
    }

    private void initAdapter() {
        super.dialogsAdapter=new DialogsListAdapter<>(R.layout.item_custom_chat_dialog,super.imageLoader);
        super.dialogsAdapter.setItems(DialogsFixtures.getDialogs());
        super.dialogsAdapter.setOnDialogClickListener(this);
        super.dialogsAdapter.setOnDialogLongClickListener(this);
        dialogsList.setAdapter(super.dialogsAdapter);
    }
}
