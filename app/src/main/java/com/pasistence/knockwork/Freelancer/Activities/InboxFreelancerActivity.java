package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Adapter.InboxListAdapter;
import com.pasistence.knockwork.Employeer.Activities.InboxActivity;
import com.pasistence.knockwork.Model.InboxDataModel;
import com.pasistence.knockwork.R;

import java.util.ArrayList;

public class InboxFreelancerActivity extends AppCompatActivity {
    Context mContext;
    MaterialSearchBar inboxsearchBar;
    RecyclerView inboxrecyclerview;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<InboxDataModel> inboxDataModels = new ArrayList<InboxDataModel>();
    InboxListAdapter inboxListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_freelancer);

        mInit();


        InboxDataModel lancers = new InboxDataModel("1", "Cristina Afeeba", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s ", "I Can do ", "https://content-static.upwork.com/uploads/2014/10/01073427/profilephoto1.jpg");

        InboxDataModel lancers2 = new InboxDataModel("2", "Afeeba Cristina", "Technology is making online work similar to local work, with added speed, cost, and quality advantages.", "I Can do", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDdFKBrWfM9lqmjc_Cvg4n4BebNmgNt7OWQ59W0SjTU0TcfoubA");

        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);
        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);
        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);
        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);
        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);
        inboxDataModels.add(lancers);
        inboxDataModels.add(lancers2);


        inboxListAdapter = new InboxListAdapter(mContext, inboxDataModels);
        inboxrecyclerview.setAdapter(inboxListAdapter);
        inboxListAdapter.notifyDataSetChanged();


        loadSuggestList();

    }

    private void mInit() {
        mContext = InboxFreelancerActivity.this;

        // inboxsearchBar     = (MaterialSearchBar) findViewById(R.id.inbox_search_bar);
        inboxrecyclerview = (RecyclerView) findViewById(R.id.recycler_inbox_freelancer);
        inboxrecyclerview.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        inboxrecyclerview.setLayoutManager(layoutManager);
    }

    private void startSearch(String text) {
        // adapter = new SearchAdapter(WorkerDisplayList.this, this, database.getWorkerName(text));
        // WorkerListRecyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        // suggestList = database.getNames();
        // materialSearchBar.setLastSuggestions(suggestList);
    }
}
