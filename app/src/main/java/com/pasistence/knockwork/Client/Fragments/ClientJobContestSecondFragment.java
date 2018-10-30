package com.pasistence.knockwork.Client.Fragments;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pasistence.knockwork.Client.Activities.ClientJobContestActivity;
import com.pasistence.knockwork.Client.Activities.ClientJobRequest;
import com.pasistence.knockwork.R;


public class ClientJobContestSecondFragment extends Fragment {

    public EditText editContestPrice;
    public ImageView contestimg;
    public TextView txtTerms,txtterms1,txtterms2,txtterms3;
    public CheckBox chk;
    public Button btn;


    public ClientJobContestSecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_client_job_contest_second, container, false);
        mInit(view);
       // return inflater.inflate(R.layout.fragment_client_job_contest_second, container, false);
        return view;
    }

    private void mInit(View view) {

        contestimg       = (ImageView)view.findViewById(R.id.contest2_img);
        editContestPrice = (EditText)view.findViewById(R.id.contest2_edit_price);
        txtTerms         = (TextView)view.findViewById(R.id.contest2_txt);
        txtterms1        = (TextView)view.findViewById(R.id.contest2_terms1);
        txtterms2        = (TextView)view.findViewById(R.id.contest2_terms2);
        txtterms3        = (TextView)view.findViewById(R.id.contest2_terms3);
        chk              = (CheckBox)view.findViewById(R.id.contest2_chk);
        btn              = (Button)view.findViewById(R.id.contest2_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Poast Contest", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
