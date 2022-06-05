package com.devs270.oiccalculator.fragments.motor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.devs270.oiccalculator.R;
import com.devs270.oiccalculator.activities.ResultActivity;
import com.devs270.oiccalculator.dialog.SpinnerError;

import java.math.BigDecimal;

public class TruckFragment extends Motor {

    public static final String TAG = "TruckFragment";

    private String[] zonesArray = new String[]{"Select Zone","A","B","C"};
    private String[] pvtpubArray = new String[]{"Select PVT/PUBL","Public", "Private"};
    private String[] yes_no = new String[]{"Select yes/no","No","Yes"};
    private String[] ncbArray = new String[]{"Select NCB value","0","20","25","35","45","50"};

    /**
     * Variables
     */
    private int mAge;
    private int mIDV;
    private int mZone;
    private int mNILDEP;
    private int mCPA;
    private int mNCB;
    private int mLPG_CNG;
    private int mBuiltInLPG;
    private int mTowing;
    private float mDiscount;
    private int mPVTPUB;
    private int mGVW;
    private int mIMT;
    private int mDRCLS;
    private int mNFPP;

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

        ArrayAdapter<String> pvtpubArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, pvtpubArray);
        pvtpubArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        private_public.setAdapter(pvtpubArrayAdapter);

        ArrayAdapter<String> ncbArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, ncbArray);
        ncbArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        ncb.setAdapter(ncbArrayAdapter);

        ArrayAdapter<String> yes_no_ArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, yes_no);
        yes_no_ArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        cpa.setAdapter(yes_no_ArrayAdapter);
        built_in_LPG.setAdapter(yes_no_ArrayAdapter);
        imt.setAdapter(yes_no_ArrayAdapter);
        nil_dep.setAdapter(yes_no_ArrayAdapter);
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

        mPVTPUB = private_public.getSelectedItemPosition();
        if(mPVTPUB == 0) {
            SpinnerError.setSpinnerError(private_public, "Field Can't be unselected");
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

        try{
            mDiscount = Integer.parseInt(discount.getText().toString());
            BigDecimal roundfinalPrice = new BigDecimal(mDiscount).setScale(2,BigDecimal.ROUND_HALF_UP);
            mDiscount = Float.valueOf(roundfinalPrice.toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mDiscount = 0;
        }

        try{
            mGVW = Integer.parseInt(gvw.getText().toString());
            allClear = true;
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            gvw.setError("GVW not provided ");
            gvw.requestFocus();
            allClear = false;
        }

        mIMT = imt.getSelectedItemPosition();

        try{
            mDRCLS = Integer.parseInt(dr_cls.getText().toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mDRCLS = 0;
        }

        try{
            mNFPP = Integer.parseInt(nfpp.getText().toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mNFPP = 0;
        }

        mNILDEP = nil_dep.getSelectedItemPosition();

        mNCB = ncb.getSelectedItemPosition();

        mCPA = cpa.getSelectedItemPosition();


        /*try{
            mTowing = Integer.parseInt(towing_charges.getText().toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mTowing = 0;
        }*/

        try{
            mTowing = Integer.parseInt(towing_charges.getText().toString());
            if(mTowing>1500){
                Toast.makeText(getContext(),"Towing Charges should be less than 1500",Toast.LENGTH_SHORT).show();
                allClear = false;
                towing_charges.requestFocus();
            }
            else allClear = true;
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mTowing = 0;
        }

        if(allClear){
            Bundle b = new Bundle();
            b.putString("RESULT",TAG);
            b.putInt("Age",mAge);
            b.putInt("Zone",mZone);
            b.putInt("IDV",mIDV);
            b.putInt("LPG_CNG",mLPG_CNG);
            b.putInt("BuiltInLPG",mBuiltInLPG);
            b.putInt("GVW",mGVW);
            b.putInt("IMT",mIMT);
            b.putInt("DR_CLS",mDRCLS);
            b.putInt("NFPP",mNFPP);
            b.putInt("PVTPUB",mPVTPUB);
            b.putInt("Towing",mTowing);
            b.putInt("NCB",mNCB);
            b.putInt("NILDEP",mNILDEP);
            b.putInt("CPA",mCPA);
            b.putFloat("Discount",mDiscount);
            Intent i = new Intent(getContext(), ResultActivity.class);
            i.putExtras(b);
            startActivity(i);
        }
    }

    @Override
    public void hideView() {
        ll_ageSpinner.setVisibility(View.GONE);
        ll_cc.setVisibility(View.GONE);
        ll_paTOpass2.setVisibility(View.GONE);
        ll_paToAmount.setVisibility(View.GONE);
        ll_paTOpass.setVisibility(View.GONE);
        ll_driver.setVisibility(View.GONE);
        ll_elec_fit.setVisibility(View.GONE);
    }
}
