package com.ishank.insuranceprmcalculator.activities;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.inmobi.ads.InMobiInterstitial;
import com.ishank.insuranceprmcalculator.R;
import com.ishank.insuranceprmcalculator.fragments.health.HealthFragment;
import com.ishank.insuranceprmcalculator.fragments.motor.*;
import com.ishank.insuranceprmcalculator.utils.*;

public class ResultActivity extends AppCompatActivity {

    private final String TAG = "ResultActivity";
    Activity thisActivity = this;
    Bundle b ;

    TableLayout tb;
    Button cancel;
    Button share;
    InMobiInterstitial interstitialAd;
    Boolean mCanShowAd = true;


    /**
     * Variables
     */
    private int mAge;
    private int mIDV;
    private int mZone;
    private int mNumberOfPassengers;
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
    private int mEngine_Protect;
    private int mNCB_Protect;
    private int mInvoice_Protect;
    private int mPassenger;
    private int mSum;
    private int mSpouse;
    private int mChild1;
    private int mChild2;
    private int mDiabetes;

    /**
     * Ads Object
     */

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

            case TwoWheelerODFragment.TAG :
                getTwoWheelerODResultandDisplay();
                break;

            case PrivateCarFragment.TAG:
                getPrivateCarResultandDisplay();
                break;

            case PrivateCarODFragment.TAG:
                getPrivateCarODResultandDisplay();
                break;

            case TruckFragment.TAG :
                getTruckResultandDisplay();
                break;

            case TaxiFragment.TAG :
                getTaxiResultandDisplay();
                break;

            case RickshawFragment.TAG :
                getRickShawResultandDisplay();
                break;

            case Rickshaw7Fragment.TAG :
                getRickShaw7ResultandDisplay();
                break;

            case ThreeWheelPickupFragment.TAG :
                getThreeWheelPickupResultandDisplay();
                break;

            case FourWhPassMoreSixFragment.TAG :
                getFourWhPassMoreSixResultandDisplay();
                break;

            case HealthFragment.TAG :
                getHealthResultandDisplay();
                break;

            case LTBPvtCarFragment.TAG :
                getLTBPvtCarResultandDisplay();
                break;

            case LTBTwoWheelerFragment.TAG :
                getLTBTwoWheelerResultandDisplay();
                break;

