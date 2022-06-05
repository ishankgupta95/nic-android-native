package com.ishank.insuranceprmcalculator.fragments.motor;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.ishank.insuranceprmcalculator.R;
import com.ishank.insuranceprmcalculator.activities.ResultActivity;
import com.ishank.insuranceprmcalculator.dialog.SpinnerError;

import java.math.BigDecimal;

public class Rickshaw7Fragment extends Motor {

    public static final String TAG = "RickshawFragment7";

    private String[] zonesArray = new String[]{"Select Zone","A","B","C"};
    private String[] ncbArray = new String[]{"Select NCB value","0","20","25","35","45","50"};
    private String[] yes_no = new String[]{"Select yes/no","No","Yes"};
    private String[] passengerArray = new String[]{"Select Passenger","7","8","9","10","11","12","13","14","15","16","17"};

    /**
     * Variables
     */
    private int mAge;
    private int mIDV;
    private int mZone;
    private int mNILDEP;
    private int mCPA;
    private int mIMT;
    private int mNCB;
    private int mLPG_CNG;
    private int mBuiltInLPG;
    private int mDriver;
    private float mDiscount;
    private int mPass;

    /**
     *
     *  If all clear
     */
    private boolean allClear = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        autoPopulate();
    }

    private void autoPopulate() {

        ArrayAdapter<String> zoneArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, zonesArray);
        zoneArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        zones.setAdapter(zoneArrayAdapter);


        ArrayAdapter<String> ncbArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, ncbArray);
        ncbArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        ncb.setAdapter(ncbArrayAdapter);

        ArrayAdapter<String> passengerArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, passengerArray);
        passengerArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        passenger.setAdapter(passengerArrayAdapter);

        ArrayAdapter<String> yes_no_ArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, yes_no);
        yes_no_ArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        nil_dep.setAdapter(yes_no_ArrayAdapter);
        cpa.setAdapter(yes_no_ArrayAdapter);
        driver.setAdapter(yes_no_ArrayAdapter);
        built_in_LPG.setAdapter(yes_no_ArrayAdapter);
        imt.setAdapter(yes_no_ArrayAdapter);

    }

    @Override
    public void getFields() {



        try{
            mAge = Integer.parseInt(age.getText().toString());
            allClear = true;
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            age.setError("Age not provided ");
            age.requestFocus();
            allClear = false;
        }

        mZone = zones.getSelectedItemPosition();
        if(mZone == 0) {
            SpinnerError.setSpinnerError(zones, "Field Can't be unselected");
            allClear = false;
        }


        mPass = passenger.getSelectedItemPosition();
        if(mPass == 0) {
            SpinnerError.setSpinnerError(passenger, "Field Can't be unselected");
            allClear = false;
        }


        try{
            mIDV = Integer.parseInt(idv.getText().toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mIDV = 0;
        }

        try{
            mLPG_CNG = Integer.parseInt(lpg_cng.getText().toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mLPG_CNG = 0;
        }

        mBuiltInLPG = built_in_LPG.getSelectedItemPosition();

        mNILDEP = nil_dep.getSelectedItemPosition();

        mNCB = ncb.getSelectedItemPosition();

        mIMT = imt.getSelectedItemPosition();

        mDriver = driver.getSelectedItemPosition();

        mCPA = cpa.getSelectedItemPosition();

        try{
            mDiscount = Integer.parseInt(discount.getText().toString());
            BigDecimal roundfinalPrice = new BigDecimal(mDiscount).setScale(2,BigDecimal.ROUND_HALF_UP);
            mDiscount = Float.valueOf(roundfinalPrice.toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mDiscount = 0;
        }

        if(allClear) {
            Bundle b = new Bundle();
            b.putString("RESULT", TAG);
            b.putInt("Age", mAge);
            b.putInt("Zone", mZone);
            b.putInt("IDV", mIDV);
            b.putInt("LPG_CNG", mLPG_CNG);
            b.putInt("BuiltInLPG", mBuiltInLPG);
            b.putInt("IMT",mIMT);
            b.putInt("Pass",mPass);
            b.putInt("NCB", mNCB);
            b.putInt("NILDEP", mNILDEP);
            b.putInt("CPA", mCPA);
            b.putInt("Driver", mDriver);
            b.putFloat("Discount", mDiscount);
            Intent i = new Intent(getContext(), ResultActivity.class);
            i.putExtras(b);
            startActivity(i);
        }
    }

    @Override
    public void hideView() {
        ll_ageSpinner.setVisibility(View.GONE);
        ll_pubpvt.setVisibility(View.GONE);
        ll_cc.setVisibility(View.GONE);
        ll_engine_protect.setVisibility(View.GONE);
        ll_ncb_protect.setVisibility(View.GONE);
        ll_invoice_protect.setVisibility(View.GONE);
        ll_paTOpass.setVisibility(View.GONE);
        ll_paTOpass2.setVisibility(View.GONE);
        ll_gvw.setVisibility(View.GONE);
        ll_elec_fit.setVisibility(View.GONE);
        ll_towing.setVisibility(View.GONE);
        ll_fnpp.setVisibility(View.GONE);
        ll_dr_cls.setVisibility(View.GONE);
        ll_paToAmount.setVisibility(View.GONE);
        ll_trailer_idv.setVisibility(View.GONE);
        ll_number_of_trailer.setVisibility(View.GONE);
        ll_numberOfPassengers.setVisibility(View.GONE);
        ll_nildep.setVisibility(View.GONE);
        ll_discount.setVisibility(View.GONE);
    }

}
