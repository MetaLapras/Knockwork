package com.pasistence.knockwork.Freelancer.Activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pasistence.knockwork.Common.Common;
import com.pasistence.knockwork.Model.ApiResponse.ApiBidsResponse;
import com.pasistence.knockwork.R;
import com.pasistence.knockwork.Remote.MyApi;

import java.util.List;

import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.pasistence.knockwork.Common.PreferenceUtils.getLid;
import static com.pasistence.knockwork.Common.PreferenceUtils.getUid;

public class ManageBidsActivity extends FreeLancerBaseActivity implements View.OnClickListener
{

    private static final String TAG = "managebids";
    Context mContext;
    TextView txtBids,txtTotalSpend,txtlast30,txtAverage;
    FButton btnAdd10,btnAdd50,btnAdd100,btnAdd300;
    String Uid,Lid;
    MyApi mService;
    List<ApiBidsResponse> Bids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_manage_bids);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_manage_bids, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);

        mInit();

    }

    private void mInit() {

        mContext = ManageBidsActivity.this;
        txtBids = (TextView)findViewById(R.id.txt_bids);
        txtlast30 = (TextView)findViewById(R.id.txt_last30days);
        txtAverage = (TextView)findViewById(R.id.txt_average);
        txtTotalSpend = (TextView)findViewById(R.id.txt_totalSpend);
        btnAdd10 = (FButton)findViewById(R.id.btn_add10);
        btnAdd50 = (FButton)findViewById(R.id.btn_add50);
        btnAdd100 = (FButton)findViewById(R.id.btn_add100);
        btnAdd300 = (FButton)findViewById(R.id.btn_add300);

        Uid = getUid(mContext);
        Lid = getLid(mContext);
        mService = Common.getApi();
        Log.e(TAG, Lid);
        Log.e(TAG, Uid );

        loadBids();

        btnAdd300.setOnClickListener(this);
        btnAdd100.setOnClickListener(this);
        btnAdd50.setOnClickListener(this);
        btnAdd10.setOnClickListener(this);

    }

    private void loadBids() {
        mService.getBidsById(Uid,Lid).enqueue(new Callback<List<ApiBidsResponse>>() {
            @Override
            public void onResponse(Call<List<ApiBidsResponse>> call, Response<List<ApiBidsResponse>> response) {
                Log.e(TAG,response.body().toString());
                Bids = response.body();

                for (ApiBidsResponse b: Bids) {
                    txtBids.setText(b.getBids());
                    txtTotalSpend.setText(b.getTotalspend());
                    txtlast30.setText(b.getBids());

                    int avg = (Integer.valueOf(b.getTotalspend())/30);
                    txtAverage.setText(String.valueOf(avg) + "%");

                }

            }

            @Override
            public void onFailure(Call<List<ApiBidsResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v == btnAdd10){
            updateBids(10);
        }
        if(v == btnAdd50){
            updateBids(50);
        }
        if(v == btnAdd100){
            updateBids(100);
        }
        if(v == btnAdd300){
            updateBids(300);
        }
    }

    private void updateBids(int bid) {
        mService.IncreaseBids(Uid,Lid,bid).enqueue(new Callback<ApiBidsResponse>() {
            @Override
            public void onResponse(Call<ApiBidsResponse> call, Response<ApiBidsResponse> response) {
                Log.e(TAG, response.body().toString() );
                ApiBidsResponse result = response.body();
                if(!result.getError()){
                    Common.commonDialog(mContext,"Bids Added Successfully");
                    loadBids();
                }else {
                    Common.commonDialog(mContext,"Something Went Wrong! Please Try Again");
                }
            }

            @Override
            public void onFailure(Call<ApiBidsResponse> call, Throwable t) {
                t.printStackTrace();
                Common.commonDialog(mContext,"Server Not Found");
            }
        });
    }
}
