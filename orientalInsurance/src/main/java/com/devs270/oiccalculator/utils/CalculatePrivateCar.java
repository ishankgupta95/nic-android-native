package com.devs270.oiccalculator.utils;

import java.text.DecimalFormat;

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
    private float nonElectrical =0;
    private int towing;
    private int ncb;
    private int nil_dep;
    private int rti;
    private int cpa;
    private int driver;
    private int pa_to_pass ;
    private int pa_amount;
    private float underwriting_discount;
    private float ndp_discount;
    private float other_add_on_discount;


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
    private float mNIL_DEP = 0;
    private float mNET_OD = 0;
    private float mOD_Total = 0;
    private float mTP = 0;
    private float mCPA_100 = 0;
    private float mDriver = 0;
    private float mLPG_60 = 0;
    private float mPassPa;
    private float mBasicTotal;
    private float mTP_Total = 0;
    private float mTotal2 = 0;
    private float mGST = 0;
    private float mNet_Payable = 0;
    private float mBuiltInLPG = 0;
    private float mRTI = 0;
    private float mUnderwritingDiscount = 0;
    private float mNDPDiscount = 0;
    private float mOtherAddOnDiscount = 0;

    public static float age_zoneBValue[][] = {{ 3.039f, 3.191f, 3.343f},
            { 3.191f, 3.351f, 3.510f},
            { 3.267f, 3.430f, 3.594f}};

    public static float age_zoneAValue[][] = {{ 3.127f, 3.283f, 3.440f},
            { 3.283f, 3.447f, 3.612f},
            { 3.362f, 3.529f, 3.698f}};


    public CalculatePrivateCar(int age, int zone, int cc, int idv, int lpg_cng, int buitlInLPG, int tape, int towing, int ncb,
                               int nil_dep, int rti, int cpa, int driver, int pa_to_pass, int pa_amount, float underwriting_discount,
                               float ndp_discount,float other_add_on_discount) {

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
        this.rti = rti;
        this.cpa = cpa;
        this.driver = driver;
        this.pa_to_pass = pa_to_pass;
        this.pa_amount = pa_amount;
        this.underwriting_discount = underwriting_discount;
        this.ndp_discount = ndp_discount;
        this.other_add_on_discount = other_add_on_discount;
    }

    public void calculateInsurance(){

        switch (zone){
            case 1:
                switch(this.cc){
                    case 1: mTP = 2055; mBasic = to2decimal(age_zoneAValue[cc-1][getAgeGroup(age)] * ((float)idv + nonElectrical)/100);  break;
                    case 2: mTP = 2863; mBasic = to2decimal(age_zoneAValue[cc-1][getAgeGroup(age)] * ((float)idv + nonElectrical)/100); break;
                    case 3: mTP = 7890; mBasic = to2decimal(age_zoneAValue[cc-1][getAgeGroup(age)] * ((float)idv + nonElectrical)/100); break;
                }

                if((builtInLPG == 2) && (lpg_cng == 0)) {
                    mLPG_60 = 60;
                    mBuiltInLPG = (0.05f * to2decimal(nonElectrical + mBasic));
                }
                else if(lpg_cng != 0){
                    mBuiltInLPG = 0;
                }

                if((lpg_cng!=0) && (idv != 0) && (builtInLPG == 0)){
                    mLPG = to2decimal(0.04f * lpg_cng);
                    mLPG_60 = 60;
                }

                else if(builtInLPG != 0){
                    mLPG = 0;
                }

                if(tape!=0 && idv != 0)
                    mTape = to2decimal(0.04f * tape);

                if(underwriting_discount != 0) {
                    mUnderwritingDiscount = to2decimal((underwriting_discount* (mBasic + mTape + mLPG + mBuiltInLPG))/100); }

                else {
                    mUnderwritingDiscount = 0;
                }

                mBasicTotal = to2decimal(mBasic + mTape + mBuiltInLPG + mLPG - mUnderwritingDiscount);

                if(nil_dep == 2) {
                    if(ndp_discount != 0){
                        mNIL_DEP = to2decimal((ndp_discount * (getNILDEP(age) * (mBasic + mLPG + mTape + mLPG_60)))/100);
                    }

                    else{
                        mNIL_DEP = to2decimal(getNILDEP(age) * (mBasic + mLPG + mTape + mLPG_60));
                    }
                }

                if(towing!=0 && idv != 0)
                    mTowing_Charges = to2decimal(0.05f * towing);

                mTotal = mBasicTotal + mTowing_Charges - mNIL_DEP;

                mNCB = to2decimal((ncb * (mOD_Total + mNIL_DEP + mTowing_Charges))/100);
                //mNCB  = mNCB/100;

                mOD_Total = to2decimal(mBasic + mTape + mLPG + mBuiltInLPG - mUnderwritingDiscount);

                if(rti == 2){
                    if(age == 0){
                        if(other_add_on_discount != 0){
                            mRTI = to2decimal((other_add_on_discount * ((0.30f/100) * (idv + nonElectrical)))/100);}

                        else {
                            mRTI = to2decimal((0.30f/100) * (idv + nonElectrical));
                        }
                    }

                    else if (age == 1){
                        if(other_add_on_discount != 0){
                            mRTI = to2decimal((other_add_on_discount * ((0.40f/100) * (idv + nonElectrical)))/100);
                        }

                        else {
                            mRTI = to2decimal((0.40f/100) * (idv + nonElectrical));
                        }
                    }

                    else if (age > 1){
                        mRTI = 0;
                    }
                }

                mNET_OD = Math.round(mOD_Total + mNIL_DEP + mRTI + mTowing_Charges - mNCB);

                /******************************************************TP SECTION*******************************************************************************/

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
                case 1: mTP = 2055; mBasic = to2decimal(age_zoneBValue[cc-1][getAgeGroup(age)] * ((float)idv + nonElectrical)/100);  break;
                case 2: mTP = 2863; mBasic = to2decimal(age_zoneBValue[cc-1][getAgeGroup(age)] * ((float)idv + nonElectrical)/100); break;
                case 3: mTP = 7890; mBasic = to2decimal(age_zoneBValue[cc-1][getAgeGroup(age)] * ((float)idv + nonElectrical)/100); break;
            }

                if((builtInLPG == 2) && (lpg_cng == 0)) {
                    mLPG_60 = 60;
                    mBuiltInLPG = (0.05f * to2decimal(nonElectrical + mBasic));
                }
                else if(lpg_cng != 0){
                    mBuiltInLPG = 0;
                }

                if((lpg_cng!=0) && (idv != 0) && (builtInLPG == 0)){
                    mLPG = to2decimal(0.04f * lpg_cng);
                    mLPG_60 = 60;
                }

                else if(builtInLPG != 0){
                    mLPG = 0;
                }

                if(tape!=0 && idv != 0)
                    mTape = to2decimal(0.04f * tape);

                if(underwriting_discount != 0) {
                    mUnderwritingDiscount = to2decimal((underwriting_discount* (mBasic + mTape + mLPG + mBuiltInLPG))/100); }

                else {
                    mUnderwritingDiscount = 0;
                }

                mBasicTotal = mBasic + mTape + mBuiltInLPG + mLPG - mUnderwritingDiscount;

                if(nil_dep == 2) {
                    if(ndp_discount != 0){
                        mNIL_DEP = to2decimal((ndp_discount * (getNILDEP(age) * (mBasic + mLPG + mTape + mLPG_60)))/100);
                    }

                    else{
                        mNIL_DEP = to2decimal(getNILDEP(age) * (mBasic + mLPG + mTape + mLPG_60));
                    }
                }

                if(towing!=0 && idv != 0)
                    mTowing_Charges = to2decimal(0.05f * towing);

                mTotal = mBasicTotal + mTowing_Charges - mNIL_DEP;

                mNCB = to2decimal((ncb * (mOD_Total + mNIL_DEP + mTowing_Charges))/100);
                //mNCB  = mNCB/100;

                mOD_Total = to2decimal(mBasic + mTape + mLPG + mBuiltInLPG - mUnderwritingDiscount);

                if(rti == 2){
                    if(age == 0){
                        if(other_add_on_discount != 0){
                            mRTI = to2decimal((other_add_on_discount * ((0.30f/100) * (idv + nonElectrical)))/100);}

                        else {
                            mRTI = to2decimal((0.30f/100) * (idv + nonElectrical));
                        }
                    }

                    else if (age == 1){
                        if(other_add_on_discount != 0){
                            mRTI = to2decimal((other_add_on_discount * ((0.40f/100) * (idv + nonElectrical)))/100);
                        }

                        else {
                            mRTI = to2decimal((0.40f/100) * (idv + nonElectrical));
                        }
                    }

                    else if (age > 1){
                        mRTI = 0;
                    }
                }

                mNET_OD = Math.round(mOD_Total + mNIL_DEP + mRTI + mTowing_Charges - mNCB);

                /******************************************************TP SECTION*******************************************************************************/

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
        }

    }

    private float to2decimal(float x){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float twoDigitsF = Float.valueOf(decimalFormat.format(x));
        return twoDigitsF;
    }

    public float getBasic(){ return to2decimal(mBasic);}

    public float getLPG(){return to2decimal(mLPG + mBuiltInLPG);}

    public float getTAPE(){return to2decimal(mTape);}

    public float getTowingCharges(){return to2decimal(mTowing_Charges);}

    public float getSubTotal(){return mSubTotal;}

    public float getNCB(){return to2decimal(mNCB);}

    public float getTotal(){return to2decimal(mTotal);}

    public float getNIL_DEP(){return to2decimal(mNIL_DEP);}

    public float getOD_Total(){return mOD_Total;}

    public float getNET_OD(){return mNET_OD;}

    public float getTP(){return mTP;}

    public float getCPA_100(){return mCPA_100;}

    public float getDriver(){return mDriver;}

    public float getLPG_60(){return mLPG_60;}

    public float getPassPa(){return mPassPa;}

    public float getBasicTotal() {return mBasicTotal;}

    public float getTP_Total(){return mTP_Total;}

    public float getTotal2(){return mTotal2;}

    public float getGST(){return mGST;}

    public float getNet_Payable(){return Math.round(mNet_Payable);}

    public float getUnderwriting_Discount(){return to2decimal(mUnderwritingDiscount);}

    public float getNDP_Discount(){return to2decimal(mNDPDiscount);}

    public float getOther_Add_On_Discount(){return to2decimal(mOtherAddOnDiscount);}

    public float getRTIi() {return to2decimal(mRTI);}

    private float getNILDEP(int age) {

        float a = 0;

        if(age == 0)
            a = 0.15f;
        else if(age>=1 && age<2)
            a = 0.25f;
        else if(age>=2 && age<5)
            a = 0.35f;
        else if(age>=5)
            a = 0f;

        return  a;
    }

    private int getAgeGroup(int age){
        int i=0;
        if(age<=5)
            i = 0;
        if (5<age && age<=10)
            i =  1;
        if (age>10)
            i =  2;
        return i;
    }


}
