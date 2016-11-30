package com.cornez.actionbarexperiment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ver on 11/29/2016.
 */

public class EntreeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entree, container, false);
    }
}