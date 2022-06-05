package com.ishank.insuranceprmcalculator.utils;

import java.text.DecimalFormat;

/**
 * Created by ishank on 31-Dec-17.
 */

public class CalculateFourWhPassMoreSix {


    private final String TAG = "4WhPassMore6";

    private float mBasic = 0;
    private float mOD = 0;
    private float mLPG = 0;
    private float mIMT = 0;
    private float mNCB = 0;
    private float mOD_Total = 0;
    private float mDiscount = 0;
    private float mND_Cover;
    private float mNET_OD = 0;
    private float mTP = 0;
    private float mLPG_60 = 0;
    private float mCPA_100 = 0;
    private float mTP_Total = 0;
    private float mTotal = 0;
    private float mGST = 0;
    private float mNet_Payable = 0;
    private float mBuilt_In = 0;
    private int mPassenger = 0;
    private float mDR_CLS = 0;

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
    private int drcls;
    private int numberOfPassengers;

    public CalculateFourWhPassMoreSix(int age, int zone, int idv, int lpg_cng, int buitlInLPG, int ncb,
                                     int nil_dep, int cpa, float discount, int imt, int drcls, int numberOfPassengers) {

        this.age = age;
        this.zone = zone;
        this.idv = idv;
        this.lpg_cng = lpg_cng;
        this.buitlInLPG = buitlInLPG;
        this.drcls = drcls;

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
        this.numberOfPassengers = numberOfPassengers;
    }

