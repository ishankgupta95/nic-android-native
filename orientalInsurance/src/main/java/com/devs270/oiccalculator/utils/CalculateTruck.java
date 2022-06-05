package com.devs270.oiccalculator.utils;

import java.text.DecimalFormat;

/**
 * Created by Abhishek on 09-10-2017.
 */

public class CalculateTruck {

    private final String TAG = "CalculatePrivateCar";

    /**
     * Output Fields
     */

    /**
     * Result Fields
     */
    private float mBasic = 0;
    private float mLPG = 0;
    private float mTowing_Charges = 0;
    private float mIMT = 0;
    private float mNCB = 0;
    private float mOD_Total = 0;
    private float mDiscount = 0;
    private float mND_Cover;
    private float mNET_OD = 0;
    private float mTP = 0;
    private float mDR_CLS = 0;
    private float mLPG_60 = 0;
    private float mCPA_100 = 0;
    private float mNFPP = 0;
    private float mTP_Total = 0;
    private float mTotal = 0;
    private float mGST = 0;
    private float mNet_Payable = 0;
    private float mBuilt_In = 0;

    /**
     * Entry fields
     */
    private int age;
    private int ageTemp;
    private int zone;
    private int idv;
    private int lpg_cng;
    private int buitlInLPG;
    private int towing;
    private int ncb;
    private int nil_dep;
    private int cpa;
    private float discount;
    private int gvw;
    private double gvw_temp;
    private int imt;
    private int drcls;
    private int nfpp;
    private int pvtpub;

    private float[][] pub = {{1.726f,1.743f,1.751f},
                                {1.77f,1.787f,1.795f},
                                    {1.812f,1.83f,1.839f}};

    private  float[][] pvt = {{1.208f,1.22f,1.226f},
                                {1.239f,1.251f,1.257f},
                                    {1.268f,1.281f,1.287f}};


    public CalculateTruck(int age, int zone, int idv, int lpg_cng, int buitlInLPG, int towing, int ncb,
        int nil_dep, int cpa, float discount, int gvw, int imt, int drcls, int nfpp, int pvtpub) {

        this.age = age;

            if(age<5)
                ageTemp = 0;
            else if (age>=5 && age<7)
                ageTemp = 1;
            else if (age>=7)
                ageTemp = 2;

        this.zone = zone;
        this.idv = idv;
        this.lpg_cng = lpg_cng;
        this.buitlInLPG = buitlInLPG;
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
        this.discount = discount;
        this.gvw = gvw;

        this.imt = imt;
        this.drcls = drcls;
        this.nfpp = nfpp;
        this.pvtpub = pvtpub;
    }

