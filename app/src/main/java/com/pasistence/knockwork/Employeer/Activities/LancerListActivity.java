package com.pasistence.knockwork.Employeer.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Employeer.Adapters.LancerListAdapter;
import com.pasistence.knockwork.Employeer.Models.LancerList;
import com.pasistence.knockwork.R;

import java.util.ArrayList;

public class LancerListActivity extends AppCompatActivity {
    Context mContext;
    MaterialSearchBar searchBar;
    RecyclerView recyclerLancer;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<LancerList> lancerList = new ArrayList<LancerList>();
    LancerListAdapter lancerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancer_list);
        mInit();


        LancerList lancers = new LancerList("1","Lorem Ipsum","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.","United State"," $ 25","80%","https://content-static.upwork.com/uploads/2014/10/01073427/profilephoto1.jpg");

        LancerList lancers2 = new LancerList("2","Cupcake","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.","United State"," $ 55","70%","https://cdn.cultofmac.com/wp-content/uploads/2011/10/97571564a70014ca5658b67f64f2ce23_1253524914.jpeg");

        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers);
        lancerList.add(lancers2);


        lancerListAdapter = new LancerListAdapter(LancerListActivity.this, lancerList);
        recyclerLancer.setAdapter(lancerListAdapter);
        lancerListAdapter.notifyDataSetChanged();


        loadSuggestList();

    }

    private void mInit() {
        mContext = LancerListActivity.this;

        searchBar = (MaterialSearchBar)findViewById(R.id.search_bar_lancer);
        recyclerLancer = (RecyclerView)findViewById(R.id.recycler_lancer_list);
        recyclerLancer.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerLancer.setLayoutManager(layoutManager);
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
