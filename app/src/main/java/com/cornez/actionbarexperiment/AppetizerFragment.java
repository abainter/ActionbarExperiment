package com.cornez.actionbarexperiment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * Created by Ver on 11/29/2016.
 */

public class AppetizerFragment extends Fragment {

    RadioGroup appetizerRG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_appetizer, container, false);

        appetizerRG = (RadioGroup) rootView.findViewById(R.id.appetizerGroup);
        appetizerRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int price=0;
                switch(checkedId){
                    case R.id.salad:
                        price = 3;
                        break;
                    case R.id.soup:
                        price = 4;
                        break;
                    case R.id.fruit:
                        price = 2;
                        break;
                }
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                int checkedIndex = group.indexOfChild(checkedRadioButton);
                SavePreferences("APPETIZER", checkedIndex);
                SavePreferences("APPETIZER_PRICE", price);
            }
        });
        loadPreferences();

        return rootView;
    }

    public void loadPreferences(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE);
        if(sharedPreferences != null){
            int index = sharedPreferences.getInt("APPETIZER", 0);
            ((RadioButton) appetizerRG.getChildAt(index)).setChecked(true);
        }
    }
    private void SavePreferences(String key, int value){
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

}