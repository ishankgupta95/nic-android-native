package com.wolborg.insuranceprmcalculator.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.text.DecimalFormat;

public class CalculateTwoWheeler {

    private final String TAG = "CalculateTwoWheeler";
    /**
     * Entry Fields
     */
    private int age;
    private int zone;
    private int cc;
    private int idv;
    private int ncb;
    private int nil_dep;
    private int cpa;
    private int pa_to_pass ;
    private int pa_amount;
    private float discount;


    /**
     * Result Fields
     */
    private float mBasic = 0;
    private float mNCB = 0;
    private float mOD = 0;
    private float mDiscount = 0;
    private float mNIL_DEP = 0;
    private float mNET_OD = 0;
    private float mTP = 0;
    private float mCPA_50 = 0;
    private float mPassPa;
    private float mTP_Total = 0;
    private float mTotal = 0;
    private float mGST = 0;
    private float mNet_Payable = 0;

    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser myparser;


    public static float age_zoneBValue[][] = {{ 1.341f, 1.408f, 1.475f},
                                        { 1.408f, 1.478f, 1.549f},
                                        { 1.442f, 1.514f, 1.586f}};

    public static float age_zoneAValue[][] = {{ 1.366f, 1.434f, 1.503f},
                                        { 1.434f, 1.506f, 1.578f},
                                        { 1.469f, 1.542f, 1.616f}};


    public CalculateTwoWheeler(@NonNull int age, @NonNull int zone, @NonNull int cc, @Nullable int idv, int ncb, int nil_dep, @NonNull int cpa,
                               int pa_to_pass, int pa_amount, float discount){

        this.age = age;
        this.zone = zone;
        this.cc = cc;
        this.idv = idv;
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
        this.pa_to_pass = pa_to_pass;
        this.pa_amount = pa_amount;
        this.discount = discount;

    }

