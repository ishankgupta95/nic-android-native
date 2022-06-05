package com.devs270.oiccalculator.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.devs270.oiccalculator.R;
import com.devs270.oiccalculator.fragments.motor.PrivateCarFragment;
import com.devs270.oiccalculator.fragments.motor.TruckFragment;
import com.devs270.oiccalculator.fragments.motor.TwoWheelerFragment;

public class ChooseInsuranceType extends FragmentActivity {

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_insurance_type);

        mTabHost = findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        /*mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator(getString(R.string.twowheel)),
                TwoWheelerFragment.class, null);*/

        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator(getString(R.string.privateCar)),
                PrivateCarFragment.class, null);

        /*mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator(getString(R.string.commercial_vehicle)),
                TruckFragment.class, null);*/

        /*mTabHost.addTab(
                mTabHost.newTabSpec("tab4").setIndicator(getString(R.string.rickshaw)),
                RickshawFragment.class,null);*/

    }
}
