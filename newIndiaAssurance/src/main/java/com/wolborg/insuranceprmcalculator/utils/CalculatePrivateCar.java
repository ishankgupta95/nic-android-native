package com.wolborg.insuranceprmcalculator.utils;

import java.text.DecimalFormat;

/**
 * Created by Abhishek on 01-10-2017.
 */

public class CalculatePrivateCar {

    private final String TAG = "CalculatePrivateCar";
    /**
     * Entry Fields
     */
    private int age;
    private int zone;
    private int cc;
    private int idv;
    private int lpg_cng;
    private int builtInLPG;
    private int tape;
    private int towing;
    private int ncb;
    private int nil_dep;
    private int cpa;
    private int driver;
    private int pa_to_pass ;
    private int pa_amount;
    private float discount;


    /**
     * Result Fields
     */
    private float mBasic = 0;
    private float mLPG = 0;
    private float mTape = 0;
    private float mTowing_Charges = 0;
    private float mSubTotal = 0;
    private float mTotal = 0;
    private float mNCB = 0;
    private float mDiscount = 0;
    private float mNIL_DEP = 0;
    private float mNET_OD = 0;
    private float mOD_Total = 0;
    private float mTP = 0;
    private float mCPA_100 = 0;
    private float mDriver = 0;
    private float mLPG_60 = 0;
    private float mPassPa;
    private float mTP_Total = 0;
    private float mTotal2 = 0;
    private float mGST = 0;
    private float mNet_Payable = 0;


    public CalculatePrivateCar(int age, int zone, int cc, int idv, int lpg_cng, int buitlInLPG, int tape, int towing, int ncb,
                               int nil_dep, int cpa, int driver, int pa_to_pass, int pa_amount, float discount) {

        this.age = age;
        this.zone = zone;
        this.cc = cc;
        this.idv = idv;
        this.lpg_cng = lpg_cng;
        this.builtInLPG = buitlInLPG;
        this.tape = tape;
        this.towing = towing;

        switch(ncb){
            case 0: this.ncb = 0; break;
            case 1: this.ncb = 0; break;
            case 2: this.ncb = 20; break;
            case 3: this.ncb = 25; break;
            case 4: this.ncb = 35; break;
            case 5: this.ncb = 45; break;
            case 6: this.ncb = 50; break;
        }

        this.nil_dep = nil_dep;
        this.cpa = cpa;
        this.driver = driver;
        this.pa_to_pass = pa_to_pass;
        this.pa_amount = pa_amount;
        this.discount = discount;
    }

