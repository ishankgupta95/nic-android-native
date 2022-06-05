package com.ishank.insuranceprmcalculator.utils;

import java.text.DecimalFormat;

public class CalculatePrivateCarOD {

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
    private int engine_protect;
    private int ncb_protect;
    private int invoice_protect;


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
    private int mEngine_Protect = 0;
    private int mNCB_Protect = 0;
    private int mInvoice_Protect = 0;


    public CalculatePrivateCarOD(int age, int zone, int cc, int idv, int lpg_cng, int buitlInLPG, int tape, int towing, int ncb,
                               int nil_dep, int cpa, int driver, int pa_to_pass, int pa_amount, float discount, int engine_protect,int ncb_protect, int invoice_protect) {

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
        this.engine_protect = engine_protect;
        this.ncb_protect = ncb_protect;
        this.invoice_protect = invoice_protect;
    }

    public void calculateInsurance(){

        switch (zone){
            case 1:
                switch(this.cc){
                    case 1:
//                        mTP = 2072;
                        mBasic = to2decimal(0.025f * (float)idv);
                    break;
                    case 2:
//                        mTP = 3221;
                        mBasic = to2decimal(0.0262f * (float)idv);
                        break;
                    case 3:
//                        mTP = 7890;
                        mBasic = to2decimal(0.027f * (float)idv);
                        break;
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

                if(tape!=0 && idv != 0)
                    mTape = to2decimal(0.04f * tape);

                if(towing!=0 && idv != 0)
                    mTowing_Charges = to2decimal(0.05f * towing);

                mSubTotal = mBasic + mLPG + mTowing_Charges + mTape;

                mNCB = to2decimal(ncb * mSubTotal/100);
                //mNCB  = mNCB/100;

                mTotal = Math.round(mSubTotal-mNCB);

                if(nil_dep == 2)
                    mNIL_DEP = to2decimal(getNILDEP(age)*(idv));

                if(engine_protect == 2)
                    mEngine_Protect = Math.round((idv * getEngineProtect(age))/100);

                if(ncb_protect ==2){
                    if(ncb == 20){
                        mNCB_Protect = Math.round((0.16f * idv)/100);
                    }

                    else if(ncb == 25){
                        mNCB_Protect = Math.round((0.17f * idv)/100);
                    }

                    else if(ncb == 35){
                        mNCB_Protect = Math.round((0.20f * idv)/100);
                    }

                    else if(ncb == 45){
                        mNCB_Protect = Math.round((0.21f * idv)/100);
                    }

                    else if(ncb == 50){
                        mNCB_Protect = Math.round((0.23f * idv)/100);
                    }

                    else mNCB_Protect = 0;
                }

                if(invoice_protect == 2)
                    mInvoice_Protect = Math.round((idv * getInvoiceProtect(age))/100);

                if(age >4){
                    mNIL_DEP = 0;
                    mEngine_Protect = 0;
                    mInvoice_Protect = 0;
                }

                mOD_Total = mNIL_DEP + mTotal + mEngine_Protect + mNCB_Protect + mInvoice_Protect;

                if(discount!=0)
                    mDiscount = to2decimal((discount * mTotal)/100);

                mNET_OD = Math.round(mOD_Total - mDiscount);

                /*TP SECTION*******************************************************************************/
//
//                if(driver==2)
//                    mDriver = 50;
//
//                if(cpa==2)
//                    mCPA_100 = 295;
//
//                if(pa_to_pass!=0)
//                    mPassPa = 5 * pa_to_pass * pa_amount/10000;

//                mTP_Total = mTP + mCPA_100 + mDriver + mLPG_60 +  mPassPa;

                mTotal2 = mNET_OD;

                mGST = 2*Math.round(mTotal2 * 0.09f);

                mNet_Payable = mGST + mTotal2;

                break;

            case 2:
                switch(this.cc){
                    case 1:
//                        mTP = 2072;
                        mBasic = to2decimal(0.022f * (float)idv);break;
                    case 2:
//                        mTP = 3221;
                    mBasic = to2decimal(0.0252f * (float)idv); break;
                    case 3:
//                        mTP = 7890;
                        mBasic = to2decimal(0.0234f * (float)idv); break;
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

                if(tape!=0 && idv != 0)
                    mTape = to2decimal(0.04f * tape);

                if(towing!=0 && idv != 0)
                    mTowing_Charges = to2decimal(0.05f * towing);

                mSubTotal = mBasic + mLPG + mTowing_Charges + mTape;


                mNCB = to2decimal(ncb * mSubTotal/100);
                //mNCB  = mNCB/100;

                mTotal = Math.round(mSubTotal-mNCB);

                if(nil_dep == 2)
                    mNIL_DEP = to2decimal(getNILDEP(age)*(idv));
                if(engine_protect == 2)
                    mEngine_Protect = Math.round((idv * getEngineProtect(age))/100);

                if(ncb_protect ==2){
                    if(ncb == 20){
                        mNCB_Protect = Math.round((0.16f * idv)/100);
                    }

                    else if(ncb == 25){
                        mNCB_Protect = Math.round((0.17f * idv)/100);
                    }

                    else if(ncb == 35){
                        mNCB_Protect = Math.round((0.20f * idv)/100);
                    }

                    else if(ncb == 45){
                        mNCB_Protect = Math.round((0.21f * idv)/100);
                    }

                    else if(ncb == 50){
                        mNCB_Protect = Math.round((0.23f * idv)/100);
                    }

                    else mNCB_Protect = 0;
                }

                if(invoice_protect == 2)
                    mInvoice_Protect = Math.round((idv * getInvoiceProtect(age))/100);

                if(age >4){
                    mNIL_DEP = 0;
                    mEngine_Protect = 0;
                    mInvoice_Protect = 0;
                }

                mOD_Total = mNIL_DEP + mTotal + mEngine_Protect + mNCB_Protect + mInvoice_Protect ;

                if(discount!=0)
                    mDiscount = to2decimal((discount * mTotal)/100);

                mNET_OD = Math.round(mOD_Total - mDiscount);

//                if(driver==2)
//                    mDriver = 50;
//
//                if(cpa==2)
//                    mCPA_100 = 295;
//
//                if(pa_to_pass!=0)
//                    mPassPa = 5 * pa_to_pass * pa_amount/10000;

//                mTP_Total = mTP + mCPA_100 + mDriver + mLPG_60 +  mPassPa;

                mTotal2 = mNET_OD;

                mGST = 2*Math.round(mTotal2 * 0.09f);

                mNet_Payable = mGST + mTotal2;

                break;

            case 3:
                switch(this.cc){
                    case 1:
//                        mTP = 2072;
                    mBasic = to2decimal(0.0219f * (float)idv);  break;
                    case 2:
//                        mTP = 3221;
                        mBasic = to2decimal(0.0243f * (float)idv); break;
                    case 3:
//                        mTP = 7890;
                        mBasic = to2decimal(0.0225f * (float)idv); break;
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

                if(tape!=0 && idv != 0)
                    mTape = to2decimal(0.04f * tape);

                if(towing!=0 && idv != 0)
                    mTowing_Charges = to2decimal(0.05f * towing);

                mSubTotal = mBasic + mLPG + mTowing_Charges + mTape;

                mNCB = to2decimal(ncb * mSubTotal/100);
                //mNCB  = mNCB/100;

                mTotal = Math.round(mSubTotal-mNCB);

                if(nil_dep == 2)
                    mNIL_DEP = to2decimal(getNILDEP(age)*(idv));
                if(engine_protect == 2)
                    mEngine_Protect = Math.round((idv * getEngineProtect(age))/100);

                if(ncb_protect ==2){
                    if(ncb == 20){
                        mNCB_Protect = Math.round((0.16f * idv)/100);
                    }

                    else if(ncb == 25){
                        mNCB_Protect = Math.round((0.17f * idv)/100);
                    }

                    else if(ncb == 35){
                        mNCB_Protect = Math.round((0.20f * idv)/100);
                    }

                    else if(ncb == 45){
                        mNCB_Protect = Math.round((0.21f * idv)/100);
                    }

                    else if(ncb == 50){
                        mNCB_Protect = Math.round((0.23f * idv)/100);
                    }

                    else mNCB_Protect = 0;
                }

                if(invoice_protect == 2)
                    mInvoice_Protect = Math.round((idv * getInvoiceProtect(age))/100);

                if(age >4){
                    mNIL_DEP = 0;
                    mEngine_Protect = 0;
                    mInvoice_Protect = 0;
                }

                mOD_Total = mNIL_DEP + mTotal + mEngine_Protect + mNCB_Protect + mInvoice_Protect ;

                if(discount!=0)
                    mDiscount = to2decimal((discount * mTotal)/100);

                mNET_OD = Math.round(mOD_Total - mDiscount);

//                if(driver==2)
//                    mDriver = 50;
//
//                if(cpa==2)
//                    mCPA_100 = 295;
//
//                if(pa_to_pass!=0)
//                    mPassPa = 5 * pa_to_pass * pa_amount/10000;

//                mTP_Total = mTP + mCPA_100 + mDriver + mLPG_60 +  mPassPa;

                mTotal2 = mNET_OD;

                mGST = 2*Math.round(mTotal2 * 0.09f);

                mNet_Payable = mGST + mTotal2;

                break;

            case 4:
                switch(this.cc){
                    case 1:
//                        mTP = 2072;
                        mBasic = to2decimal(0.0207f * (float)idv);  break;
                    case 2:
//                        mTP = 3221;
                        mBasic = to2decimal(0.0225f * (float)idv); break;
                    case 3:
//                        mTP = 7890;
                        mBasic = to2decimal(0.0224f * (float)idv); break;
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

                if(tape!=0 && idv != 0)
                    mTape = to2decimal(0.04f * tape);

                if(towing!=0 && idv != 0)
                    mTowing_Charges = to2decimal(0.05f * towing);

                mSubTotal = mBasic + mLPG + mTowing_Charges + mTape;

                mNCB = to2decimal(ncb * mSubTotal/100);
                //mNCB  = mNCB/100;

                mTotal = Math.round(mSubTotal-mNCB);

                if(nil_dep == 2)
                    mNIL_DEP = to2decimal(getNILDEP(age)*(idv));
                if(engine_protect == 2)
                    mEngine_Protect = Math.round((idv * getEngineProtect(age))/100);

                if(ncb_protect ==2){
                    if(ncb == 20){
                        mNCB_Protect = Math.round((0.16f * idv)/100);
                    }

                    else if(ncb == 25){
                        mNCB_Protect = Math.round((0.17f * idv)/100);
                    }

                    else if(ncb == 35){
                        mNCB_Protect = Math.round((0.20f * idv)/100);
                    }

                    else if(ncb == 45){
                        mNCB_Protect = Math.round((0.21f * idv)/100);
                    }

                    else if(ncb == 50){
                        mNCB_Protect = Math.round((0.23f * idv)/100);
                    }

                    else mNCB_Protect = 0;
                }

                if(invoice_protect == 2)
                    mInvoice_Protect = Math.round((idv * getInvoiceProtect(age))/100);

                if(age >4){
                    mNIL_DEP = 0;
                    mEngine_Protect = 0;
                    mInvoice_Protect = 0;
                }

                mOD_Total = mNIL_DEP + mTotal + mEngine_Protect + mNCB_Protect + mInvoice_Protect ;

                if(discount!=0)
                    mDiscount = to2decimal((discount * mTotal)/100);

                mNET_OD = Math.round(mOD_Total - mDiscount);

//                if(driver==2)
//                    mDriver = 50;
//
//                if(cpa==2)
//                    mCPA_100 = 295;
//
//                if(pa_to_pass!=0)
//                    mPassPa = 5 * pa_to_pass * pa_amount/10000;

//                mTP_Total = Math.round(mTP + mCPA_100 + mDriver + mLPG_60 +  mPassPa);

                mTotal2 = mNET_OD;

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

    private float getEngineProtect(int age){
        float i = 0;
        if(age == 0)
            i = 0.09f;
        if (age==1)
            i = 0.11f;
        if(age==2)
            i = 0.14f;
        if(age==3)
            i = 0.16f;
        if(age==4)
            i = 0.20f;
        return i;
    }

    private float getInvoiceProtect(int age){
        float i = 0;
        if(age == 0)
            i = 0.08f;
        if (age==1)
            i = 0.30f;
        if(age==2)
            i = 0.42f;
        if(age==3)
            i = 0.59f;
        if(age==4)
            i = 0.67f;
        return i;
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

    public int getmEngine_Protect() {
        return mEngine_Protect;
    }

    public int getmNCB_Protect(){
        return mNCB_Protect;
    }

    public int getmInvoice_Protect() {
        return mInvoice_Protect;
    }

    public String getIDV() {
        if (idv == 0) {
            return "IDV not entered";
        } else return Integer.toString(idv);
    }

    private float getNILDEP(int age) {

        float a = 0;

        if(age == 0)
            a = 0.0045f;
        else if(age>=1 && age<2)
            a = 0.0060f;
        else if(age>=2 && age<3)
            a = 0.0075f;
        else if(age>=3 && age<4)
            a = 0.0098f;
        else if (age>=4)
            a = 0.0125f;

        return  a;
    }


}
