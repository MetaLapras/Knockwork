package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pasistence.knockwork.Model.ApiResponse.ApiEducationResponse;
import com.pasistence.knockwork.R;

import java.util.ArrayList;

public class FreeLancerEducationAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<ApiEducationResponse.LancerEducation> lancerEducations = new ArrayList<>();
    TextView txtDegree,txtUniversity,txtPercentage,txtPassingyear;
    LayoutInflater inflter;

    public FreeLancerEducationAdapter(Context mContext, ArrayList<ApiEducationResponse.LancerEducation> lancerEducations) {
        this.mContext = mContext;
        this.lancerEducations = lancerEducations;
    }

    @Override
    public int getCount() {
        return lancerEducations.size();
    }

    @Override
    public Object getItem(int position) {
        return lancerEducations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vList;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vList = new View(mContext);
            vList = inflater.inflate(R.layout.custom_freelancer_education_layout,null);
        }else {
            vList = (View)convertView;
        }

    //    convertView = inflter.inflate(R.layout.custom_freelancer_education_layout, null);//set layout for displaying item

        txtDegree = (TextView)vList.findViewById(R.id.txt_degree);
        txtUniversity = (TextView)vList.findViewById(R.id.txt_university);
        txtPassingyear = (TextView)vList.findViewById(R.id.txt_passingyear);
        txtPercentage = (TextView)vList.findViewById(R.id.txt_percentage);

        ApiEducationResponse.LancerEducation lancer = lancerEducations.get(position);

        txtPercentage.setText(lancer.getUserPercentage());
        txtDegree.setText(lancer.getUserDegree());
        txtUniversity.setText(lancer.getUserUniversity());
        txtPassingyear.setText(lancer.getUserPassingyear());


        return vList;

    }
}
