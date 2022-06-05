package com.wolborg.insuranceprmcalculator.utils;

import java.text.DecimalFormat;

public class CalculateThreeWheelPickup {

    private final String TAG = "Calculate3WheelPickup";

    private float mBasic = 0;
    private float mLPG = 0;
    private float mIMT = 0;
    private float mNCB = 0;
    private float mOD_Total = 0;
    private float mDiscount = 0;
    private float mOD = 0;
    private float mND_Cover;
    private float mNET_OD = 0;
    private float mTP = 0;
    private float mLPG_60 = 0;
    private float mCPA_100 = 0;
    private float mDriver = 0;
    private float mTP_Total = 0;
    private float mTotal = 0;
    private float mGST12 = 0;
    private float mGST18 = 0;
    private float mGST = 0;
    private float mNet_Payable = 0;
    private float mBuilt_In = 0;

    /**
     * Entry fields
     */

    private int age;
    private int zone;
    private int idv;
    private int lpg_cng;
    private int buitlInLPG;
    private int ncb;
    private int nil_dep;
    private int cpa;
    private float discount;
    private int imt;
    private int pvtpub;
    private int driver;

    private float[][] pub = {{1.664f,1.656f,1.640f},
            {1.704f,1.697f,1.681f},
            {1.747f,1.739f,1.722f}};

    private  float[][] pvt = {{1.165f,1.159f,1.148f},
            {1.194f,1.188f,1.177f},
            {1.223f,1.217f,1.205f}};

    public CalculateThreeWheelPickup(int age, int zone, int idv, int lpg_cng, int buitlInLPG, int ncb,
                          int nil_dep, int cpa, float discount, int imt, int pvtpub, int driver) {

        this.age = age;
        this.zone = zone;
        this.idv = idv;
        this.lpg_cng = lpg_cng;
        this.buitlInLPG = buitlInLPG;

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
        this.imt = imt;
        this.pvtpub = pvtpub;
        this.driver = driver;
    }

    public void calculateInsurance() {

        if(pvtpub == 1){
            mTP = 4492;
            if(zone == 1){
                if(age < 5){
                    mBasic = to2decimal((float)(idv * 1.664)/100);
                }

                if(age >= 5 && age <= 7){
                    mBasic = to2decimal((float)(idv * 1.704)/100);
                }

                if(age > 7){
                    mBasic = to2decimal((float)(idv * 1.747)/100);
                }
            }

            if(zone == 2){
                if(age < 5){
                    mBasic = to2decimal((float)(idv * 1.656)/100);
                }

                if(age >= 5 && age <= 7){
                    mBasic = to2decimal((float)(idv * 1.697)/100);
                }

                if(age > 7){
                    mBasic = to2decimal((float)(idv * 1.739)/100);
                }
            }

            if(zone == 3){
                if(age < 5){
                    mBasic = to2decimal((float)(idv * 1.640)/100);
                }

                if(age >= 5 && age <= 7){
                    mBasic = to2decimal((float)(idv * 1.681)/100);
                }

                if(age > 7){
                    mBasic = to2decimal((float)(idv * 1.722)/100);
                }
            }
        }

        if(pvtpub == 2){
            mTP = 3922;
            if(zone == 1){
                if(age < 5){
                    mBasic = to2decimal((float)(idv * 1.165)/100);
                }

                if(age >= 5 && age <= 7){
                    mBasic = to2decimal((float)(idv * 1.194)/100);
                }

                if(age > 7){
                    mBasic = to2decimal((float)(idv * 1.223)/100);
                }
            }

            if(zone == 2){
                if(age < 5){
                    mBasic = to2decimal((float)(idv * 1.159)/100);
                }

                if(age >= 5 && age <= 7){
                    mBasic = to2decimal((float)(idv * 1.188)/100);
                }

                if(age > 7){
                    mBasic = to2decimal((float)(idv * 1.217)/100);
                }
            }

            if(zone == 3){
                if(age < 5){
                    mBasic = to2decimal((float)(idv * 1.148)/100);
                }

                if(age >= 5 && age <= 7){
                    mBasic = to2decimal((float)(idv * 1.177)/100);
                }

                if(age > 7){
                    mBasic = to2decimal((float)(idv * 1.205)/100);
                }
            }
        }

        if(buitlInLPG == 2) {
            mLPG_60 = 60;
            mLPG = 0;
            if (idv != 0) {
                mBuilt_In = to2decimal(0.05f * mBasic);
            }
        }

        if(lpg_cng != 0){
            mLPG_60 = 60;
            if(idv != 0) {
                mLPG = to2decimal(0.04f * lpg_cng);
            }
        }

        if(buitlInLPG == 2){
            mBuilt_In = to2decimal((float)(mBasic * 0.05));
            mLPG = 0;
            mLPG_60 = 60;
        }

        if(imt == 2)
            mIMT = to2decimal((15 * (mBasic + mLPG))/100);

        if(nil_dep == 2)
            mND_Cover = to2decimal((getNildep(age) * (float)idv)/100);

        mNCB = to2decimal((ncb * (mBasic + mBuilt_In + mLPG + mIMT))/100);

        mOD = to2decimal((mBasic + mLPG + mIMT) - mNCB);

        mDiscount = to2decimal((discount * (mOD))/100);

        mOD_Total = Math.round(mOD + mND_Cover - mDiscount);

        if(driver==2)
            mDriver = 50;

        if(cpa == 2)
            mCPA_100 = 295;

        mTP_Total = Math.round(mTP + mCPA_100 + mLPG_60 + mDriver);

        mTotal = mTP_Total + mOD_Total;

//        mGST = 2*Math.round(mTotal * 0.09f);

        mGST12 = Math.round(mTP * 0.12f);

        mGST18 = 2*Math.round((mTotal - mTP) * 0.09f);

        mGST = mGST12 + mGST18;

        mNet_Payable = mGST + mTotal;
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

    public float getIMT() {
        return to2decimal(mIMT);
    }

    public float getNCB() {
        return to2decimal(mNCB);
    }

    public float getOD() {
        return mOD;
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

    public float getTP() {
        return mTP;
    }

    public float getLPG_60() {
        return mLPG_60;
    }

    public float getCPA_100() {
        return mCPA_100;
    }

    public float getDriver() {
        return mDriver;
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

    public String getIDV() {
        if (idv == 0) {
            return "IDV not entered";
        } else return Integer.toString(idv);
    }
}
