package com.pasistence.knockwork.Client.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.ChattingAdapter;
import com.pasistence.knockwork.Adapter.InboxListAdapter;
import com.pasistence.knockwork.Adapter.LancerListAdapter;
import com.pasistence.knockwork.Adapter.ManageJobPostingAdapter;
import com.pasistence.knockwork.Model.ChattingModel;
import com.pasistence.knockwork.Model.InboxDataModel;
import com.pasistence.knockwork.Model.LancerListModel;
import com.pasistence.knockwork.R;

import java.util.ArrayList;
import java.util.List;

public class ChattingActivity extends AppCompatActivity {

    Context mContext;
    RecyclerView chattingrecyclerview;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<ChattingModel> chattingModels = new ArrayList<ChattingModel>();
    ChattingAdapter chattingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);


        mInit();

        ChattingModel lancers = new ChattingModel("1", "https://content-static.upwork.com/uploads/2014/10/01073427/profilephoto1.jpg", "Chepmen triou", "write a message", "");
        ChattingModel lancers2 = new ChattingModel("2", "https://cdn.cultofmac.com/wp-content/uploads/2011/10/97571564a70014ca5658b67f64f2ce23_1253524914.jpeg", "Asedsbv nfewh", "write a message", "");
        ChattingModel lancers3 = new ChattingModel("3", "https://i.cricketcb.com/stats/img/faceImages/8364.jpg", "Qwsx Lcdskc", "write a message", "");

        chattingModels.add(lancers);
        chattingModels.add(lancers2);
        chattingModels.add(lancers3);
        chattingModels.add(lancers);
        chattingModels.add(lancers2);
        chattingModels.add(lancers3);
        chattingModels.add(lancers);
        chattingModels.add(lancers2);
        chattingModels.add(lancers3);
        chattingModels.add(lancers);
        chattingModels.add(lancers2);
        chattingModels.add(lancers3);
        chattingModels.add(lancers);
        chattingModels.add(lancers2);
        chattingModels.add(lancers3);


        chattingAdapter = new ChattingAdapter(mContext, chattingModels);
        chattingrecyclerview.setAdapter(chattingAdapter);
        chattingAdapter.notifyDataSetChanged();
    }



    private void mInit()
    {
        mContext = ChattingActivity.this;

        //searchBar = (MaterialSearchBar)findViewById(R.id.search_bar_lancer);
        chattingrecyclerview = (RecyclerView)findViewById(R.id.chatting_recycler_view);
        chattingrecyclerview.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        chattingrecyclerview.setLayoutManager(layoutManager);

    }

}
