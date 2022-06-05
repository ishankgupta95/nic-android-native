package com.ishank.insuranceprmcalculator.utils;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

public class CalculateTaxi {

    private final String TAG = "CalculateTaxi";
    /**
     * Entry Fields
     */
    private int age;
    private int zone;
    private int cc;
    private int idv;
    private int lpg_cng;
    private int builtInLPG;
    private int nil_dep;
    private int ncb;
    private int imt;
    private int pass;
    private int cpa;
    private int driver;
    private float discount;

    /**
     * Result Fields
     */
    private float mBasic = 0;
    private float mLPG = 0;
    private float mTotal = 0;
    private float mNCB = 0;
    private float mDiscount = 0;
    private float mOD = 0;
    private float mOD_Total = 0;
    private float mTP = 0;
    private float mCPA_100 = 0;
    private float mDriver = 0;
    private float mLPG_60 = 0;
    private float mTP_Total = 0;
    private float mGST = 0;
    private float mNet_Payable = 0;
    private float mBuilt_In = 0;
    private float mIMT = 0;
    private float mND_Cover = 0;
    private float mPass = 0;
    private float mNIL_DEP = 0;
    private float mCPA_50 = 0;
    private float mNET_OD = 0;


    public static float age_zoneAValue[][] = {{ 3.284f, 3.448f, 3.612f},
            { 3.366f, 3.534f, 3.703f},
            { 3.448f, 3.62f, 3.793f}};

    public static float age_zoneBValue[][] = {{ 3.191f, 3.351f, 3.51f},
            { 3.271f, 3.435f, 3.598f},
            { 3.351f, 3.519f, 3.686f}};


    public CalculateTaxi(@NonNull int age, @NonNull int zone, int idv, @NonNull int cc, int lpg_cng, int builtInLPG, int imt, @NonNull int pass, int ncb, int nil_dep, int cpa, int driver, float discount) {

        this.age = age;
        this.zone = zone;
        this.cc = cc;
        this.idv = idv;
        this.lpg_cng = lpg_cng;
        this.builtInLPG = builtInLPG;
        this.nil_dep = nil_dep;
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
        this.pass = pass;
        this.driver = driver;
        this.cpa = cpa;
        this.discount = discount;
    }


