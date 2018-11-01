package com.pasistence.knockwork.ChatBox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pasistence.knockwork.R;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

public class DemoMessagesActivity extends AppCompatActivity
        implements MessagesListAdapter.SelectionListener,
        MessagesListAdapter.OnLoadMoreListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_messages);
    }

    @Override
    public void onLoadMore(int page, int totalItemsCount) {
        
    }

    @Override
    public void onSelectionChanged(int count) {

    }
}
