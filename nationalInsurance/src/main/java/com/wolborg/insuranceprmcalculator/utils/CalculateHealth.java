package com.wolborg.insuranceprmcalculator.utils;

import androidx.annotation.NonNull;

//import com.google.android.gms.ads.formats.NativeAdOptions;

/**
 * Created by ishan on 16-11-2017.
 */

public class CalculateHealth {

    private final String TAG = "CalculateHealth";

    /**
     * Entry Fields
     */
    private int age;
    private int sum;
    private int spouse;
    private int child1;
    private int child2;
    private int diabetes;

    /**
     * Result Fields
     */
    private int mSum = 0;
    private int mSelf = 0;
    private int mSpouse = 0;
    private int mChild1 = 0;
    private int mChild2 = 0;
    private int mTotal = 0;
    private int mLoading = 0;
    private int mTotal_Premium = 0;
    private int mGST = 0;
    private int mFinal_Amount = 0;

    public CalculateHealth(@NonNull int age, @NonNull int sum, int spouse, int child1, int child2, int diabetes){

        this.age = age;
        this.sum = sum;
        this.spouse = spouse;
        this.child1 = child1;
        this.child2 = child2;
        this.diabetes = diabetes;
    }

    public void calculateInsurance(){
        if (age <= 35){
            if (sum == 2) {
                mSum = 200000;
                mSelf = 2469;
            }

            else if (sum == 3) {
                mSum = 250000;
                mSelf = 2956;
            }

            else if (sum == 4) {
                mSum = 300000;
                mSelf = 3444;
            }

            else if (sum == 5) {
                mSum = 350000;
                mSelf = 3870;
            }

            else if (sum == 6) {
                mSum = 400000;
                mSelf = 4297;
            }

            else if (sum == 7) {
                mSum = 450000;
                mSelf = 4723;
            }

            else if (sum == 8){
                mSum = 500000;
                mSelf = 5151;
            }

            if(spouse == 2)
                mSpouse = Math.round(0.25f * mSelf);

            if(child1 == 2)
                mChild1 = Math.round(0.20f * mSelf);

            if(child2 == 2)
                mChild2 = Math.round(0.20f * mSelf);

            mTotal = mSelf + mSpouse + mChild1 + mChild2;

            if(diabetes == 2)
                mLoading = Math.round(0.10f * mTotal);
            else if (diabetes == 3)
                mLoading = Math.round(0.25f * mTotal);

            mTotal_Premium = mTotal + mLoading;

            mGST = 2* Math.round( mTotal_Premium * 0.09f);

            mFinal_Amount = mTotal_Premium + mGST;
        }

        if (age >= 36 && age<=45 ){
            if (sum == 2) {
                mSum = 200000;
                mSelf = 2683;
            }
            else if (sum == 3) {
                mSum = 250000;
                mSelf = 3213;
            }
            else if (sum == 4) {
                mSum = 300000;
                mSelf = 3743;
            }
            else if (sum == 5) {
                mSum = 350000;
                mSelf = 4207;
            }
            else if (sum == 6) {
                mSum = 400000;
                mSelf = 4670;
            }
            else if (sum == 7) {
                mSum = 450000;
                mSelf = 5135;
            }
            else if (sum == 8) {
                mSum = 500000;
                mSelf = 5598;
            }

            if(spouse == 2)
                mSpouse = Math.round(0.30f * mSelf);

            if(child1 == 2)
                mChild1 = Math.round(0.20f * mSelf);

            if(child2 == 2)
                mChild2 = Math.round(0.20f * mSelf);

            mTotal = mSelf + mSpouse + mChild1 + mChild2;

            if(diabetes == 2)
                mLoading = Math.round(0.10f * mTotal);
            else if (diabetes == 3)
                mLoading = Math.round(0.25f * mTotal);

            mTotal_Premium = mTotal + mLoading;

            mGST = 2* Math.round( mTotal_Premium * 0.09f);

            mFinal_Amount = mTotal_Premium + mGST;
        }

        if (age >= 46 && age<=50 ){
            if (sum == 2) {
                mSum = 200000;
                mSelf = 4290;
            }
            else if (sum == 3) {
                mSum = 250000;
                mSelf = 5200;
            }
            else if (sum == 4) {
                mSum = 300000;
                mSelf = 6108;
            }
            else if (sum == 5) {
                mSum = 350000;
                mSelf = 6942;
            }
            else if (sum == 6) {
                mSum = 400000;
                mSelf = 7776;
            }
            else if (sum == 7) {
                mSum = 450000;
                mSelf = 8610;
            }
            else if (sum == 8) {
                mSum = 500000;
                mSelf = 9444;
            }

            if(spouse == 2)
                mSpouse = Math.round(0.35f * mSelf);

            if(child1 == 2)
                mChild1 = Math.round(0.20f * mSelf);

            if(child2 == 2)
                mChild2 = Math.round(0.20f * mSelf);

            mTotal = mSelf + mSpouse + mChild1 + mChild2;

            if(diabetes == 2)
                mLoading = Math.round(0.10f * mTotal);
            else if (diabetes == 3)
                mLoading = Math.round(0.25f * mTotal);

            mTotal_Premium = mTotal + mLoading;

            mGST = 2* Math.round( mTotal_Premium * 0.09f);

            mFinal_Amount = mTotal_Premium + mGST;
        }

        if (age >= 51 && age<=55 ){
            if (sum == 2) {
                mSum = 200000;
                mSelf = 4485;
            }
            else if (sum == 3) {
                mSum = 250000;
                mSelf = 5436;
            }
            else if (sum == 4) {
                mSum = 300000;
                mSelf = 6386;
            }
            else if (sum == 5) {
                mSum = 350000;
                mSelf = 7258;
            }
            else if (sum == 6) {
                mSum = 400000;
                mSelf = 8129;
            }
            else if (sum == 7) {
                mSum = 450000;
                mSelf = 9001;
            }
            else if (sum == 8) {
                mSum = 500000;
                mSelf = 9873;
            }

            if(spouse == 2)
                mSpouse = Math.round(0.40f * mSelf);

            if(child1 == 2)
                mChild1 = Math.round(0.20f * mSelf);

            if(child2 == 2)
                mChild2 = Math.round(0.20f * mSelf);

            mTotal = mSelf + mSpouse + mChild1 + mChild2;

            if(diabetes == 2)
                mLoading = Math.round(0.10f * mTotal);
            else if (diabetes == 3)
                mLoading = Math.round(0.25f * mTotal);

            mTotal_Premium = mTotal + mLoading;

            mGST = 2* Math.round( mTotal_Premium * 0.09f);

            mFinal_Amount = mTotal_Premium + mGST;
        }

        if (age >= 56 && age<=60 ){
            if (sum == 2) {
                mSum = 200000;
                mSelf = 5127;
            }
            else if (sum == 3) {
                mSum = 250000;
                mSelf = 6236;
            }
            else if (sum == 4) {
                mSum = 300000;
                mSelf = 7346;
            }
            else if (sum == 5) {
                mSum = 350000;
                mSelf = 8375;
            }
            else if (sum == 6) {
                mSum = 400000;
                mSelf = 9406;
            }
            else if (sum == 7) {
                mSum = 450000;
                mSelf = 10436;
            }
            else if (sum == 8) {
                mSum = 500000;
                mSelf = 11466;
            }

            if(spouse == 2)
                mSpouse = Math.round(0.40f * mSelf);

            if(child1 == 2)
                mChild1 = Math.round(0.20f * mSelf);

            if(child2 == 2)
                mChild2 = Math.round(0.20f * mSelf);

            mTotal = mSelf + mSpouse + mChild1 + mChild2;

            if(diabetes == 2)
                mLoading = Math.round(0.10f * mTotal);
            else if (diabetes == 3)
                mLoading = Math.round(0.25f * mTotal);

            mTotal_Premium = mTotal + mLoading;

            mGST = 2* Math.round( mTotal_Premium * 0.09f);

            mFinal_Amount = mTotal_Premium + mGST;
        }
    }

    public int getmSum(){
        return mSum;
    }

    public int getmSelf(){
        return mSelf;
    }

    public int getmSpouse(){
        return mSpouse;
    }

    public int getmChild1(){
        return mChild1;
    }

    public int getmChild2(){
        return mChild2;
    }

    public int getmLoading(){
        return mLoading;
    }

    public int getmTotal(){
        return mTotal;
    }

    public int getmTotal_Premium(){
        return mTotal_Premium;
    }

    public int getmGST(){
        return mGST;
    }

    public int getmFinal_Amount(){
        return mFinal_Amount;
    }
}