    public void calculateInsurance(){

        switch (zone){
            case 1:
                switch(this.cc){
                    case 1: mTP = 2055; mBasic = to2decimal(0.025f * (float)idv);  break;
                    case 2: mTP = 2863; mBasic = to2decimal(0.0262f * (float)idv); break;
                    case 3: mTP = 7890; mBasic = to2decimal(0.027f * (float)idv); break;
                }

                if(builtInLPG == 2)
                    mLPG_60 = 60;

                if(lpg_cng!=0 && idv != 0){
                    mLPG = to2decimal(0.04f * lpg_cng);
                    mLPG_60 = 60;
                }

                if(tape!=0 && idv != 0)
                    mTape = to2decimal(0.04f * tape);

                if(towing!=0 && idv != 0)
                    mTowing_Charges = to2decimal(0.05f * towing);

                mSubTotal = mBasic + mLPG + mTowing_Charges + mTape;

                mNCB = to2decimal(ncb * mSubTotal/100);
                //mNCB  = mNCB/100;

                mTotal = Math.round(mSubTotal-mNCB);

                if(nil_dep == 2)
                    mNIL_DEP = to2decimal(getNILDEP(age)*(mBasic + mLPG + mTape));

                if(age >4){
                    mNIL_DEP = 0;
                }

                mOD_Total = mNIL_DEP + mTotal ;

                if(discount!=0)
                    mDiscount = to2decimal((discount * mTotal)/100);

                mNET_OD = Math.round(mOD_Total - mDiscount);

                if(driver==2)
                    mDriver = 50;

                if(cpa==2)
                    mCPA_100 = 100;

                if(pa_to_pass!=0)
                mPassPa = 5 * pa_to_pass * pa_amount/10000;

                mTP_Total = mTP + mCPA_100 + mDriver + mLPG_60 +  mPassPa;

                mTotal2 = mTP_Total + mNET_OD;

                mGST = 2*Math.round(mTotal2 * 0.09f);

                mNet_Payable = mGST + mTotal2;

                break;

            case 2:
                switch(this.cc){
                case 1: mTP = 2055; mBasic = to2decimal(0.022f * (float)idv);  break;
                case 2: mTP = 2863; mBasic = to2decimal(0.0252f * (float)idv); break;
                case 3: mTP = 7890; mBasic = to2decimal(0.0234f * (float)idv); break;
            }

                if(builtInLPG == 2)
                    mLPG_60 = 60;

                if(lpg_cng!=0 && idv != 0){
                    mLPG = to2decimal(0.04f * lpg_cng);
                    mLPG_60 = 60;
                }

                if(tape!=0 && idv != 0)
                    mTape = to2decimal(0.04f * tape);

                if(towing!=0 && idv != 0)
                    mTowing_Charges = to2decimal(0.05f * towing);

                mSubTotal = mBasic + mLPG + mTowing_Charges + mTape;


                mNCB = to2decimal(ncb * mSubTotal/100);
                //mNCB  = mNCB/100;

                mTotal = Math.round(mSubTotal-mNCB);

                if(nil_dep == 2)
                    mNIL_DEP = to2decimal(getNILDEP(age)*(mBasic + mLPG + mTape));

                if(age >4){
                    mNIL_DEP = 0;
                }

                mOD_Total = mNIL_DEP + mTotal ;

                if(discount!=0)
                    mDiscount = to2decimal((discount * mTotal)/100);

                mNET_OD = Math.round(mOD_Total - mDiscount);

                if(driver==2)
                    mDriver = 50;

                if(cpa==2)
                    mCPA_100 = 100;

                if(pa_to_pass!=0)
                    mPassPa = 5 * pa_to_pass * pa_amount/10000;

                mTP_Total = mTP + mCPA_100 + mDriver + mLPG_60 +  mPassPa;

                mTotal2 = mTP_Total + mNET_OD;

                mGST = 2*Math.round(mTotal2 * 0.09f);

                mNet_Payable = mGST + mTotal2;

                break;

            case 3:
                switch(this.cc){
                    case 1: mTP = 2055; mBasic = to2decimal(0.0219f * (float)idv);  break;
                    case 2: mTP = 2863; mBasic = to2decimal(0.0243f * (float)idv); break;
                    case 3: mTP = 7890; mBasic = to2decimal(0.0225f * (float)idv); break;
                }

                if(builtInLPG == 2)
                    mLPG_60 = 60;

                if(lpg_cng!=0 && idv != 0){
                    mLPG = to2decimal(0.04f * lpg_cng);
                    mLPG_60 = 60;
                }

                if(tape!=0 && idv != 0)
                    mTape = to2decimal(0.04f * tape);

                if(towing!=0 && idv != 0)
                    mTowing_Charges = to2decimal(0.05f * towing);

                mSubTotal = mBasic + mLPG + mTowing_Charges + mTape;

                mNCB = to2decimal(ncb * mSubTotal/100);
                //mNCB  = mNCB/100;

                mTotal = Math.round(mSubTotal-mNCB);

                if(nil_dep == 2)
                    mNIL_DEP = to2decimal(getNILDEP(age)*(mBasic + mLPG + mTape));

                if(age >4){
                    mNIL_DEP = 0;
                }

                mOD_Total = mNIL_DEP + mTotal ;

                if(discount!=0)
                    mDiscount = to2decimal((discount * mTotal)/100);

                mNET_OD = Math.round(mOD_Total - mDiscount);

                if(driver==2)
                    mDriver = 50;

                if(cpa==2)
                    mCPA_100 = 100;

                if(pa_to_pass!=0)
                    mPassPa = 5 * pa_to_pass * pa_amount/10000;

                mTP_Total = mTP + mCPA_100 + mDriver + mLPG_60 +  mPassPa;

                mTotal2 = mTP_Total + mNET_OD;

                mGST = 2*Math.round(mTotal2 * 0.09f);

                mNet_Payable = mGST + mTotal2;

                break;

            case 4:
                switch(this.cc){
                    case 1: mTP = 2055; mBasic = to2decimal(0.0207f * (float)idv);  break;
                    case 2: mTP = 2863; mBasic = to2decimal(0.0225f * (float)idv); break;
                    case 3: mTP = 7890; mBasic = to2decimal(0.0224f * (float)idv); break;
                }

                if(builtInLPG == 2)
                    mLPG_60 = 60;

                if(lpg_cng!=0 && idv != 0){
                    mLPG = to2decimal(0.04f * lpg_cng);
                    mLPG_60 = 60;
                }

                if(tape!=0 && idv != 0)
                    mTape = to2decimal(0.04f * tape);

                if(towing!=0 && idv != 0)
                    mTowing_Charges = to2decimal(0.05f * towing);

                mSubTotal = mBasic + mLPG + mTowing_Charges + mTape;

                mNCB = to2decimal(ncb * mSubTotal/100);
                //mNCB  = mNCB/100;

                mTotal = Math.round(mSubTotal-mNCB);

                if(nil_dep == 2)
                    mNIL_DEP = to2decimal(getNILDEP(age)*(mBasic + mLPG + mTape));

                if(age >4){
                    mNIL_DEP = 0;
                }

                mOD_Total = mNIL_DEP + mTotal ;

                if(discount!=0)
                    mDiscount = to2decimal((discount * mTotal)/100);

                mNET_OD = Math.round(mOD_Total - mDiscount);

                if(driver==2)
                    mDriver = 50;

                if(cpa==2)
                    mCPA_100 = 100;

                if(pa_to_pass!=0)
                    mPassPa = 5 * pa_to_pass * pa_amount/10000;

                mTP_Total = Math.round(mTP + mCPA_100 + mDriver + mLPG_60 +  mPassPa);

                mTotal2 = mTP_Total + mNET_OD;

                mGST = 2*Math.round(mTotal2 * 0.09f);

                mNet_Payable = mGST + mTotal2;

                break;
        }

    }

