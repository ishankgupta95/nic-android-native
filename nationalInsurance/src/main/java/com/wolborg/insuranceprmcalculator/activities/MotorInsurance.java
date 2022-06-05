package com.wolborg.insuranceprmcalculator.activities;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.wolborg.insuranceprmcalculator.R;
import com.wolborg.insuranceprmcalculator.fragments.motor.*;
import com.wolborg.insuranceprmcalculator.utils.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class MotorInsurance extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_insurance_type2);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TwoWheelerFragment(), getResources().getString(R.string.twowheel));
        adapter.addFrag(new PrivateCarFragment(), getResources().getString(R.string.privateCar));
        adapter.addFrag(new TruckFragment(), getResources().getString(R.string.commercial_vehicle));
        adapter.addFrag(new TaxiFragment(), getResources().getString(R.string.four_wheeler_upto_6_pass));
        adapter.addFrag(new RickshawFragment(),getResources().getString(R.string.rickshaw));
        adapter.addFrag(new Rickshaw7Fragment(),getResources().getString(R.string.rickshaw7));
        adapter.addFrag(new ThreeWheelPickupFragment(),getResources().getString(R.string.ThreeWheelPickup));
        adapter.addFrag(new FourWhPassMoreSixFragment(),getResources().getString(R.string.FourWhPassMoreSix));

        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}