            default:
                Toast.makeText(getApplicationContext(),"Some Technical Difficulty Occurred ",Toast.LENGTH_SHORT).show();
        }

    }

    private void getRickShawResultandDisplay() {

        mAge = b.getInt("Age");
        mZone = b.getInt("Zone");
        mIDV = b.getInt("IDV");
        mLPG_CNG = b.getInt("LPG_CNG");
        mBuiltInLPG = b.getInt("BuiltInLPG");
        mIMT = b.getInt("IMT");
        mPassenger = b.getInt("Pass");
        mNCB = b.getInt("NCB");
        mNILDEP = b.getInt("NILDEP");
        mCPA = b.getInt("CPA");
        mDriver =  b.getInt("Driver");
        mDiscount = b.getFloat("Discount");

        CalculateRickshaw calculate = new CalculateRickshaw(mAge, mZone, mIDV, mLPG_CNG, mBuiltInLPG, mIMT, mNCB, mPassenger, mNILDEP, mCPA, mDriver, mDiscount);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("Built In",Float.toString(calculate.getmBuilt_In())));
        tb.addView(addRow("IMT",Float.toString(calculate.getIMT())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("OD",Float.toString(calculate.getOD())));
        tb.addView(addRow("Discount",Float.toString(calculate.getDiscount())));
        tb.addView(addRow("ND Cover",Float.toString(calculate.getND_Cover())));
        tb.addView(addRow("OD Total",Float.toString(calculate.getOD_Total())));
        tb.addView(addRow("TP",Float.toString(calculate.getTP())));
        tb.addView(addRow("Pass",Float.toString(calculate.getPass())));
        tb.addView(addRow("Driver",Float.toString(calculate.getDriver())));
        tb.addView(addRow("CPA",Float.toString(calculate.getCPA_100())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG_60())));
        tb.addView(addRow("TP Total",Float.toString(calculate.getTP_Total())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal())));
        tb.addView(addRow("GST",Float.toString(calculate.getGST())));
        tb.addView(addRow("Net Payable",Float.toString(calculate.getNet_Payable())));

    }

    private void getRickShaw7ResultandDisplay() {

        mAge = b.getInt("Age");
        mZone = b.getInt("Zone");
        mIDV = b.getInt("IDV");
        mLPG_CNG = b.getInt("LPG_CNG");
        mBuiltInLPG = b.getInt("BuiltInLPG");
        mIMT = b.getInt("IMT");
        mPassenger = b.getInt("Pass");
        mNCB = b.getInt("NCB");
        mCPA = b.getInt("CPA");
        mDriver =  b.getInt("Driver");

        CalculateRickshaw7 calculate = new CalculateRickshaw7(mAge, mZone, mIDV, mLPG_CNG, mBuiltInLPG, mIMT, mNCB, mPassenger, mCPA, mDriver);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("IMT",Float.toString(calculate.getIMT())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("OD",Float.toString(calculate.getOD())));
        tb.addView(addRow("OD Total",Float.toString(calculate.getOD_Total())));
        tb.addView(addRow("TP",Float.toString(calculate.getTP())));
        tb.addView(addRow("Passenger",Float.toString(calculate.getPass())));
        tb.addView(addRow("Driver",Float.toString(calculate.getDriver())));
        tb.addView(addRow("CPA-100",Float.toString(calculate.getCPA_100())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG_60())));
        tb.addView(addRow("TP Total",Float.toString(calculate.getTP_Total())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal())));
        tb.addView(addRow("GST",Float.toString(calculate.getGST())));
        tb.addView(addRow("Net Payable",Float.toString(calculate.getNet_Payable())));

    }

    private void getHealthResultandDisplay() {

        mAge = b.getInt("Age");
        mSum = b.getInt("Sum");
        mSpouse = b.getInt("Spouse");
        mChild1 = b.getInt("Child1");
        mChild2 = b.getInt("Child2");
        mDiabetes = b.getInt("Diabetes");

        CalculateHealth calculate = new CalculateHealth(mAge, mSum, mSpouse, mChild1, mChild2, mDiabetes);
        calculate.calculateInsurance();

        tb.addView(addRow("Sum Insured Opted", Integer.toString(calculate.getmSum())));
        tb.addView(addRow("Self", Integer.toString(calculate.getmSelf())));
        tb.addView(addRow("Spouse", Integer.toString(calculate.getmSpouse())));
        tb.addView(addRow("Child 1", Integer.toString(calculate.getmChild1())));
        tb.addView(addRow("Child 2", Integer.toString(calculate.getmChild2())));
        tb.addView(addRow("Total", Integer.toString(calculate.getmTotal())));
        tb.addView(addRow("Diabates/Hypertension", Integer.toString(calculate.getmLoading())));
        tb.addView(addRow("Total", Integer.toString(calculate.getmTotal_Premium())));
        tb.addView(addRow("GST", Integer.toString(calculate.getmGST())));
        tb.addView(addRow("Final Amount", Integer.toString(calculate.getmFinal_Amount())));
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
        mEngine_Protect = b.getInt("Engine Protect");
        mNCB_Protect = b.getInt("NCB Protect");
        mInvoice_Protect = b.getInt("Invoice Protect");
        CalculateTwoWheeler calculate = new CalculateTwoWheeler(mAge,mZone,mCC,mIDV,mNCB,mNILDEP,mCPA,mPAtoPass,mPA_Amount,mDiscount,mEngine_Protect,mNCB_Protect,mInvoice_Protect);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getmBasic())));
        tb.addView(addRow("NCB",Float.toString(calculate.getmNCB())));
        tb.addView(addRow("OD",Float.toString(calculate.getmOD())));
        tb.addView(addRow("Discount",Float.toString(calculate.getmDiscount())));
        tb.addView(addRow("NIL DEP",Float.toString(calculate.getmNIL_DEP())));
        tb.addView(addRow("Engine Protect",Integer.toString(calculate.getmEngine_Protect())));
        tb.addView(addRow("NCB Protect",Integer.toString(calculate.getmNCB_Protect())));
        tb.addView(addRow("Invoice Protect",Integer.toString(calculate.getmInvoice_Protect())));
        tb.addView(addRow("Net OD",Float.toString(calculate.getmNET_OD())));
        tb.addView(addRow("TP",Integer.toString(calculate.getmTP())));
        tb.addView(addRow("CPA-50",Integer.toString(calculate.getmCPA_50())));
        tb.addView(addRow("PA Pass",Integer.toString(calculate.getmPassPa())));
        tb.addView(addRow("TP Total",Integer.toString(calculate.getmTP_Total())));
        tb.addView(addRow("Total",Integer.toString(calculate.getmTotal())));
        tb.addView(addRow("GST",Integer.toString(calculate.getmGST())));
        tb.addView(addRow("Net Payable",Integer.toString(calculate.getmNet_Payable())));
    }

    private void getTwoWheelerODResultandDisplay() {
        mAge = b.getInt("Age");
        mZone = b.getInt("Zone");
        mCC = b.getInt("CC");
        mIDV = b.getInt("IDV");
        mNCB = b.getInt("NCB");
        mNILDEP = b.getInt("NILDEP");
        mCPA = b.getInt("CPA");
//        mPAtoPass = b.getInt("PAtoPass");
//        mPA_Amount = b.getInt("PA_Amount");
        mDiscount = b.getFloat("Discount");
        mEngine_Protect = b.getInt("Engine Protect");
        mNCB_Protect = b.getInt("NCB Protect");
        mInvoice_Protect = b.getInt("Invoice Protect");
        CalculateTwoWheelerOD calculate = new CalculateTwoWheelerOD(mAge,mZone,mCC,mIDV,mNCB,mNILDEP,mDiscount,mEngine_Protect,mNCB_Protect,mInvoice_Protect);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getmBasic())));
        tb.addView(addRow("NCB",Float.toString(calculate.getmNCB())));
        tb.addView(addRow("OD",Float.toString(calculate.getmOD())));
        tb.addView(addRow("Discount",Float.toString(calculate.getmDiscount())));
        tb.addView(addRow("NIL DEP",Float.toString(calculate.getmNIL_DEP())));
        tb.addView(addRow("Engine Protect",Integer.toString(calculate.getmEngine_Protect())));
        tb.addView(addRow("NCB Protect",Integer.toString(calculate.getmNCB_Protect())));
        tb.addView(addRow("Invoice Protect",Integer.toString(calculate.getmInvoice_Protect())));
        tb.addView(addRow("Net OD",Float.toString(calculate.getmNET_OD())));
