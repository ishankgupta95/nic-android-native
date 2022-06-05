package com.wolborg.insuranceprmcalculator.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.wolborg.insuranceprmcalculator.R;
import com.wolborg.insuranceprmcalculator.fragments.motor.PrivateCarFragment;
import com.wolborg.insuranceprmcalculator.fragments.motor.TruckFragment;
import com.wolborg.insuranceprmcalculator.fragments.motor.TwoWheelerFragment;
import com.wolborg.insuranceprmcalculator.utils.CalculatePrivateCar;
import com.wolborg.insuranceprmcalculator.utils.CalculateTruck;
import com.wolborg.insuranceprmcalculator.utils.CalculateTwoWheeler;
import com.wolborg.insuranceprmcalculator.utils.TakeScreentShot;

public class ResultActivity extends AppCompatActivity {

    private final String TAG = "ResultActivity";
    Activity thisActivity = this;
    Bundle b ;

    TableLayout tb;
    Button cancel;
    Button share;

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
    private int mLPG_CNG;
    private int mBuiltInLPG;
    private int mTape;
    private int mTowing;
    private int mDriver;
    private int mPVTPUB;
    private int mGVW;
    private int mIMT;
    private int mDRCLS;
    private int mNFPP;
    private float mBuilt_In;

    /**
     * Ads Object
     */
    AdView adView;
    InterstitialAd adView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tb = (TableLayout)  findViewById(R.id.tableLayout);
        share = (Button) findViewById(R.id.button_share);
        cancel = (Button)findViewById(R.id.button_cancelDailog);
        cancel.setOnClickListener(clicks);
        share.setOnClickListener(clicks);

        b = getIntent().getExtras();
        String resultType = b.getString("RESULT");