    public void calculateInsurance() {

        switch(pvtpub){
            case 1 :
                {
                    if(gvw > 12000 && idv!=0){
                    gvw_temp = gvw-12000;
                    gvw_temp = gvw_temp/100;
                    mBasic = to2decimal((float)(idv * (pub[ageTemp][3 - zone] / 100) + (Math.ceil(gvw_temp) * 27)));
                    }

                    else{
                        mBasic = to2decimal(idv * (pub[ageTemp][3 - zone] / 100));
                    }

                    if(lpg_cng!=0 && idv != 0){
                        mLPG = to2decimal((4f * lpg_cng)/100);
                        mLPG_60 = 60;
                    }

                    if(buitlInLPG == 2){
                        mLPG_60 = 60;
                        mBuilt_In =  to2decimal((float)(mBasic * 0.05));}

                    if(towing<10000 && idv != 0) {
                        mTowing_Charges = to2decimal((5f * towing) / 100);
                    }
                    else if(idv != 0){
                        mTowing_Charges = to2decimal((7.5f * towing) / 100);
                    }

                    if(imt == 2)
                        mIMT = to2decimal((15 * (mBasic+mLPG))/100);

                    mNCB = to2decimal((ncb * (mBasic + mLPG + mIMT + mTowing_Charges))/100);

                    mOD_Total = (mBasic + mLPG + mIMT + mTowing_Charges + mBuilt_In) - mNCB;

                    mDiscount = to2decimal((discount * mOD_Total)/100);

                    if(nil_dep == 2)
                    mND_Cover = to2decimal((getNildep(age) * (float)idv)/100);

                    if(age >4){
                        mND_Cover = 0;
                    }

                    mNET_OD = Math.round(mOD_Total - mDiscount + mND_Cover) ;

                    mTP = getTP(0,gvw);

                    mDR_CLS = (50 * drcls);

                    if(cpa == 2)
                        mCPA_100 = 100;

                    mNFPP = (75 * nfpp);

                    mTP_Total = Math.round(mTP + mDR_CLS + mCPA_100 + mLPG_60 + mNFPP);

                    mTotal = mTP_Total + mNET_OD ;

                    mGST = 2*Math.round(mTotal * 0.09f);

                    mNet_Payable = mGST + mTotal;
                }
                break;
            case 2 :
            {
                if(gvw > 12000 && idv!=0) {
                        gvw_temp = gvw - 12000;
                        gvw_temp = gvw_temp / 100;
                        mBasic = to2decimal((float) (idv * (pvt[ageTemp][3 - zone] / 100) + (Math.ceil(gvw_temp) * 27)));

                }

                else{
                    mBasic =  to2decimal(idv * (pvt[ageTemp][3 - zone] / 100));
                }

                if(lpg_cng!=0 && idv != 0){
                    mLPG = to2decimal((4f * lpg_cng)/100);
                    mLPG_60 = 60;
                }

                if(buitlInLPG == 2){
                    mLPG_60 = 60;
                    mBuilt_In =  to2decimal((float)(mBasic * 0.05));}

                if(towing<10000 && idv != 0) {
                    mTowing_Charges = to2decimal((5f * towing) / 100);
                }
                else if(idv != 0){
                    mTowing_Charges = to2decimal((7.5f * towing) / 100);
                }

                if(imt == 2)
                    mIMT = to2decimal((15 * (mBasic+mLPG))/100);

                mNCB = to2decimal((ncb * (mBasic + mLPG + mIMT + mTowing_Charges))/100);

                mOD_Total = (mBasic + mLPG + mIMT + mTowing_Charges + mBuilt_In) - mNCB;

                mDiscount = to2decimal((discount * mOD_Total)/100);

                if(nil_dep == 2)
                mND_Cover = to2decimal((getNildep(age) * (float)idv)/100);

                if(age >4){
                    mND_Cover = 0;
                }

                mNET_OD = Math.round(mOD_Total - mDiscount + mND_Cover);

                mTP = getTP(1,gvw);

                mDR_CLS = (50 * drcls);

                if(cpa == 2)
                    mCPA_100 = 100;

                mNFPP = (75 * nfpp);

                mTP_Total = Math.round(mTP + mDR_CLS + mCPA_100 + mLPG_60 +mNFPP);

                mTotal = mTP_Total + mNET_OD ;

                mGST = 2*Math.round(mTotal * 0.09f);

                mNet_Payable = mGST + mTotal;
            }
                break;
        }
    }

    private float getNildep(int age) {

        float f = 0f;
        if(age == 0)
            f =  0.29f;
        else if(age == 1)
            f = 0.31f;
        else if (age == 2)
            f = 0.36f;
        else if(age == 3)
            f = 0.43f;
        else if(age>= 4)
            f =  0.53f;
        return f;
    }

    private float getTP(int i, int gvw) {
        float x = 0;

        if(gvw<=7500)
            x = i==0 ? 14390 : 7938;
        else if(gvw>7500 && gvw<= 12000)
            x = i==0 ? 19667 : 14330;
        else if (gvw>12000 && gvw<=20000)
            x = i==0 ? 28899 : 9871;
        else if (gvw>20000 && gvw<= 40000)
            x = i==0 ? 31626 : 14805;
        else if (gvw>40000)
            x = i==0 ? 33024 : 21318;

        return x;
    }

    private float to2decimal(float x){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float twoDigitsF = Float.valueOf(decimalFormat.format(x));
        return twoDigitsF;
    }

    public float getBasic() {
        return to2decimal(mBasic);
    }

    public float getLPG() {
        return to2decimal(mLPG);
    }

    public float getTowing_Charges() {
        return to2decimal(mTowing_Charges);
    }

    public float getIMT() {
        return to2decimal(mIMT);
    }

    public float getNCB() {
        return to2decimal(mNCB);
    }

    public float getOD_Total() {
        return mOD_Total;
    }

    public float getDiscount() {
        return mDiscount;
    }

    public float getND_Cover() {
        return mND_Cover;
    }

    public float getNET_OD() {
        return mNET_OD;
    }

    public float getTP() {
        return mTP;
    }

    public float getDR_CLS() {
        return mDR_CLS;
    }

    public float getLPG_60() {
        return mLPG_60;
    }

    public float getCPA_100() {
        return mCPA_100;
    }

    public float getNFPP() {
        return mNFPP;
    }

    public float getTP_Total() {
        return mTP_Total;
    }

    public float getTotal() {
        return mTotal;
    }

    public float getGST() {
        return mGST;
    }

    public float getNet_Payable() {
        return mNet_Payable;
    }

    public float getBuilt_IN() {
        return mBuilt_In;
    }
}