//        tb.addView(addRow("TP",Integer.toString(calculate.getmTP())));
//        tb.addView(addRow("CPA-50",Integer.toString(calculate.getmCPA_50())));
//        tb.addView(addRow("PA Pass",Integer.toString(calculate.getmPassPa())));
//        tb.addView(addRow("TP Total",Integer.toString(calculate.getmTP_Total())));
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
        mEngine_Protect = b.getInt("Engine Protect");
        mNCB_Protect = b.getInt("NCB Protect");
        mInvoice_Protect = b.getInt("Invoice Protect");
        CalculatePrivateCar calculate = new CalculatePrivateCar(mAge, mZone, mCC, mIDV, mLPG_CNG, mBuiltInLPG, mTape, mTowing, mNCB,
                mNILDEP, mCPA, mDriver, mPAtoPass, mPA_Amount, mDiscount,mEngine_Protect,mNCB_Protect,mInvoice_Protect);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("Tape",Float.toString(calculate.getTAPE())));
        tb.addView(addRow("Towing Charges",Float.toString(calculate.getTowingCharges())));
        tb.addView(addRow("Sub-Total",Float.toString(calculate.getSubTotal())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal())));
        tb.addView(addRow("NIL DEP",Float.toString(calculate.getNIL_DEP())));
        tb.addView(addRow("Engine Protect",Integer.toString(calculate.getmEngine_Protect())));
        tb.addView(addRow("NCB Protect",Integer.toString(calculate.getmNCB_Protect())));
        tb.addView(addRow("Invoice Protect",Integer.toString(calculate.getmInvoice_Protect())));
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

    private void getPrivateCarODResultandDisplay(){

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
        mEngine_Protect = b.getInt("Engine Protect");
        mNCB_Protect = b.getInt("NCB Protect");
        mInvoice_Protect = b.getInt("Invoice Protect");
        CalculatePrivateCarOD calculate = new CalculatePrivateCarOD(mAge, mZone, mCC, mIDV, mLPG_CNG, mBuiltInLPG, mTape, mTowing, mNCB,
                mNILDEP, mCPA, mDriver, mPAtoPass, mPA_Amount, mDiscount,mEngine_Protect,mNCB_Protect,mInvoice_Protect);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("Tape",Float.toString(calculate.getTAPE())));
        tb.addView(addRow("Towing Charges",Float.toString(calculate.getTowingCharges())));
        tb.addView(addRow("Sub-Total",Float.toString(calculate.getSubTotal())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal())));
        tb.addView(addRow("NIL DEP",Float.toString(calculate.getNIL_DEP())));
        tb.addView(addRow("Engine Protect",Integer.toString(calculate.getmEngine_Protect())));
        tb.addView(addRow("NCB Protect",Integer.toString(calculate.getmNCB_Protect())));
        tb.addView(addRow("Invoice Protect",Integer.toString(calculate.getmInvoice_Protect())));
        tb.addView(addRow("OD Total",Float.toString(calculate.getOD_Total())));
        tb.addView(addRow("Discount",Float.toString(calculate.getDiscount())));
        tb.addView(addRow("Net OD",Float.toString(calculate.getNET_OD())));
