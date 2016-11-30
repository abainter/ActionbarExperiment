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

public class DessertFragment extends Fragment {

    RadioGroup dessertRG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dessert, container, false);
        dessertRG = (RadioGroup) rootView.findViewById(R.id.dessertGroup);
        dessertRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int price = 0;
                switch(checkedId){
                    case R.id.cake:
                        price = 5;
                        break;
                    case R.id.pie:
                        price = 6;
                        break;
                    case R.id.icecream:
                        price = 4;
                        break;
                }
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                int checkedIndex = group.indexOfChild(checkedRadioButton);
                SavePreferences("DESSERT", checkedIndex);
                SavePreferences("DESSERT_PRICE", price);
            }
        });
        loadPreferences();

        return rootView;
    }
    public void loadPreferences(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE);
        if(sharedPreferences != null){
            int index = sharedPreferences.getInt("DESSERT", 0);
            ((RadioButton) dessertRG.getChildAt(index)).setChecked(true);
        }
    }
    private void SavePreferences(String key, int value){
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
}