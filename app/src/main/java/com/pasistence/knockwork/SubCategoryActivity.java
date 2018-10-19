package com.pasistence.knockwork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.pasistence.knockwork.Client.Activities.LancersActivity;
import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ResponseSubCategory;
import com.pasistence.knockwork.Remote.MyApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryActivity extends AppCompatActivity {
    private static final String TAG ="subCat-->" ;
    Context mContext ;
    ListView listSubcategory;
    CardView cardCategory;
    MaterialSearchBar searchBar;
    TextView txtCategoryTitle;
    String title,image,CatId;
    ImageView imgCardBack;
    MyApi mService;
    ArrayAdapter<String> itemsAdapter;
    List<ResponseSubCategory> subCategoryList;
    LinearLayout list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        mInit();

        Bundle bu;
        bu = getIntent().getExtras();
        title = bu.getString("title");
        image = bu.getString("image");
        CatId = bu.getString("id");

        txtCategoryTitle.setText(title);
        Picasso.with(mContext).load(image).into(imgCardBack);

        mService.getSubCategoryById(CatId)
                .enqueue(new Callback<List<ResponseSubCategory>>() {
                    @Override
                    public void onResponse(Call<List<ResponseSubCategory>> call, Response<List<ResponseSubCategory>> response) {
                        Log.e(TAG, response.body().toString());
                        subCategoryList = response.body();

                        final List<String> labels = new ArrayList<String>();
                        for(int i = 0; i<subCategoryList.size(); i++)
                        {
                            labels.add(subCategoryList.get(i).getSub_categories_title());
                        }

                        itemsAdapter = new ArrayAdapter<String>
                                (mContext,android.R.layout.simple_list_item_1,labels);
                        listSubcategory.setAdapter(itemsAdapter);
                        itemsAdapter.notifyDataSetChanged();




                        listSubcategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(mContext,LancersActivity.class);
                                intent.putExtra("catId",CatId);
                                intent.putExtra("subcatId",subCategoryList.get(position).getSub_categories_id());
                                intent.putStringArrayListExtra("suggestion", (ArrayList<String>) labels);

                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<ResponseSubCategory>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


    }

    private void mInit() {
        mContext = SubCategoryActivity.this;
        listSubcategory = (ListView)findViewById(R.id.list_subcategory);

        cardCategory = (CardView)findViewById(R.id.card_category);
        searchBar = (MaterialSearchBar)findViewById(R.id.category_search_bar);
        txtCategoryTitle = (TextView)findViewById(R.id.txt_Category_title);
        imgCardBack = (ImageView)findViewById(R.id.img_cardback);

       // list = (LinearLayout)findViewById(R.id.list_subcategory);

        mService = Common.getApi();

    }
}
