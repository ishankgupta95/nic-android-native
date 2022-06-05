package com.ishank.insuranceprmcalculator.activities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ishank.insuranceprmcalculator.R;
import com.ishank.insuranceprmcalculator.fragments.motor.*;
import com.ishank.insuranceprmcalculator.utils.CustomAdapter;

public class MotorInsurance1 extends AppCompatActivity implements CustomAdapter.OnItemClicked {

    private final static String TAG = "MotorInsurance1";
    private RecyclerView recyclerView;
    private CustomAdapter mAdapter;
    Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_insurance1);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        fragments = new Fragment[]{
                new TwoWheelerFragment(),
//                new TwoWheelerODFragment(),
                new PrivateCarFragment(),
//                new PrivateCarODFragment(),
                new TruckFragment(),
                new ThreeWheelPickupFragment(),
                new RickshawFragment(),
                new Rickshaw7Fragment(),
                new TaxiFragment(),
                new FourWhPassMoreSixFragment(),
                new LTBTwoWheelerFragment(),
                new LTBPvtCarFragment()
        };

        String[] strings = new String[]{rc(R.string.twowheel), rc(R.string.privateCar), rc(R.string.commercial_vehicle),
                rc(R.string.ThreeWheelPickup), rc(R.string.rickshaw), rc(R.string.rickshaw7),
                rc(R.string.four_wheeler_upto_6_pass), rc(R.string.FourWhPassMoreSix), rc(R.string.LTBTwoWheeler), rc(R.string.LTBPvtCar)};


        mAdapter = new CustomAdapter(strings);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnClick(this);
    }

    String rc(int i){
        return getApplicationContext().getResources().getString(i);
    }

    @Override
    public void onItemClick(int position) {
        openFragment(fragments[position]);
    }

    private void openFragment(final Fragment fragment)   {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