        switch (resultType){
            case TwoWheelerFragment.TAG :
                getTwoWheelerResultandDisplay();
                break;

            case PrivateCarFragment.TAG:
                getPrivateCarResultandDisplay();
                break;

            case TruckFragment.TAG :
                getTruckResultandDisplay();
                break;

            default:
                Toast.makeText(getApplicationContext(),"Some Technical Difficulty Occurred ",Toast.LENGTH_SHORT).show();
        }

        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").addTestDevice("57BB49040935CE0320C9C01E93003C87").build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                super.onAdOpened();
                AdRequest adRequest = new AdRequest.Builder()
                        .setRequestAgent("android_studio:ad_template").addTestDevice("57BB49040935CE0320C9C01E93003C87").build();
                adView.loadAd(adRequest);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Log.e(TAG,"onAdFailedToLoad");
            }
        });

        adView2 = new InterstitialAd(this);
        adView2.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));

        AdRequest adrequest2 = new AdRequest.Builder()
                .addTestDevice("57BB49040935CE0320C9C01E93003C87").build();
        adView2.loadAd(adrequest2);
        adView2.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                super.onAdOpened();
                AdRequest adrequest2 = new AdRequest.Builder()
                        .addTestDevice("57BB49040935CE0320C9C01E93003C87").build();
                adView2.loadAd(adrequest2);
            }
        });
    }

    private void getTwoWheelerResultandDisplay() {
        mAge = b.getInt("Age");
        mZone = b.getInt("Zone");
        mCC = b.getInt("CC");
        mIDV = b.getInt("IDV");
        mNCB = b.getInt("NCB");
        mNILDEP = b.getInt("NILDEP");
        mCPA = b.getInt("CPA");
        mPAtoPass = b.getInt("PAtoPass");
        mPA_Amount = b.getInt("PA_Amount");
        mDiscount = b.getFloat("Discount");
        CalculateTwoWheeler calculate = new CalculateTwoWheeler(mAge,mZone,mCC,mIDV,mNCB,mNILDEP,mCPA,mPAtoPass,mPA_Amount,mDiscount);
        calculate.calculateInsurance();

        tb.addView(addRow("Basic",Float.toString(calculate.getmBasic())));
        tb.addView(addRow("NCB",Float.toString(calculate.getmNCB())));
        tb.addView(addRow("OD",Float.toString(calculate.getmOD())));
        tb.addView(addRow("Discount",Float.toString(calculate.getmDiscount())));
        tb.addView(addRow("NIL DEP",Float.toString(calculate.getmNIL_DEP())));
        tb.addView(addRow("Net OD",Float.toString(calculate.getmNET_OD())));
        tb.addView(addRow("TP",Integer.toString(calculate.getmTP())));
        tb.addView(addRow("CPA-50",Integer.toString(calculate.getmCPA_50())));
        tb.addView(addRow("PA Pass",Integer.toString(calculate.getmPassPa())));
        tb.addView(addRow("TP Total",Integer.toString(calculate.getmTP_Total())));
        tb.addView(addRow("Total",Integer.toString(calculate.getmTotal())));
        tb.addView(addRow("GST",Integer.toString(calculate.getmGST())));
        tb.addView(addRow("Net Payable",Integer.toString(calculate.getmNet_Payable())));
    }

    private void getPrivateCarResultandDisplay(){

        mAge = b.getInt("Age");
        mZone = b.getInt("Zone");
        mCC = b.getInt("CC");
        mIDV = b.getInt("IDV");
        mLPG_CNG = b.getInt("LPG_CNG");
        mBuiltInLPG = b.getInt("BuiltInLPG");
        mTape = b.getInt("Tape");
        mTowing = b.getInt("Towing");
        mNCB = b.getInt("NCB");
        mNILDEP = b.getInt("NILDEP");
        mCPA = b.getInt("CPA");
        mDriver = b.getInt("Driver");
        mPAtoPass = b.getInt("PAtoPass");
        mPA_Amount = b.getInt("PA_Amount");
        mDiscount = b.getFloat("Discount");
        CalculatePrivateCar calculate = new CalculatePrivateCar(mAge, mZone, mCC, mIDV, mLPG_CNG, mBuiltInLPG, mTape, mTowing, mNCB,
                mNILDEP, mCPA, mDriver, mPAtoPass, mPA_Amount, mDiscount);
        calculate.calculateInsurance();

        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("Tape",Float.toString(calculate.getTAPE())));
        tb.addView(addRow("Towing Charges",Float.toString(calculate.getTowingCharges())));
        tb.addView(addRow("Sub-Total",Float.toString(calculate.getSubTotal())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal())));
        tb.addView(addRow("NIL DEP",Float.toString(calculate.getNIL_DEP())));
        tb.addView(addRow("OD Total",Float.toString(calculate.getOD_Total())));
        tb.addView(addRow("Discount",Float.toString(calculate.getDiscount())));
        tb.addView(addRow("Net OD",Float.toString(calculate.getNET_OD())));
        tb.addView(addRow("TP",Float.toString(calculate.getTP())));
        tb.addView(addRow("CPA-100",Float.toString(calculate.getCPA_100())));
        tb.addView(addRow("Driver",Float.toString(calculate.getDriver())));
        tb.addView(addRow("LPG-60",Float.toString(calculate.getLPG_60())));
        tb.addView(addRow("PA Pass",Float.toString(calculate.getPassPa())));
        tb.addView(addRow("TP Total",Float.toString(calculate.getTP_Total())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal2())));
        tb.addView(addRow("GST",Float.toString(calculate.getGST())));
        tb.addView(addRow("Net Payable",Float.toString(calculate.getNet_Payable())));
    }

    private void getTruckResultandDisplay(){

        mAge = b.getInt("Age");
        mZone = b.getInt("Zone");
        mIDV = b.getInt("IDV");
        mLPG_CNG = b.getInt("LPG_CNG");
        mBuiltInLPG = b.getInt("BuiltInLPG");
        mTowing = b.getInt("Towing");
        mNCB = b.getInt("NCB");
        mNILDEP = b.getInt("NILDEP");
        mCPA = b.getInt("CPA");
        mDiscount = b.getFloat("Discount");
        mGVW = b.getInt("GVW");
        mIMT = b.getInt("IMT");
        mDRCLS =b.getInt("DR_CLS");
        mNFPP = b.getInt("NFPP");
        mPVTPUB = b.getInt("PVTPUB");
        mBuilt_In =  b.getFloat("Built In");

        CalculateTruck calculate = new CalculateTruck(mAge, mZone, mIDV, mLPG_CNG, mBuiltInLPG, mTowing, mNCB, mNILDEP, mCPA, mDiscount,
                mGVW, mIMT, mDRCLS, mNFPP, mPVTPUB);
        calculate.calculateInsurance();

        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("Built In",Float.toString(calculate.getBuilt_IN())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("Towing Charges",Float.toString(calculate.getTowing_Charges())));
        tb.addView(addRow("IMT",Float.toString(calculate.getIMT())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("OD Total",Float.toString(calculate.getOD_Total())));
        tb.addView(addRow("Discount",Float.toString(calculate.getDiscount())));
        tb.addView(addRow("ND Cover",Float.toString(calculate.getND_Cover())));
        tb.addView(addRow("Net OD",Float.toString(calculate.getNET_OD())));
        tb.addView(addRow("TP",Float.toString(calculate.getTP())));
        tb.addView(addRow("DR/CLS",Float.toString(calculate.getDR_CLS())));
        tb.addView(addRow("CPA-100",Float.toString(calculate.getCPA_100())));
        tb.addView(addRow("LPG-60",Float.toString(calculate.getLPG_60())));
        tb.addView(addRow("NFPP",Float.toString(calculate.getNFPP())));
        tb.addView(addRow("TP Total",Float.toString(calculate.getTP_Total())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal())));
        tb.addView(addRow("GST",Float.toString(calculate.getGST())));
        tb.addView(addRow("Net Payable",Float.toString(calculate.getNet_Payable())));
    }

    View.OnClickListener clicks = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == cancel){

                onBackPressed();
            }

            if(view == share){
                View screenshot = tb;
                TakeScreentShot.captureView(screenshot,thisActivity);
            }
        }
    };

    private TableRow addRow(String labelText, String valueText) {
        TableRow tr = new TableRow(this);
        tr.setGravity(Gravity.CENTER_VERTICAL);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1));

        TextView txtViewLabel = new TextView(this);
        txtViewLabel.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        txtViewLabel.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        txtViewLabel.setTextSize(18);
        txtViewLabel.setTextColor(getResources().getColor(R.color.secondaryColor));
        txtViewLabel.setBackgroundResource(R.drawable.table_row_border);
        txtViewLabel.setText(labelText);
        if(labelText.equalsIgnoreCase("net payable"))
            txtViewLabel.setTextColor(getResources().getColor(R.color.secondaryColor));
        tr.addView(txtViewLabel);

        TextView txtViewValue = new TextView(this);
        txtViewValue.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1));
        txtViewValue.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        txtViewValue.setTextSize(18);
        txtViewValue.setTextColor(getResources().getColor(R.color.secondaryColor));
        txtViewValue.setBackgroundResource(R.drawable.table_row_border);
        txtViewValue.setText(valueText);
        tr.addView(txtViewValue);

        return tr;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(adView2.isLoaded())
            adView2.show();
    }
}
