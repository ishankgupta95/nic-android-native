package com.wolborg.insuranceprmcalculator.utils;

import java.text.DecimalFormat;

public class CalculateRickshaw7 {
    private final String TAG = "CalculateRickshaw7";
    /**
     * Entry Fields
     */
    private int age;
    private int zone;
    private int idv;
    private int lpg_cng;
    private int builtInLPG;
    private int ncb;
    private int imt;
    private int cpa;
    private int driver;
    private int passenger;
    /**
     * Result Fields
     */
    private float mBasic = 0;
    private float mLPG = 0;
    private float mIMT = 0;
    private float mSubTotal = 0;
    private float mTotal = 0;
    private float mNCB = 0;
    private float mNET_OD = 0;
    private float mOD_Total = 0;
    private float mBuiltInLPG = 0;
    private float mTP = 0;
    private float mOD = 0;
    private float mCPA_100 = 0;
    private float mDriver = 0;
    private float mLPG_60 = 0;
    private int mPassenger = 0;
    private float mTP_Total = 0;
    private float mTotal2 = 0;
    private float mGST = 0;
    private float mNet_Payable = 0;

    public CalculateRickshaw7(int age, int zone, int idv, int lpg_cng, int builtInLPG, int imt, int ncb, int passenger, int cpa, int driver) {

        this.age = age;
        this.zone = zone;
        this.idv = idv;
        this.lpg_cng = lpg_cng;
        this.builtInLPG = builtInLPG;
        switch(ncb){
            case 0: this.ncb = 0; break;
            case 1: this.ncb = 0; break;
            case 2: this.ncb = 20; break;
            case 3: this.ncb = 25; break;
            case 4: this.ncb = 35; break;
            case 5: this.ncb = 45; break;
            case 6: this.ncb = 50; break;
        }
        this.imt = imt;
        this.cpa = cpa;
        this.driver = driver;
        this.passenger = passenger;
    }

    public void calculateInsurance() {

        switch (zone){
            case 1:
                if(age < 5){
                    mBasic = to2decimal(0.01785f * (float)idv);
                }

                if(age >= 5 && age <= 7){
                    mBasic = to2decimal(0.0183f * (float)idv);
                }

                if(age > 7){
                    mBasic = to2decimal(0.01874f * (float)idv);
                }

                break;

            case 2:
                if(age < 5){
                    mBasic = to2decimal(0.01777f * (float)idv);
                }

                if(age >= 5 && age <= 7){
                    mBasic = to2decimal(0.01821f * (float)idv);
                }

                if(age > 7){
                    mBasic = to2decimal(0.01866f * (float)idv);
                }

                break;

            case 3:
                if(age < 5){
                    mBasic = to2decimal(0.01759f * (float)idv);
                }

                if(age >= 5 && age <= 7){
                    mBasic = to2decimal(0.01803f * (float)idv);
                }

                if(age > 7){
                    mBasic = to2decimal(0.01847f * (float)idv);
                }

                break;
        }

        mTP = 6763;

        switch(passenger){
            case 1: mPassenger = 7 * 1349; break;
            case 2: mPassenger = 8 * 1349; break;
            case 3: mPassenger = 9 * 1349; break;
            case 4: mPassenger = 10 * 1349; break;
            case 5: mPassenger = 11 * 1349; break;
            case 6: mPassenger = 12 * 1349; break;
            case 7: mPassenger = 13 * 1349; break;
            case 8: mPassenger = 14 * 1349; break;
            case 9: mPassenger = 15 * 1349; break;
            case 10: mPassenger = 16 * 1349; break;
            case 11: mPassenger = 17 * 1349; break;
        }

        if(builtInLPG == 2) {
            mLPG_60 = 60;
            if (idv != 0) {
                mLPG = to2decimal(0.05f * mBasic);
            }
        }

        if(lpg_cng != 0){
            mLPG_60 = 60;
            if(idv != 0) {
                mLPG = to2decimal(0.04f * lpg_cng);
            }
        }

        if(builtInLPG == 2){
            mLPG = to2decimal((float)(mBasic * 0.05));
            mLPG_60 = 60;
        }

        if(imt ==2)
            mIMT = to2decimal(0.15f * (mBasic + mLPG));

        mSubTotal = mBasic + mLPG + mIMT + mBuiltInLPG;

        mNCB = to2decimal(ncb * mSubTotal/100);

        mTotal = to2decimal(mSubTotal-mNCB);

        mOD_Total = Math.round(mSubTotal-mNCB);

        mOD = to2decimal((mBasic + mLPG + mIMT) - mNCB);

        mNET_OD = Math.round(mOD_Total);

                /*TP SECTION*******************************************************************************/

        if(driver==2)
            mDriver = 50;

        if(cpa==2)
            mCPA_100 = 295;

        mTP_Total = mTP + mCPA_100 + mDriver + mLPG_60 + mPassenger;

        mTotal2 = mTP_Total + mNET_OD;

        mGST = 2*Math.round(mTotal2 * 0.09f);

        mNet_Payable = mGST + mTotal2;

    }


    private float to2decimal(float x){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float twoDigitsF = Float.valueOf(decimalFormat.format(x));
        return twoDigitsF;
    }

    public float getBasic() {
        return mBasic;
    }

    public float getLPG() {
        return mLPG;
    }

    public float getIMT() {
        return mIMT;
    }

    public float getNCB() {
        return mNCB;
    }

    public float getOD() {
        return mOD;
    }

    public float getOD_Total() {
        return mTotal;
    }

    public float getTP() {
        return mTP;
    }

    public float getPass() {
        return mPassenger;
    }

    public float getDriver() {
        return mDriver;
    }

    public float getCPA_100() {
        return mCPA_100;
    }

    public float getLPG_60() {
        return mLPG_60;
    }

    public float getTP_Total() {
        return mTP_Total;
    }

    public float getTotal() {
        return mTotal2;
    }

    public float getGST() {
        return mGST;
    }

    public float getNet_Payable() {
        return mNet_Payable;
    }

    public String getIDV() {
        if (idv == 0) {
            return "IDV not entered";
        } else return Integer.toString(idv);
    }
}
