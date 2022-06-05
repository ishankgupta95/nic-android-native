package com.devs270.oiccalculator.fragments.motor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devs270.oiccalculator.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoWheelerSettings extends Fragment {


    public TwoWheelerSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two_wheeler_settings, container, false);
    }

}