    private float to2decimal(float x){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float twoDigitsF = Float.valueOf(decimalFormat.format(x));
        return twoDigitsF;
    }

    public float getBasic(){ return to2decimal(mBasic);}

    public float getLPG(){return to2decimal(mLPG);}

    public float getTAPE(){return to2decimal(mTape);}

    public float getTowingCharges(){return to2decimal(mTowing_Charges);}

    public float getSubTotal(){return mSubTotal;}

    public float getNCB(){return to2decimal(mNCB);}

    public float getTotal(){return to2decimal(mTotal);}

    public float getNIL_DEP(){return to2decimal(mNIL_DEP);}

    public float getOD_Total(){return mOD_Total;}

    public float getDiscount(){return mDiscount;}

    public float getNET_OD(){return mNET_OD;}

    public float getTP(){return mTP;}

    public float getCPA_100(){return mCPA_100;}

    public float getDriver(){return mDriver;}

    public float getLPG_60(){return mLPG_60;}

    public float getPassPa(){return mPassPa;}

    public float getTP_Total(){return mTP_Total;}

    public float getTotal2(){return mTotal2;}

    public float getGST(){return mGST;}

    public float getNet_Payable(){return Math.round(mNet_Payable);}

    private float getNILDEP(int age) {

        float a = 0;

        if(age == 0)
            a = 0.15f;
        else if(age>=1 && age<2)
            a = 0.25f;
        else if(age>=2 && age<3)
            a = 0.35f;
        else if(age>=3)
            a = 0.40f;

        return  a;
    }


}
