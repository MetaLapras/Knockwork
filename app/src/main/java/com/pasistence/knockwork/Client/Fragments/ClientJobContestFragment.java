package com.pasistence.knockwork.Client.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pasistence.knockwork.Freelancer.Fragments.FreelancerWorkExperienceFragment;
import com.pasistence.knockwork.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientJobContestFragment extends Fragment {
    public TextView txtContestTitle,txtContestDescription,txtContestOptional;
    public EditText editContestTitle,editContestDesription;
    public Button btnContinue;
    public ImageView optionalImag;


    public ClientJobContestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_client_job_contest, container, false);
        mInit(view);

      /*  return inflater.inflate(R.layout.fragment_client_job_contest, container, false);*/
        return view;
    }

    private void mInit(View view) {
        txtContestTitle        = (TextView)view.findViewById(R.id.contest_title);
        txtContestDescription  =(TextView)view.findViewById(R.id.contest_description);
        txtContestOptional     =(TextView)view.findViewById(R.id.contest_optional);

        editContestTitle       = (EditText)view.findViewById(R.id.edt_contest_title);
        editContestDesription  =(EditText)view.findViewById(R.id.edit_contest_description);

        btnContinue            = (Button)view.findViewById(R.id.contest_btnContinue);

        optionalImag           =  (ImageView)view.findViewById(R.id.contest_img);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contest_frame,new ClientJobContestSecondFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }


}
