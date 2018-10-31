package com.pasistence.knockwork.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.pasistence.knockwork.Model.ApiResponse.ApiEducationResponse;
import com.pasistence.knockwork.Model.ApiResponse.ApiExperienceResponse;
import com.pasistence.knockwork.R;

import java.util.ArrayList;

public class FreeLancerExperienceAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<ApiExperienceResponse.LancerExperience> lancerExperiences = new ArrayList<>();
    TextView txtdesignation,txtcompanyname,txtstartdate,txtenddate;
    Button btnEdit,btnDelete;
    LayoutInflater inflter;

    public FreeLancerExperienceAdapter(Context mContext, ArrayList<ApiExperienceResponse.LancerExperience> lancerExperiences) {
        this.mContext = mContext;
        this.lancerExperiences = lancerExperiences;
    }

    @Override
    public int getCount() {
        return lancerExperiences.size();
    }

    @Override
    public Object getItem(int position) {
        return lancerExperiences.get(position);
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
            vList = inflater.inflate(R.layout.custom_freelancer_workexperine_layout,null);
        }else {
            vList = (View)convertView;
        }

        //    convertView = inflter.inflate(R.layout.custom_freelancer_education_layout, null);//set layout for displaying item

        txtdesignation = (TextView)vList.findViewById(R.id.txt_designation);
        txtcompanyname = (TextView)vList.findViewById(R.id.txt_compnayname);
        txtstartdate = (TextView)vList.findViewById(R.id.txt_startdate);
        txtenddate = (TextView)vList.findViewById(R.id.txt_enddate);
        btnEdit = (Button) vList.findViewById(R.id.btn_edit);
        btnDelete = (Button)vList.findViewById(R.id.btn_delete);

        ApiExperienceResponse.LancerExperience lancer = lancerExperiences.get(position);

        txtdesignation.setText(lancer.getJobDescription()+"");
        txtcompanyname.setText(lancer.getCompanyName()+"");
        txtstartdate.setText(lancer.getStartDate()+"");
        txtenddate.setText(lancer.getEndDate()+"");




        return vList;

    }
}
