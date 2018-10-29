package com.pasistence.knockwork.Freelancer.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.pasistence.knockwork.R;

import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FreelancerWorkExperienceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FreelancerWorkExperienceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FreelancerWorkExperienceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public FButton btnSubmit;
    public Spinner spnYears,spnMonths;
    public EditText edtCompanyName,edtProfile;
    public TextView txtWorkfrom,txtWorkto;

    private OnFragmentInteractionListener mListener;

    public FreelancerWorkExperienceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FreelancerWorkExperienceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FreelancerWorkExperienceFragment newInstance(String param1, String param2) {
        FreelancerWorkExperienceFragment fragment = new FreelancerWorkExperienceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_freelancer_work_experience, container, false);

        mInit(view);
        return view;
    }

    private void mInit(View view) {
        btnSubmit =(FButton)view.findViewById(R.id.freelancer_profile_btnSubmit) ;
        edtCompanyName = (EditText)view.findViewById(R.id.freelancer_profile_editcompanyName);
        edtProfile = (EditText)view.findViewById(R.id.freelancer_profile_editprofile);
        spnMonths =(Spinner)view.findViewById(R.id.freelancer_profile_spnmonths);
        spnYears =(Spinner)view.findViewById(R.id.freelancer_profile_spnyears);
        txtWorkfrom = (TextView)view.findViewById(R.id.freelancer_profile_workfrom);
        txtWorkto = (TextView)view.findViewById(R.id.freelancer_profile_workTo);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
