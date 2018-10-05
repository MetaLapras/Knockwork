package com.pasistence.knockwork.Employeer.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Employeer.Adapters.LancerListAdapter;
import com.pasistence.knockwork.Employeer.Adapters.SearchAdapterLancer;
import com.pasistence.knockwork.Employeer.Models.LancerList;
import com.pasistence.knockwork.R;

import java.util.ArrayList;
import java.util.List;

public class LancerListActivity extends AppCompatActivity {
    private static final String TAG = "search";
    Context mContext;
    MaterialSearchBar searchBar;
    RecyclerView recyclerLancer;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<LancerList> lancerList = new ArrayList<LancerList>();
    LancerListAdapter lancerListAdapter;
    LancerListAdapter searchAdapter;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancer_list);
        mInit();


        LancerList lancers = new LancerList("1","Lorem Ipsum","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.","United State"," $ 25","80%","https://content-static.upwork.com/uploads/2014/10/01073427/profilephoto1.jpg","Web Developer");
        LancerList lancers2 = new LancerList("2","Cupcake","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.","United State"," $ 55","70%","https://cdn.cultofmac.com/wp-content/uploads/2011/10/97571564a70014ca5658b67f64f2ce23_1253524914.jpeg","Android Developer");
        LancerList lancers3 = new LancerList("3","Jelly Bean","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ","United State"," $ 55","70%","https://i.cricketcb.com/stats/img/faceImages/8364.jpg","Software Developer");

        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers3);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers3);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers3);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers3);
        lancerList.add(lancers);
        lancerList.add(lancers2);
        lancerList.add(lancers3);



        loadSuggestList();

        //Setup search bar
        searchBar.setHint("Search");
        searchBar.setLastSuggestions(suggestList);
        searchBar.setCardViewElevation(10);
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                List<String>suggest = new ArrayList<String>();
                for(String search : suggestList)
                {
                    if(search.toLowerCase().contains(searchBar.getText().toLowerCase()))
                        suggest.add(search);
                }

                searchBar.setLastSuggestions(suggest);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                {
                    recyclerLancer.setAdapter(lancerListAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });


        lancerListAdapter = new LancerListAdapter(LancerListActivity.this, lancerList);
        recyclerLancer.setAdapter(lancerListAdapter);
        lancerListAdapter.notifyDataSetChanged();


    }

    private void mInit() {
        mContext = LancerListActivity.this;

        searchBar = (MaterialSearchBar)findViewById(R.id.search_bar_lancer);
        recyclerLancer = (RecyclerView)findViewById(R.id.recycler_lancer_list);
        recyclerLancer.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerLancer.setLayoutManager(layoutManager);
    }

   private void startSearch(CharSequence text) {
       ArrayList<LancerList> searchLancer = new ArrayList<LancerList>();
        for(LancerList list : lancerList){
            if(list.getCategory().equals(text.toString())){

                searchLancer.add(list);
                Log.e(TAG, searchLancer.toString());

                searchAdapter = new LancerListAdapter(LancerListActivity.this,searchLancer);
                recyclerLancer.setAdapter(searchAdapter);
            }
        }

    }

    private void loadSuggestList() {
        //get Data into the list
        /*for(LancerList list : lancerList){
            suggestList.add(list.getCategory());
        }*/
        suggestList.add("Software Developer");
        suggestList.add("Web Developer");
        suggestList.add("Android Developer");
        searchBar.setLastSuggestions(suggestList);
    }

}
