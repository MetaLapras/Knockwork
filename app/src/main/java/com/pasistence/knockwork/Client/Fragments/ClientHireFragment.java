package com.pasistence.knockwork.Client.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pasistence.knockwork.Client.Activities.ClientJobRequest;
import com.pasistence.knockwork.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import de.hdodenhof.circleimageview.CircleImageView;
import info.hoang8f.widget.FButton;

public class ClientHireFragment extends Fragment {


    public ClientHireFragment() {
        // Required empty public constructor
    }
    public TextView txtName,txtState,txtPrice,txtFeedback,txtDescription,txtTimeline,txtAmount,txtAmountPrice;
    FButton btnSubmitRequest;
    MaterialEditText edtstarttime,edtendtime;
    Context mContext;
    CircleImageView circleImageView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_client_job_contest, container, false);
        mInit(view);

        // Inflate the layout for this fragment
        return view;
    }

    private void mInit(View view) {

        mContext = getActivity();

        circleImageView = (CircleImageView)view.findViewById(R.id.circularImage_profile);
        txtName=(TextView)view.findViewById(R.id.txt_lancer_name);
        txtState=(TextView)view.findViewById(R.id.txt_lancer_state);
        txtPrice=(TextView)view.findViewById(R.id.txt_lancer_earing);
        txtFeedback=(TextView)view.findViewById(R.id.txt_lancer_per);
        txtDescription=(TextView)view.findViewById(R.id.txt_description_lancer);
        txtAmount=(TextView)view.findViewById(R.id.txt_amount);
        txtAmountPrice=(TextView)view.findViewById(R.id.txt_hiredamount);
        txtTimeline=(TextView)view.findViewById(R.id.txt_timeline);


        edtstarttime = (MaterialEditText)view.findViewById(R.id.edt_start_time);
        edtendtime = (MaterialEditText)view.findViewById(R.id.edt_end_time);

        btnSubmitRequest = (FButton)view.findViewById(R.id.btn_submit_request);

    }
}
