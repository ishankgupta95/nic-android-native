package com.ishank.insuranceprmcalculator.fragments.motor;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.TextView;

import com.ishank.insuranceprmcalculator.R;
import com.ishank.insuranceprmcalculator.activities.ResultActivity;
import com.ishank.insuranceprmcalculator.dialog.Learn;
import com.ishank.insuranceprmcalculator.dialog.SpinnerError;

import java.math.BigDecimal;

public class TwoWheelerODFragment extends Motor {

    public static final String TAG = "TwoWheelerODFragment";

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
    private float mDiscount;
    private int mEngineProtect;
    private int mNCBProtect;
    private int mInvoiceProtect;

    /**
     *
     * @param learnDialog
     */
    Learn learnDialog;


    /**
     * Zones spinner Data
     *
     */
    private String[] zonesArray = new String[]{"Select Zone","Zone A","Zone B"};
    private String[] ccArray = new String[]{"Select CC","Upto 75","From 75 to 150","From 150 to 350","Above 350"};
    private String[] ncbArray = new String[]{"Select NCB value","0","20","25","35","45","50"};
    private String[] yes_no = new String[]{"Select yes/no","No","Yes"};
    private String[] paTopassArray = new String[]{"Select value","1","2"};

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
//        setName();
        autoPopulate();
        setOnLearnClicks();
    }

    private void setOnLearnClicks() {
        learn1.setOnClickListener(learn);
        learn2.setOnClickListener(learn);
        learn3.setOnClickListener(learn);
        learn4.setOnClickListener(learn);
        learn5.setOnClickListener(learn);
        learn6.setOnClickListener(learn);
        learn12.setOnClickListener(learn);
        learn13.setOnClickListener(learn);
        learn15.setOnClickListener(learn);
        learn16.setOnClickListener(learn);
    }

    @Override
    public void getFields() {
        try{
            mAge = Integer.parseInt(age.getText().toString());
            allClear = true;
        } catch (NumberFormatException exception) {
            Log.e(TAG, exception.getMessage());
            age.setError("Age not provided ");
            allClear = false;
            age.requestFocus();
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
//            Log.e(TAG, exception.getMessage());
//            mIDV = 0;
            Log.e(TAG, exception.getMessage());
            idv.setError("IDV not provided ");
            allClear = false;
            idv.requestFocus();
        }

        mNCB = ncb.getSelectedItemPosition();

        mNILDEP = nil_dep.getSelectedItemPosition();

        mCPA = cpa.getSelectedItemPosition();

        mPAtoPass = pa_toPass.getSelectedItemPosition();

        try{
            mPA_Amount = Integer.parseInt(pa_amount.getText().toString());
            if(mPA_Amount < 10000 || mPA_Amount > 200000){
                Toast.makeText(getContext(),"PA Amount should be greater than 10000 and less than 200000",Toast.LENGTH_SHORT).show();
                allClear = false;
                pa_amount.requestFocus();
            }

            else if(mPA_Amount % 10000 != 0){
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
            mDiscount = Integer.parseInt(discount.getText().toString());
            BigDecimal roundfinalPrice = new BigDecimal(mDiscount).setScale(2,BigDecimal.ROUND_HALF_UP);
            mDiscount = Float.valueOf(roundfinalPrice.toString());
        }catch (NumberFormatException exception){
            Log.e(TAG, exception.getMessage());
            mDiscount = 0;
        }

        mEngineProtect = engine_protect.getSelectedItemPosition();

        mNCBProtect = ncb_protect.getSelectedItemPosition();

        mInvoiceProtect = invoice_protect.getSelectedItemPosition();

        if(allClear){
            age.clearFocus();
            pa_amount.clearFocus();
            Bundle b = new Bundle();
            b.putString("RESULT",TAG);
            b.putInt("Age",mAge);
            b.putInt("Zone",mZone);
            b.putInt("CC",mCC);
            b.putInt("IDV",mIDV);
            b.putInt("NCB",mNCB);
            b.putInt("NILDEP",mNILDEP);
//            b.putInt("CPA",mCPA);
//            b.putInt("PAtoPass",mPAtoPass);
//            b.putInt("PA_Amount",mPA_Amount);
            b.putFloat("Discount",mDiscount);
            b.putInt("Engine Protect",mEngineProtect);
            b.putInt("NCB Protect",mNCBProtect);
            b.putInt("Invoice Protect",mInvoiceProtect);
            Intent i = new Intent(getContext(), ResultActivity.class);
            i.putExtras(b);
            startActivity(i);
        }

    }

//    private void setName(){
//        TextView name = (TextView) getView().findViewById(R.id.module_name);
//        name.setText("Two Wheeler OD");
//        name.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                //DO you work here
//            }
//        });
//    }
    private void autoPopulate() {
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
        tv_nilDep.setText("NIL DEP+");
        nil_dep.setAdapter(yes_no_ArrayAdapter);
        cpa.setAdapter(yes_no_ArrayAdapter);
        engine_protect.setAdapter(yes_no_ArrayAdapter);
        ncb_protect.setAdapter(yes_no_ArrayAdapter);
        invoice_protect.setAdapter(yes_no_ArrayAdapter);

        ArrayAdapter<String> paTopassArrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, paTopassArray);
        paTopassArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        pa_toPass.setAdapter(paTopassArrayAdapter);

    }

    public void hideView() {
        ll_towing.setVisibility(View.GONE);
        ll_pubpvt.setVisibility(View.GONE);
        ll_lpg_cng.setVisibility(View.GONE);
        ll_elec_fit.setVisibility(View.GONE);
        ll_built_in_lpg.setVisibility(View.GONE);
        ll_driver.setVisibility(View.GONE);
        ll_paTOpass2.setVisibility(View.GONE);
        ll_ageSpinner.setVisibility(View.GONE);
        ll_pubpvt.setVisibility(View.GONE);
        ll_gvw.setVisibility(View.GONE);
        ll_imt.setVisibility(View.GONE);
        ll_dr_cls.setVisibility(View.GONE);
        ll_fnpp.setVisibility(View.GONE);
        ll_passenger.setVisibility(View.GONE);
        ll_trailer_idv.setVisibility(View.GONE);
        ll_number_of_trailer.setVisibility(View.GONE);
        ll_numberOfPassengers.setVisibility(View.GONE);
        ll_cpa.setVisibility(View.GONE);
        ll_paTOpass.setVisibility(View.GONE);
        ll_paToAmount.setVisibility(View.GONE);
    }

    View.OnClickListener learn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view == learn1){
                learnDialog = new Learn(getContext(),true,R.string.twowheelerLearn1);
                learnDialog.show();
            }

            else if (view == learn2){
                learnDialog = new Learn(getContext(),true,R.string.twowheelerLearn2);
                learnDialog.show();
            }

            else if (view == learn3){
                learnDialog = new Learn(getContext(),true,R.string.twowheelerLearn3);
                learnDialog.show();
            }

            else if (view == learn4){
                learnDialog = new Learn(getContext(),true,R.string.twowheelerLearn4);
                learnDialog.show();
            }

            else if (view == learn5){
                learnDialog = new Learn(getContext(),true,R.string.twowheelerLearn5);
                learnDialog.show();
            }

            else if (view == learn6){
                learnDialog = new Learn(getContext(),true,R.string.twowheelerLearn6);
                learnDialog.show();
            }

            else if (view == learn12){
                learnDialog = new Learn(getContext(),true,R.string.twowheelerLearn7);
                learnDialog.show();
            }

            else if (view == learn13){
                learnDialog = new Learn(getContext(),true,R.string.twowheelerLearn8);
                learnDialog.show();
            }

            else if (view == learn15){
                learnDialog = new Learn(getContext(),true,R.string.twowheelerLearn9);
                learnDialog.show();
            }

            else if (view == learn16){
                learnDialog = new Learn(getContext(),true,R.string.twowheelerLearn10);
                learnDialog.show();
            }
        }
    };

}