    public void calculateInsurance(){
        if(zone == 1){
            switch (cc){
                case 1: mTP = 569;
                    mBasic = age_zoneAValue[getAgeGroup(age)][0]*idv;
                    mBasic /= 100;
                    mNCB = mBasic * ncb;
                    mNCB /= 100;
                    mOD = mBasic - mNCB;
                    mDiscount = mOD * (discount/100);
                    if(nil_dep == 2)
                        mNIL_DEP = (mBasic * getNilDep(age))/100;

                    if(age >4){
                        mNIL_DEP = 0;
                    }

                    mNET_OD  = mOD - mDiscount + mNIL_DEP;
                    if(cpa == 2)
                        mCPA_50 = 50;
                    if(pa_to_pass == 1){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa * 7;
                    }
                    else if(pa_to_pass == 2){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa *7 *2;
                    }
                    mTP_Total = mTP + mCPA_50 + mPassPa;
                    mTotal = mTP_Total + mNET_OD;
                    mGST = mTotal * 0.09f;
                    mNet_Payable = 2 * Math.round(mGST) + mTotal;
                    break;

                case 2: mTP = 720;
                    mBasic = age_zoneAValue[getAgeGroup(age)][0]*idv;
                    mBasic /= 100;
                    mNCB = mBasic * ncb;
                    mNCB /= 100;
                    mOD = mBasic - mNCB;
                    mDiscount = mOD * (discount/100);
                    if(nil_dep == 2)
                        mNIL_DEP = (mBasic * getNilDep(age))/100;

                    if(age >4){
                        mNIL_DEP = 0;
                    }

                    mNET_OD  = mOD - mDiscount + mNIL_DEP;
                    if(cpa == 2)
                        mCPA_50 = 50;
                    mPassPa = pa_amount/10000;
                    if(pa_to_pass == 1)
                        mPassPa = mPassPa * 7;
                    else if(pa_to_pass == 2) mPassPa = mPassPa *7 *2;
                    mTP_Total = mTP + mCPA_50 + mPassPa;
                    mTotal = mTP_Total + mNET_OD;
                    mGST = mTotal * 0.09f;
                    mNet_Payable = 2 * Math.round(mGST) + mTotal;
                    break;

                case 3:
                    mTP = 887;
                    mBasic = age_zoneAValue[getAgeGroup(age)][1]*idv;
                    mBasic /= 100;
                    mNCB = mBasic * ncb;
                    mNCB /= 100;
                    mOD = mBasic - mNCB;
                    mDiscount = mOD * (discount/100);
                    if(nil_dep == 2)
                        mNIL_DEP = (mBasic * getNilDep(age))/100;

                    if(age >4){
                        mNIL_DEP = 0;
                    }

                    mNET_OD  = mOD - mDiscount + mNIL_DEP;
                    if(cpa == 2)
                        mCPA_50 = 50;
                    if(pa_to_pass == 1){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa * 7;
                    }
                    else if(pa_to_pass == 2){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa *7 *2;
                    }
                    mTP_Total = mTP + mCPA_50 + mPassPa;
                    mTotal = mTP_Total + mNET_OD;
                    mGST = mTotal * 0.09f;
                    mNet_Payable = 2 * Math.round(mGST) + mTotal;
                    break;

                case 4:
                    mTP = 1019;
                    mBasic = age_zoneAValue[getAgeGroup(age)][2]*idv;
                    mBasic /= 100;
                    mNCB = mBasic * ncb;
                    mNCB /= 100;
                    mOD = mBasic - mNCB;
                    mDiscount = mOD * (discount/100);
                    if(nil_dep == 2)
                        mNIL_DEP = (mBasic * getNilDep(age))/100;

                    if(age >4){
                        mNIL_DEP = 0;
                    }

                    mNET_OD  = mOD - mDiscount + mNIL_DEP;
                    if(cpa == 2)
                        mCPA_50 = 50;
                    if(pa_to_pass == 1){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa * 7;
                    }
                    else if(pa_to_pass == 2){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa *7 *2;
                    }
                    mTP_Total = mTP + mCPA_50 + mPassPa;
                    mTotal = mTP_Total + mNET_OD;
                    mGST = mTotal * 0.09f;
                    mNet_Payable = 2 * Math.round(mGST) + mTotal;
                    break;

            }
        }
        else if(zone == 2){

            switch (cc){
                case 1: mTP = 569;
                    mBasic = age_zoneBValue[getAgeGroup(age)][0]*idv;
                    mBasic /= 100;
                    mNCB = mBasic * ncb;
                    mNCB /= 100;
                    mOD = mBasic - mNCB;
                    mDiscount = mOD * (discount/100);
                    if(nil_dep == 2)
                        mNIL_DEP = (mBasic * getNilDep(age))/100;

                    if(age >4){
                        mNIL_DEP = 0;
                    }

                    mNET_OD  = mOD - mDiscount + mNIL_DEP;
                    if(cpa == 2)
                        mCPA_50 = 50;
                    if(pa_to_pass == 1){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa * 7;
                    }
                    else if(pa_to_pass == 2){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa *7 *2;
                    }
                    mTP_Total = mTP + mCPA_50 + mPassPa;
                    mTotal = mTP_Total + mNET_OD;
                    mGST = mTotal * 0.09f;
                    mNet_Payable = 2 * Math.round(mGST) + mTotal;
                    break;

                case 2: mTP = 720;
                    mBasic = age_zoneBValue[getAgeGroup(age)][0]*idv;
                    mBasic /= 100;
                    mNCB = mBasic * ncb;
                    mNCB /= 100;
                    mOD = mBasic - mNCB;
                    mDiscount = mOD * (discount/100);
                    if(nil_dep == 2)
                        mNIL_DEP = (mBasic * getNilDep(age))/100;

                    if(age >4){
                        mNIL_DEP = 0;
                    }

                    mNET_OD  = mOD - mDiscount + mNIL_DEP;
                    if(cpa == 2)
                        mCPA_50 = 50;
                    if(pa_to_pass == 1){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa * 7;
                    }
                    else if(pa_to_pass == 2){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa *7 *2;
                    }
                    mTP_Total = mTP + mCPA_50 + mPassPa;
                    mTotal = mTP_Total + mNET_OD;
                    mGST = mTotal * 0.09f;
                    mNet_Payable = 2 * Math.round(mGST) + mTotal;
                    break;

                case 3:
                    mTP = 887;
                    mBasic = age_zoneBValue[getAgeGroup(age)][1]*idv;
                    mBasic /= 100;
                    mNCB = mBasic * ncb;
                    mNCB /= 100;
                    mOD = mBasic - mNCB;
                    mDiscount = mOD * (discount/100);
                    if(nil_dep == 2)
                        mNIL_DEP = (mBasic * getNilDep(age))/100;

                    if(age >4){
                        mNIL_DEP = 0;
                    }

                    mNET_OD  = mOD - mDiscount + mNIL_DEP;
                    if(cpa == 2)
                        mCPA_50 = 50;
                    if(pa_to_pass == 1){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa * 7;
                    }
                    else if(pa_to_pass == 2){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa *7 *2;
                    }
                    mTP_Total = mTP + mCPA_50 + mPassPa;
                    mTotal = mTP_Total + mNET_OD;
                    mGST = mTotal * 0.09f;
                    mNet_Payable = 2 * Math.round(mGST) + mTotal;
                    break;

                case 4:
                    mTP = 1019;
                    mBasic = age_zoneBValue[getAgeGroup(age)][2]*idv;
                    mBasic /= 100;
                    mNCB = mBasic * ncb;
                    mNCB /= 100;
                    mOD = mBasic - mNCB;
                    mDiscount = mOD * (discount/100);
                    if(nil_dep == 2)
                        mNIL_DEP = (mBasic * getNilDep(age))/100;

                    if(age >4){
                        mNIL_DEP = 0;
                    }

                    mNET_OD  = mOD - mDiscount + mNIL_DEP;
                    if(cpa == 2)
                        mCPA_50 = 50;

                    if(pa_to_pass == 1){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa * 7;
                    }
                    else if(pa_to_pass == 2){
                        mPassPa = pa_amount/10000;
                        mPassPa = mPassPa *7 *2;
                    }
                    mTP_Total = mTP + mCPA_50 + mPassPa;
                    mTotal = mTP_Total + mNET_OD;
                    mGST = mTotal * 0.09f;
                    mNet_Payable = 2 * Math.round(mGST) + mTotal;
                    break;

            }

        }
    }