    public void calculateInsurance() {
        if(zone == 1){
            switch (cc){
                case 1: mTP = 6040;
                    mBasic = age_zoneAValue[getAgeGroup(age)][0]*idv;
                    mBasic /= 100;

                    if (pass == 1){
                        mPass = 3 * 1162;}
                    if (pass == 2){
                        mPass = 4 * 1162;}
                    if (pass == 3){
                        mPass = 5 * 1162;}
                    if (pass == 4){
                        mPass = 6 * 1162;}

                    if(lpg_cng!=0 && idv != 0){
                        mLPG = to2decimal(0.04f * lpg_cng);
                        mLPG_60 = 60;
                    }

                    if(builtInLPG == 2){
                        mLPG = 0;
                        mLPG_60 = 60;
                        mBuilt_In =  to2decimal((float)(mBasic * 0.05));
                    }

                    if(imt ==2)
                        mIMT = 0.15f * (mBasic + mLPG);

                    mNCB = (mBasic + mLPG + mBuilt_In + mIMT) * ncb;
                    mNCB /= 100;

                    mOD = to2decimal((mBasic + mLPG + mBuilt_In +mIMT) - mNCB);

                    mDiscount = to2decimal(mOD * (discount/100));

                    if(nil_dep == 2)
                        mND_Cover = (idv * getNilDep(age)/100)/100;

                    mOD_Total = Math.round(mOD + mND_Cover - mDiscount);

                    if(cpa == 2)
                        mCPA_50 = 295;

                    if(driver == 2)
                        mDriver = 50;


                    mTP_Total = mTP + mPass + mCPA_50 + mDriver + mLPG_60;

                    mTotal = mTP_Total + mOD_Total;

                    mGST = 2*Math.round(mTotal * 0.09f);

                    mNet_Payable = Math.round(mGST + mTotal);

                    break;

                case 2: mTP = 7940;
                    mBasic = age_zoneAValue[getAgeGroup(age)][1]*idv;
                    mBasic /= 100;

                    if (pass == 1){
                        mPass = 3 * 978;}
                    if (pass == 2){
                        mPass = 4 * 978;}
                    if (pass == 3){
                        mPass = 5 * 978;}
                    if (pass == 4){
                        mPass = 6 * 978;}

                    if(lpg_cng!=0 && idv != 0){
                        mLPG = to2decimal(0.04f * lpg_cng);
                        mLPG_60 = 60;
                    }

                    if(builtInLPG == 2){
                        mLPG = 0;
                        mLPG_60 = 60;
                        mBuilt_In =  to2decimal((float)(mBasic * 0.05));
                    }

                    if(imt ==2)
                        mIMT = 0.15f * (mBasic + mLPG);

                    mNCB = (mBasic + mLPG + mBuilt_In +mIMT) * ncb;
                    mNCB /= 100;

                    mOD = to2decimal((mBasic + mLPG + mBuilt_In +mIMT) - mNCB);

                    mDiscount = to2decimal(mOD * (discount/100));

                    if(nil_dep == 2)
                        mND_Cover = (idv * getNilDep(age)/100)/100;

                    mOD_Total = Math.round(mOD + mND_Cover - mDiscount);

                    if(cpa == 2)
                        mCPA_50 = 295;

                    if(driver == 2)
                        mDriver = 50;

                    mTP_Total = mTP + mPass + mCPA_50 + mDriver + mLPG_60;

                    mTotal = mTP_Total + mOD_Total;

                    mGST = 2*Math.round(mTotal * 0.09f);

                    mNet_Payable = Math.round(mGST + mTotal);

                    break;

                case 3:
                    mTP = 10523;
                    mBasic = age_zoneAValue[getAgeGroup(age)][2]*idv;
                    mBasic /= 100;

                    if (pass == 1){
                        mPass = 3 * 1117;}
                    if (pass == 2){
                        mPass = 4 * 1117;}
                    if (pass == 3){
                        mPass = 5 * 1117;}
                    if (pass == 4){
                        mPass = 6 * 1117;}

                    if(lpg_cng!=0 && idv != 0){
                        mLPG = to2decimal(0.04f * lpg_cng);
                        mLPG_60 = 60;
                    }

                    if(builtInLPG == 2){
                        mLPG = 0;
                        mLPG_60 = 60;
                        mBuilt_In =  to2decimal((float)(mBasic * 0.05));
                    }

                    if(imt ==2)
                        mIMT = 0.15f * (mBasic + mLPG);

                    mNCB = (mBasic + mLPG + mBuilt_In +mIMT) * ncb;
                    mNCB /= 100;

                    mOD = to2decimal((mBasic + mLPG + mBuilt_In +mIMT) - mNCB);

                    mDiscount = to2decimal(mOD * (discount/100));

                    if(nil_dep == 2)
                        mND_Cover = (idv * getNilDep(age)/100)/100;

                    mOD_Total = Math.round(mOD + mND_Cover - mDiscount);

                    if(cpa == 2)
                        mCPA_50 = 295;

                    if(driver == 2)
                        mDriver = 50;

                    mTP_Total = mTP + mPass + mCPA_50 + mDriver + mLPG_60;

                    mTotal = mTP_Total + mOD_Total;

                    mGST = 2*Math.round(mTotal * 0.09f);

                    mNet_Payable = Math.round(mGST + mTotal);

                    break;
            }
        }
        else if(zone == 2){

            switch (cc){
                case 1: mTP = 6040;
                    mBasic = age_zoneBValue[getAgeGroup(age)][0]*idv;
                    mBasic /= 100;

                    if (pass == 1){
                        mPass = 3 * 1162;}
                    if (pass == 2){
                        mPass = 4 * 1162;}
                    if (pass == 3){
                        mPass = 5 * 1162;}
                    if (pass == 4){
                        mPass = 6 * 1162;}

                    if(lpg_cng!=0 && idv != 0){
                        mLPG = to2decimal(0.04f * lpg_cng);
                        mLPG_60 = 60;
                    }

                    if(builtInLPG == 2){
                        mLPG = 0;
                        mLPG_60 = 60;
                        mBuilt_In =  to2decimal((float)(mBasic * 0.05));
                    }

                    if(imt ==2)
                        mIMT = 0.15f * (mBasic + mLPG);

                    mNCB = (mBasic + mLPG + mBuilt_In +mIMT) * ncb;
                    mNCB /= 100;

                    mOD = to2decimal((mBasic + mLPG + mBuilt_In +mIMT) - mNCB);

                    mDiscount = to2decimal(mOD * (discount/100));

                    if(nil_dep == 2)
                        mND_Cover = (idv * getNilDep(age)/100)/100;

                    mOD_Total = Math.round(mOD + mND_Cover - mDiscount);

                    if(cpa == 2)
                        mCPA_50 = 295;

                    if(driver == 2)
                        mDriver = 50;

                    mTP_Total = mTP + mPass + mCPA_50 + mDriver + mLPG_60;

                    mTotal = mTP_Total + mOD_Total;

                    mGST = 2*Math.round(mTotal * 0.09f);

                    mNet_Payable = Math.round(mGST + mTotal);

                    break;

                case 2: mTP = 7940;
                    mBasic = age_zoneBValue[getAgeGroup(age)][1]*idv;
                    mBasic /= 100;

                    if (pass == 1){
                        mPass = 3 * 978;}
                    if (pass == 2){
                        mPass = 4 * 978;}
                    if (pass == 3){
                        mPass = 5 * 978;}
                    if (pass == 4){
                        mPass = 6 * 978;}

                    if(lpg_cng!=0 && idv != 0){
                        mLPG = to2decimal(0.04f * lpg_cng);
                        mLPG_60 = 60;
                    }

                    if(builtInLPG == 2){
                        mLPG = 0;
                        mLPG_60 = 60;
                        mBuilt_In =  to2decimal((float)(mBasic * 0.05));
                    }

                    if(imt ==2)
                        mIMT = 0.15f * (mBasic + mLPG);

                    mNCB = (mBasic + mLPG + mBuilt_In +mIMT) * ncb;
                    mNCB /= 100;

                    mOD = to2decimal((mBasic + mLPG + mBuilt_In +mIMT) - mNCB);

                    mDiscount = to2decimal(mOD * (discount/100));

                    if(nil_dep == 2)
                        mND_Cover = (idv * getNilDep(age)/100)/100;

                    mOD_Total = Math.round(mOD + mND_Cover - mDiscount);

                    if(cpa == 2)
                        mCPA_50 = 295;

                    if(driver == 2)
                        mDriver = 50;

                    mTP_Total = mTP + mPass + mCPA_50 + mDriver + mLPG_60;

                    mTotal = mTP_Total + mOD_Total;

                    mGST = 2*Math.round(mTotal * 0.09f);

                    mNet_Payable = Math.round(mGST + mTotal);

                    break;

                case 3:
                    mTP = 10523;
                    mBasic = age_zoneBValue[getAgeGroup(age)][2]*idv;
                    mBasic /= 100;

                    if (pass == 1){
                        mPass = 3 * 1117;}
                    if (pass == 2){
                        mPass = 4 * 1117;}
                    if (pass == 3){
                        mPass = 5 * 1117;}
                    if (pass == 4){
                        mPass = 6 * 1117;}

                    if(lpg_cng!=0 && idv != 0){
                        mLPG = to2decimal(0.04f * lpg_cng);
                        mLPG_60 = 60;
                    }

                    if(builtInLPG == 2){
                        mLPG = 0;
                        mLPG_60 = 60;
                        mBuilt_In =  to2decimal((float)(mBasic * 0.05));
                    }

                    if(imt ==2)
                        mIMT = 0.15f * (mBasic + mLPG);

                    mNCB = (mBasic + mLPG + mBuilt_In +mIMT) * ncb;
                    mNCB /= 100;

                    mOD = to2decimal((mBasic + mLPG + mBuilt_In +mIMT) - mNCB);

                    mDiscount = to2decimal(mOD * (discount/100));

                    if(nil_dep == 2)
                        mND_Cover = (idv * getNilDep(age)/100)/100;

                    mOD_Total = Math.round(mOD + mND_Cover - mDiscount);

                    if(cpa == 2)
                        mCPA_50 = 295;

                    if(driver == 2)
                        mDriver = 50;

                    mTP_Total = mTP + mPass + mCPA_50 + mDriver + mLPG_60;

                    mTotal = mTP_Total + mOD_Total;

                    mGST = 2*Math.round(mTotal * 0.09f);

                    mNet_Payable = Math.round(mGST + mTotal);

                    break;
            }

        }
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

    private int getNilDep(int age) {

        int i = 0;

        if(age == 0)
            i = 29;
        else if (age == 1)
            i =  31;
        else if(age == 2)
            i = 36;
        else if (age == 3)
            i = 43;
        else if (age == 4)
            i = 53;
        else i = 0;

        return i;
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

    public float getBuilt_IN() {
        return mBuilt_In;
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

    public float getDiscount() {
        return mDiscount;
    }

    public float getND_Cover() {
        return mND_Cover;
    }

    public float getOD_Total() {
        return mOD_Total;
    }

    public float getTP() {
        return mTP;
    }

    public float getDriver() {
        return mDriver;
    }

    public float getPass() {
        return mPass;
    }

    public float getCPA_100() {
        return mCPA_50;
    }

    public float getLPG_60() {
        return mLPG_60;
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

    public String getIDV() {
        if (idv == 0) {
            return "IDV not entered";
        } else return Integer.toString(idv);
    }
}