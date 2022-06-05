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
import java.text.DecimalFormat;

public class PrivateCarFragment extends Motor {

    public static final String TAG = "PrivateCarFragment";

    //private String[] ageArray = new String[]{"New Veh.","1 - 2","2 - 3","3 - 5"};
    private String[] zonesArray = new String[]{"Select Zone","A","B"};
    private String[] ccArray = new String[]{"Select CC","Upto 1000","1000 to 1500","Above 1500"};
    private String[] ncbArray = new String[]{"Select NCB value","0","20","25","35","45","50"};
    private String[] yes_no = new String[]{"Select yes/no","No","Yes"};

    /**
     * Variables
     */
    private int mAge;
    private int mIDV;
    private int mZone;
    private int mCC;
    private int mNILDEP;
    private int mCPA;
    private int mNCB;
    private int mPAtoPass;
    private int mPA_Amount;
    private int mLPG_CNG;
    private int mBuiltInLPG;
    private int mTape;
    private int mTowing;
    private int mDriver;
    private float mUnderwritingDiscount;
    private float mNDPDiscount;
    private float mOtherAddOnDiscount;

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

        /*ArrayAdapter<String> ageArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, ageArray);
        ageArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        age2.setAdapter(ageArrayAdapter);*/

        ArrayAdapter<String> zoneArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, zonesArray);
        zoneArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        zones.setAdapter(zoneArrayAdapter);

        ArrayAdapter<String> ccArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, ccArray);
        ccArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        cc.setAdapter(ccArrayAdapter);

        ArrayAdapter<String> ncbArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, ncbArray);
        ncbArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        ncb.setAdapter(ncbArrayAdapter);

        ArrayAdapter<String> yes_no_ArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, yes_no);
        yes_no_ArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        nil_dep.setAdapter(yes_no_ArrayAdapter);
        cpa.setAdapter(yes_no_ArrayAdapter);
        driver.setAdapter(yes_no_ArrayAdapter);
        built_in_LPG.setAdapter(yes_no_ArrayAdapter);
        rti.setAdapter(yes_no_ArrayAdapter);
    }

    @Override
    public void getFields() {

        //mAge = age2.getSelectedItemPosition();

        try{
            mAge = Integer.parseInt(age.getText().toString());
            allClear = true;
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            age.setError("Age not provided ");
            age.requestFocus();
            allClear = false;
            mAge = 0;
        }

        mZone = zones.getSelectedItemPosition();
        if(mZone == 0) {
            SpinnerError.setSpinnerError(zones, "Field Can't be unselected");
            allClear = false;
        }

        mCC = cc.getSelectedItemPosition();
        if(mCC == 0) {
            SpinnerError.setSpinnerError(cc, "Field Can't be unselected");
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
            mTape = Integer.parseInt(tape.getText().toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mTape = 0;
        }


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

        mNILDEP = nil_dep.getSelectedItemPosition();

        mNCB = ncb.getSelectedItemPosition();

        mDriver = driver.getSelectedItemPosition();

        mCPA = cpa.getSelectedItemPosition();

        try{
            mPAtoPass = Integer.parseInt(pa_toPass2.getText().toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mPAtoPass = 0;
        }

        try{
            mPA_Amount = Integer.parseInt(pa_amount.getText().toString());
            if(mPA_Amount<10000 || mPA_Amount>200000){
                Toast.makeText(getContext(),"PA Amount should be greater than 10000 and less than 200000",Toast.LENGTH_SHORT).show();
                allClear = false;
                pa_amount.requestFocus();
            }

             else if(mPA_Amount%10000!=0){
                Toast.makeText(getContext(),"PA Amount should be in multiples of 10000",Toast.LENGTH_SHORT).show();
                allClear = false;
                pa_amount.requestFocus();
            }
            else allClear = true;
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mPA_Amount = 0;
        }

        try{
            mUnderwritingDiscount = Integer.parseInt(underwriting_discount.getText().toString());
            if(mUnderwritingDiscount != to2decimal(mUnderwritingDiscount) ){
                Toast.makeText(getContext(),"Underwriting Discount can only be entered upto two decimal places",Toast.LENGTH_SHORT).show();
                allClear = false;
                underwriting_discount.requestFocus();
            }
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mUnderwritingDiscount = 0;
        }

        try{
            mNDPDiscount = Integer.parseInt(ndp_discount.getText().toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mNDPDiscount = 0;
        }

        try{
            mOtherAddOnDiscount = Integer.parseInt(other_add_on_discount.getText().toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mOtherAddOnDiscount = 0;
        }




        if(allClear){
            age.clearFocus();
            pa_amount.clearFocus();
            Bundle b = new Bundle();
            b.putString("RESULT",TAG);
            b.putInt("Age",mAge);
            b.putInt("Zone",mZone);
            b.putInt("CC",mCC);
            b.putInt("IDV",mIDV);
            b.putInt("LPG_CNG",mLPG_CNG);
            b.putInt("BuiltInLPG",mBuiltInLPG);
            b.putInt("Tape",mTape);
            b.putInt("Towing",mTowing);
            b.putInt("NCB",mNCB);
            b.putInt("NILDEP",mNILDEP);
            b.putInt("CPA",mCPA);
            b.putInt("Driver",mDriver);
            b.putInt("PAtoPass",mPAtoPass);
            b.putInt("PA_Amount",mPA_Amount);
            b.putFloat("Underwriting_Discount",mUnderwritingDiscount);
            b.putFloat("NDP_Discount",mNDPDiscount);
            b.putFloat("Other_Add_On_Discount",mOtherAddOnDiscount);
            Intent i = new Intent(getContext(), ResultActivity.class);
            i.putExtras(b);
            startActivity(i);
        }
    }

    @Override
    public void hideView() {
        ll_ageSpinner.setVisibility(View.GONE);
        ll_paTOpass.setVisibility(View.GONE);
        ll_pubpvt.setVisibility(View.GONE);
        ll_gvw.setVisibility(View.GONE);
        ll_imt.setVisibility(View.GONE);
        ll_dr_cls.setVisibility(View.GONE);
        ll_fnpp.setVisibility(View.GONE);
        ll_discount.setVisibility(View.GONE);

    }

    private float to2decimal(float x){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float twoDigitsF = Float.valueOf(decimalFormat.format(x));
        return twoDigitsF;
    }



}