    private int getNilDep(int age) {

        int i = 0;

        if(age<0.9)
            i = 15;
        else if (1<=age && age<1.9)
            i =  25;
        else if(2<=age && age<2.9)
            i = 35;
        else if (age>=3)
            i = 40;

        return i;
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

    private float to2decimal(float x){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        float twoDigitsF = Float.valueOf(decimalFormat.format(x));
        return twoDigitsF;
    }

    public float getmBasic(){
        return  to2decimal(mBasic);
    }

    public float getmNCB(){
        return  to2decimal(mNCB);
    }

    public float getmOD(){
        return to2decimal(mOD);
    }

    public float getmDiscount(){
        return to2decimal(mDiscount);
    }

    public float getmNIL_DEP(){
        return to2decimal(mNIL_DEP);
    }

    public float getmNET_OD(){
        return Math.round(to2decimal(mNET_OD));
    }

    public int getmTP(){
        return Math.round(mTP);
    }

    public int getmCPA_50(){
        return Math.round(mCPA_50);
    }

    public int getmPassPa(){
        return Math.round(mPassPa);
    }

    public int getmTP_Total(){
        return Math.round(mTP_Total);
    }

    public int getmTotal(){
        return Math.round(mTotal);
    }

    public int getmGST(){
        return 2 * Math.round(mGST);
    }

    public int getmNet_Payable(){
        return Math.round(mNet_Payable);
    }
}