    public void calculateInsurance() {

                if(zone == 1 && idv != 0){
                    if(age < 5){
                        if(numberOfPassengers < 18){
                            mBasic = 350;
                            mOD = to2decimal(0.0168f * (float)idv);
                        }
                        if(numberOfPassengers >= 18 && numberOfPassengers <=36){
                            mBasic = 450;
                            mOD = to2decimal(0.0168f * (float)idv);
                        }
                        if(numberOfPassengers >= 37 && numberOfPassengers <=60){
                            mBasic = 550;
                            mOD = to2decimal(0.0168f * (float)idv);
                        }
                        if(numberOfPassengers > 60){
                            mBasic = 680;
                            mOD = to2decimal(0.0168f * (float)idv);
                        }
                    }

                    if(age >= 5 && age <= 7){
                        if(numberOfPassengers < 18){
                            mBasic = 350;
                            mOD = to2decimal(0.01722f * (float)idv);
                        }
                        if(numberOfPassengers >= 18 && numberOfPassengers <=36){
                            mBasic = 450;
                            mOD = to2decimal(0.01722f * (float)idv);
                        }
                        if(numberOfPassengers >= 37 && numberOfPassengers <=60){
                            mBasic = 550;
                            mOD = to2decimal(0.01722f * (float)idv);
                        }
                        if(numberOfPassengers > 60){
                            mBasic = 680;
                            mOD = to2decimal(0.01722f * (float)idv);
                        }
                    }

                    if(age > 7){
                        if(numberOfPassengers < 18){
                            mBasic = 350;
                            mOD = to2decimal(0.01764f * (float)idv);
                        }
                        if(numberOfPassengers >= 18 && numberOfPassengers <=36){
                            mBasic = 450;
                            mOD = to2decimal(0.01764f * (float)idv);
                        }
                        if(numberOfPassengers >= 37 && numberOfPassengers <=60){
                            mBasic = 550;
                            mOD = to2decimal(0.01764f * (float)idv);
                        }
                        if(numberOfPassengers > 60){
                            mBasic = 680;
                            mOD = to2decimal(0.01764f * (float)idv);
                        }
                    }
                }

                if(zone == 2 && idv != 0){
                    if(age < 5){
                        if(numberOfPassengers < 18){
                            mBasic = 350;
                            mOD = to2decimal(0.01672f * (float)idv);
                        }
                        if(numberOfPassengers >= 18 && numberOfPassengers <=36){
                            mBasic = 450;
                            mOD = to2decimal(0.01672f * (float)idv);
                        }
                        if(numberOfPassengers >= 37 && numberOfPassengers <=60){
                            mBasic = 550;
                            mOD = to2decimal(0.01672f * (float)idv);
                        }
                        if(numberOfPassengers > 60){
                            mBasic = 680;
                            mOD = to2decimal(0.01672f * (float)idv);
                        }
                    }

                    if(age >= 5 && age <= 7){
                        if(numberOfPassengers < 18){
                            mBasic = 350;
                            mOD = to2decimal(0.01714f * (float)idv);
                        }
                        if(numberOfPassengers >= 18 && numberOfPassengers <=36){
                            mBasic = 450;
                            mOD = to2decimal(0.01714f * (float)idv);
                        }
                        if(numberOfPassengers >= 37 && numberOfPassengers <=60){
                            mBasic = 550;
                            mOD = to2decimal(0.01714f * (float)idv);
                        }
                        if(numberOfPassengers > 60){
                            mBasic = 680;
                            mOD = to2decimal(0.01714f * (float)idv);
                        }
                    }

                    if(age > 7){
                        if(numberOfPassengers < 18){
                            mBasic = 350;
                            mOD = to2decimal(0.01756f * (float)idv);
                        }
                        if(numberOfPassengers >= 18 && numberOfPassengers <=36){
                            mBasic = 450;
                            mOD = to2decimal(0.01756f * (float)idv);
                        }
                        if(numberOfPassengers >= 37 && numberOfPassengers <=60){
                            mBasic = 550;
                            mOD = to2decimal(0.01756f * (float)idv);
                        }
                        if(numberOfPassengers > 60){
                            mBasic = 680;
                            mOD = to2decimal(0.01756f * (float)idv);
                        }
                    }
                }

                if(zone == 3 && idv != 0){
                    if(age < 5){
                        if(numberOfPassengers < 18){
                            mBasic = 350;
                            mOD = to2decimal(0.01656f * (float)idv);
                        }
                        if(numberOfPassengers >= 18 && numberOfPassengers <=36){
                            mBasic = 450;
                            mOD = to2decimal(0.01656f * (float)idv);
                        }
                        if(numberOfPassengers >= 37 && numberOfPassengers <=60){
                            mBasic = 550;
                            mOD = to2decimal(0.01656f * (float)idv);
                        }
                        if(numberOfPassengers > 60){
                            mBasic = 680;
                            mOD = to2decimal(0.01656f * (float)idv);
                        }
                    }

                    if(age >= 5 && age <= 7){
                        if(numberOfPassengers < 18){
                            mBasic = 350;
                            mOD = to2decimal(0.01697f * (float)idv);
                        }
                        if(numberOfPassengers >= 18 && numberOfPassengers <=36){
                            mBasic = 450;
                            mOD = to2decimal(0.01697f * (float)idv);
                        }
                        if(numberOfPassengers >= 37 && numberOfPassengers <=60){
                            mBasic = 550;
                            mOD = to2decimal(0.01697f * (float)idv);
                        }
                        if(numberOfPassengers > 60){
                            mBasic = 680;
                            mOD = to2decimal(0.01697f * (float)idv);
                        }
                    }

                    if(age > 7){
                        if(numberOfPassengers < 18){
                            mBasic = 350;
                            mOD = to2decimal(0.01739f * (float)idv);
                        }
                        if(numberOfPassengers >= 18 && numberOfPassengers <=36){
                            mBasic = 450;
                            mOD = to2decimal(0.01739f * (float)idv);
                        }
                        if(numberOfPassengers >= 37 && numberOfPassengers <=60){
                            mBasic = 550;
                            mOD = to2decimal(0.01739f * (float)idv);
                        }
                        if(numberOfPassengers > 60){
                            mBasic = 680;
                            mOD = to2decimal(0.01739f * (float)idv);
                        }
                    }
                }

                if(lpg_cng != 0){
                    mLPG_60 = 60;
                    if(idv != 0) {
                        mLPG = to2decimal(0.04f * lpg_cng);
                    }
                }

                if(buitlInLPG == 2){
                    mLPG = 0;
                    mLPG_60 = 60;
                    mBuilt_In = to2decimal((float)((mBasic + mOD) * 0.05));
                }

                mTP = 14343;
                mPassenger = 877 * numberOfPassengers;

                if(imt == 2)
                    mIMT = to2decimal((15 * (mBasic+mLPG + mOD))/100);

                mNCB = to2decimal((ncb * (mBasic + mOD + mBuilt_In + mLPG + mIMT))/100);

                mOD_Total = mBasic + mLPG + mIMT + mBuilt_In + mOD - mNCB;

                mDiscount = to2decimal((discount * mOD_Total)/100);

                if(nil_dep == 2)
                    mND_Cover = to2decimal((getNildep(age) * (float)idv)/100);

                mNET_OD = Math.round(mOD_Total - mDiscount + mND_Cover) ;

                if(cpa == 2)
                    mCPA_100 = 295;

                mDR_CLS = 50 * drcls;

                mTP_Total = Math.round(mTP + mDR_CLS + mCPA_100 + mLPG_60 + mPassenger);

                mTotal = mTP_Total + mNET_OD ;

                mGST = 2*Math.round(mTotal * 0.09f);

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

    public float getOD() {
        return mOD;
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

    public float getPass() {
        return mPassenger;
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