//        tb.addView(addRow("TP",Float.toString(calculate.getTP())));
//        tb.addView(addRow("CPA-100",Float.toString(calculate.getCPA_100())));
//        tb.addView(addRow("Driver",Float.toString(calculate.getDriver())));
//        tb.addView(addRow("LPG-60",Float.toString(calculate.getLPG_60())));
//        tb.addView(addRow("PA Pass",Float.toString(calculate.getPassPa())));
//        tb.addView(addRow("TP Total",Float.toString(calculate.getTP_Total())));
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
        mEngine_Protect = b.getInt("Engine Protect");
        mNCB_Protect = b.getInt("NCB Protect");
        mInvoice_Protect = b.getInt("Invoice Protect");
        CalculateTruck calculate = new CalculateTruck(mAge, mZone, mIDV, mLPG_CNG, mBuiltInLPG, mTowing, mNCB, mNILDEP, mCPA, mDiscount,
                mGVW, mIMT, mDRCLS, mNFPP, mPVTPUB ,mInvoice_Protect,mEngine_Protect,mNCB_Protect);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("Built In",Float.toString(calculate.getBuilt_IN())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("Towing Charges",Float.toString(calculate.getTowing_Charges())));
        tb.addView(addRow("IMT",Float.toString(calculate.getIMT())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("ND Cover",Float.toString(calculate.getND_Cover())));
        tb.addView(addRow("Engine Protect",Integer.toString(calculate.getmEngine_Protect())));
        tb.addView(addRow("NCB Protect",Integer.toString(calculate.getmNCB_Protect())));
        tb.addView(addRow("Invoice Protect",Integer.toString(calculate.getmInvoice_Protect())));
        tb.addView(addRow("OD Total",Float.toString(calculate.getOD_Total())));
        tb.addView(addRow("Discount",Float.toString(calculate.getDiscount())));
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

    private void getThreeWheelPickupResultandDisplay(){

        mAge = b.getInt("Age");
        mZone = b.getInt("Zone");
        mIDV = b.getInt("IDV");
        mLPG_CNG = b.getInt("LPG_CNG");
        mBuiltInLPG = b.getInt("BuiltInLPG");
        mNCB = b.getInt("NCB");
        mNILDEP = b.getInt("NILDEP");
        mCPA = b.getInt("CPA");
        mDiscount = b.getFloat("Discount");
        mIMT = b.getInt("IMT");
        mPVTPUB = b.getInt("PVTPUB");
        mBuilt_In =  b.getFloat("Built In");
        mDriver = b.getInt("Driver");
        CalculateThreeWheelPickup calculate = new CalculateThreeWheelPickup(mAge, mZone, mIDV, mLPG_CNG, mBuiltInLPG, mNCB, mNILDEP, mCPA, mDiscount,
                mIMT, mPVTPUB, mDriver);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("Built In",Float.toString(calculate.getBuilt_IN())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("IMT",Float.toString(calculate.getIMT())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("OD",Float.toString(calculate.getOD())));
        tb.addView(addRow("Discount",Float.toString(calculate.getDiscount())));
        tb.addView(addRow("ND Cover",Float.toString(calculate.getND_Cover())));
        tb.addView(addRow("OD Total",Float.toString(calculate.getOD_Total())));
        tb.addView(addRow("TP",Float.toString(calculate.getTP())));
        tb.addView(addRow("CPA",Float.toString(calculate.getCPA_100())));
        tb.addView(addRow("Driver",Float.toString(calculate.getDriver())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG_60())));
        tb.addView(addRow("TP Total",Float.toString(calculate.getTP_Total())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal())));
        tb.addView(addRow("GST",Float.toString(calculate.getGST())));
        tb.addView(addRow("Net Payable",Float.toString(calculate.getNet_Payable())));
    }

    private void getFourWhPassMoreSixResultandDisplay(){

        mAge = b.getInt("Age");
        mZone = b.getInt("Zone");
        mNumberOfPassengers = b.getInt("No_of_Pass");
        mIDV = b.getInt("IDV");
        mLPG_CNG = b.getInt("LPG_CNG");
        mBuiltInLPG = b.getInt("BuiltInLPG");
        mNCB = b.getInt("NCB");
        mNILDEP = b.getInt("NILDEP");
        mCPA = b.getInt("CPA");
        mDiscount = b.getFloat("Discount");
        mIMT = b.getInt("IMT");
        mDRCLS =b.getInt("DR_CLS");
        mPVTPUB = b.getInt("PVTPUB");
        mBuilt_In =  b.getFloat("Built In");
        CalculateFourWhPassMoreSix calculate = new CalculateFourWhPassMoreSix(mAge, mZone, mIDV, mLPG_CNG, mBuiltInLPG, mNCB, mNILDEP, mCPA, mDiscount, mIMT, mDRCLS, mNumberOfPassengers);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("OD",Float.toString(calculate.getOD())));
        tb.addView(addRow("Built In",Float.toString(calculate.getBuilt_IN())));
        tb.addView(addRow("LPG/CNG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("IMT",Float.toString(calculate.getIMT())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("OD Total",Float.toString(calculate.getOD_Total())));
        tb.addView(addRow("Discount",Float.toString(calculate.getDiscount())));
        tb.addView(addRow("ND Cover",Float.toString(calculate.getND_Cover())));
        tb.addView(addRow("Net OD",Float.toString(calculate.getNET_OD())));
        tb.addView(addRow("TP",Float.toString(calculate.getTP())));
        tb.addView(addRow("Pass",Float.toString(calculate.getPass())));
        tb.addView(addRow("DR/CLS",Float.toString(calculate.getDR_CLS())));
        tb.addView(addRow("CPA",Float.toString(calculate.getCPA_100())));
        tb.addView(addRow("LPG/CNG",Float.toString(calculate.getLPG_60())));
        tb.addView(addRow("TP Total",Float.toString(calculate.getTP_Total())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal())));
        tb.addView(addRow("GST",Float.toString(calculate.getGST())));
        tb.addView(addRow("Net Payable",Float.toString(calculate.getNet_Payable())));
    }

    private void getTaxiResultandDisplay(){

        mAge = b.getInt("Age");
        mZone = b.getInt("Zone");
        mIDV = b.getInt("IDV");
        mCC = b.getInt("CC");
        mLPG_CNG = b.getInt("LPG_CNG");
        mBuiltInLPG = b.getInt("BuiltInLPG");
        mIMT = b.getInt("IMT");
        mPassenger = b.getInt("Passenger");
        mNCB = b.getInt("NCB");
        mNILDEP = b.getInt("NILDEP");
        mCPA = b.getInt("CPA");
        mDriver =  b.getInt("Driver");
        mDiscount = b.getFloat("Discount");

        CalculateTaxi calculate = new CalculateTaxi(mAge, mZone, mIDV, mCC, mLPG_CNG, mBuiltInLPG, mIMT, mPassenger, mNCB, mNILDEP, mCPA, mDriver, mDiscount);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("Built In",Float.toString(calculate.getBuilt_IN())));
        tb.addView(addRow("IMT",Float.toString(calculate.getIMT())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("OD",Float.toString(calculate.getOD())));
        tb.addView(addRow("Discount",Float.toString(calculate.getDiscount())));
        tb.addView(addRow("ND Cover",Float.toString(calculate.getND_Cover())));
        tb.addView(addRow("OD Total",Float.toString(calculate.getOD_Total())));
        tb.addView(addRow("TP",Float.toString(calculate.getTP())));
        tb.addView(addRow("Pass",Float.toString(calculate.getPass())));
        tb.addView(addRow("Driver",Float.toString(calculate.getDriver())));
        tb.addView(addRow("CPA",Float.toString(calculate.getCPA_100())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG_60())));
        tb.addView(addRow("TP Total",Float.toString(calculate.getTP_Total())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal())));
        tb.addView(addRow("GST",Float.toString(calculate.getGST())));
        tb.addView(addRow("Net Payable",Float.toString(calculate.getNet_Payable())));
    }

    private void getLTBPvtCarResultandDisplay(){

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
        mEngine_Protect = b.getInt("Engine Protect");
        mNCB_Protect = b.getInt("NCB Protect");
        mInvoice_Protect = b.getInt("Invoice Protect");
        CalculateLTBPvtCar calculate = new CalculateLTBPvtCar(mAge, mZone, mCC, mIDV, mLPG_CNG, mBuiltInLPG, mTape, mTowing, mNCB,
                mNILDEP, mCPA, mDriver, mPAtoPass, mPA_Amount, mDiscount,mEngine_Protect,mNCB_Protect,mInvoice_Protect);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getBasic())));
        tb.addView(addRow("LPG",Float.toString(calculate.getLPG())));
        tb.addView(addRow("Tape",Float.toString(calculate.getTAPE())));
        tb.addView(addRow("Towing Charges",Float.toString(calculate.getTowingCharges())));
        tb.addView(addRow("Sub-Total",Float.toString(calculate.getSubTotal())));
        tb.addView(addRow("NCB",Float.toString(calculate.getNCB())));
        tb.addView(addRow("Total",Float.toString(calculate.getTotal())));
        tb.addView(addRow("NIL DEP",Float.toString(calculate.getNIL_DEP())));
        tb.addView(addRow("Engine Protect",Integer.toString(calculate.getmEngine_Protect())));
        tb.addView(addRow("NCB Protect",Integer.toString(calculate.getmNCB_Protect())));
        tb.addView(addRow("Invoice Protect",Integer.toString(calculate.getmInvoice_Protect())));
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

    private void getLTBTwoWheelerResultandDisplay() {
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
        mEngine_Protect = b.getInt("Engine Protect");
        mNCB_Protect = b.getInt("NCB Protect");
        mInvoice_Protect = b.getInt("Invoice Protect");
        CalculateLTBTwoWheeler calculate = new CalculateLTBTwoWheeler(mAge,mZone,mCC,mIDV,mNCB,mNILDEP,mCPA,mPAtoPass,mPA_Amount,mDiscount,mEngine_Protect,mNCB_Protect,mInvoice_Protect);
        calculate.calculateInsurance();

        tb.addView(addRow("For IDV",calculate.getIDV()));
        tb.addView(addRow("Basic",Float.toString(calculate.getmBasic())));
        tb.addView(addRow("NCB",Float.toString(calculate.getmNCB())));
        tb.addView(addRow("OD",Float.toString(calculate.getmOD())));
        tb.addView(addRow("Discount",Float.toString(calculate.getmDiscount())));
        tb.addView(addRow("NIL DEP",Float.toString(calculate.getmNIL_DEP())));
        tb.addView(addRow("Engine Protect",Integer.toString(calculate.getmEngine_Protect())));
        tb.addView(addRow("NCB Protect",Integer.toString(calculate.getmNCB_Protect())));
        tb.addView(addRow("Invoice Protect",Integer.toString(calculate.getmInvoice_Protect())));
        tb.addView(addRow("Net OD",Float.toString(calculate.getmNET_OD())));
        tb.addView(addRow("TP",Integer.toString(calculate.getmTP())));
        tb.addView(addRow("CPA-50",Integer.toString(calculate.getmCPA_50())));
        tb.addView(addRow("PA Pass",Integer.toString(calculate.getmPassPa())));
        tb.addView(addRow("TP Total",Integer.toString(calculate.getmTP_Total())));
        tb.addView(addRow("Total",Integer.toString(calculate.getmTotal())));
        tb.addView(addRow("GST",Integer.toString(calculate.getmGST())));
        tb.addView(addRow("Net Payable",Integer.toString(calculate.getmNet_Payable())));
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
        txtViewLabel.setTextColor(getResources().getColor(R.color.primaryColor));
        txtViewLabel.setBackgroundResource(R.drawable.table_row_border);
        txtViewLabel.setText(labelText);
        if(labelText.equalsIgnoreCase("net payable"))
            txtViewLabel.setTextColor(getResources().getColor(R.color.secondaryColor));
        tr.addView(txtViewLabel);

        TextView txtViewValue = new TextView(this);
        txtViewValue.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1));
        txtViewValue.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        txtViewValue.setTextSize(18);
        txtViewValue.setTextColor(getResources().getColor(R.color.primaryDarkColor));
        txtViewValue.setBackgroundResource(R.drawable.table_row_border);
        txtViewValue.setText(valueText);
        if(labelText.equalsIgnoreCase("net payable"))
            txtViewValue.setTextColor(getResources().getColor(R.color.secondaryColor));
        tr.addView(txtViewValue);

        return tr;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
