package com.ishank.insuranceprmcalculator.fragments.motor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.listeners.BannerAdEventListener;
import com.ishank.insuranceprmcalculator.R;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class Motor extends Fragment {

    private String TAG = "Motor";

    /**
     * Calculator Fields, as they appear
     */
    EditText age;
    EditText numberOfPassengers;
    EditText trailerIdv;
    EditText numberOfTrailer;
    Spinner age2;
    Spinner zones;
    Spinner cc;
    Spinner private_public;
    EditText idv;
    Spinner ncb;
    Spinner nil_dep;
    Spinner cpa;
    Spinner pa_toPass;
    EditText pa_toPass2;
    EditText pa_amount;
    EditText discount;
    Spinner driver;
    Spinner built_in_LPG;
    EditText lpg_cng;
    EditText tape;
    EditText towing_charges;
    EditText gvw;
    EditText dr_cls;
    EditText nfpp;
    Spinner imt;
    Spinner engine_protect;
    Spinner ncb_protect;
    Spinner invoice_protect;
    Spinner passenger;


    TextView learn1;
    TextView learn2;
    TextView learn3;
    TextView learn4;
    TextView learn5;
    TextView learn6;
    TextView learn7;
    TextView learn8;
    TextView learn9;
    TextView learn10;
    TextView learn11;
    TextView learn12;
    TextView learn13;
    TextView learn14;
    TextView learn15;
    TextView learn16;
    TextView learn17;
    TextView learn18;
    TextView learn19;
    TextView learn20;
    TextView learn21;
    TextView learn1_2;
    TextView tv_nilDep;

    /**
     *Removable Fields
     */
    LinearLayout ll_ageET;
    LinearLayout ll_ageSpinner;
    LinearLayout ll_numberOfPassengers;
    LinearLayout ll_nildep;
    LinearLayout ll_pubpvt;
    LinearLayout ll_towing;
    LinearLayout ll_cc;
    LinearLayout ll_lpg_cng;
    LinearLayout ll_elec_fit;
    LinearLayout ll_built_in_lpg;
    LinearLayout ll_driver;
    LinearLayout ll_paTOpass;
    LinearLayout ll_paTOpass2;
    LinearLayout ll_paToAmount;
    LinearLayout ll_gvw;
    LinearLayout ll_imt;
    LinearLayout ll_dr_cls;
    LinearLayout ll_fnpp;
    LinearLayout ll_engine_protect;
    LinearLayout ll_ncb_protect;
    LinearLayout ll_invoice_protect;
    LinearLayout ll_passenger;
    LinearLayout ll_trailer_idv;
    LinearLayout ll_number_of_trailer;
    LinearLayout ll_discount;
    LinearLayout ll_cpa;


    /**
     * Buttons
     */
    Button reset;
    Button calculate;

    /**
     * Ads Object
     */
//    AdView adView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_motor, container, false);

        age = v.findViewById(R.id.editText_age);
        trailerIdv = v.findViewById(R.id.editText_trailer_of_idv) ;
        numberOfTrailer = v.findViewById(R.id.editText_number_of_trailor);
        age2 = v.findViewById(R.id.spinner_Age2);
        numberOfPassengers = v.findViewById(R.id.editText_number_of_passengers);
        zones = v.findViewById(R.id.spinner_zones);
        cc = v.findViewById(R.id.spinner_CC);
        private_public = v.findViewById(R.id.spinner_pvtpub);
        idv = v.findViewById(R.id.editText_IDV);
        ncb = v.findViewById(R.id.spinner_ncb);
        nil_dep = v.findViewById(R.id.spinner_nildep);
        cpa = v.findViewById(R.id.spinner_cpa);
        pa_toPass = v.findViewById(R.id.spinner_PAtoPass);
        pa_toPass2 = v.findViewById(R.id.editText_PAtoPass2);
        pa_amount = v.findViewById(R.id.editText_PA_Amount);
        discount = v.findViewById(R.id.editText_Discount);
        driver = v.findViewById(R.id.spinner_driver);
        lpg_cng = v.findViewById(R.id.editText_LPCNG);
        built_in_LPG = v.findViewById(R.id.spinner_builtInLPG);
        tape = v.findViewById(R.id.editText_Tape);
        towing_charges = v.findViewById(R.id.editText_Towing);
        gvw = v.findViewById(R.id.editText_GVW);
        nfpp = v.findViewById(R.id.editText_NFPP);
        dr_cls = v.findViewById(R.id.editText_DR_CLS);
        imt = v.findViewById(R.id.spinner_IMT);
        engine_protect = v.findViewById(R.id.spinner_engine_protect);
        ncb_protect = v.findViewById(R.id.spinner_ncb_protect);
        invoice_protect = v.findViewById(R.id.spinner_invoice_protect);
        passenger = v.findViewById(R.id.spinner_Passenger);

        learn1 =  v.findViewById(R.id.textViewLearn1);
        learn2 =  v.findViewById(R.id.textViewLearn2);
        learn3 =  v.findViewById(R.id.textViewLearn3);
        learn4 =  v.findViewById(R.id.textViewLearn4);
        learn5 =  v.findViewById(R.id.textViewLearn5);
        learn6 =  v.findViewById(R.id.textViewLearn6);
        learn7 =  v.findViewById(R.id.textViewLearn7);
        learn8 =  v.findViewById(R.id.textViewLearn8);
        learn9 =  v.findViewById(R.id.textViewLearn9);
        learn10 =  v.findViewById(R.id.textViewLearn10);
        learn11 =  v.findViewById(R.id.textViewLearn11);
        learn12 =  v.findViewById(R.id.textViewLearn12);
        learn13 =  v.findViewById(R.id.textViewLearn13);
        learn14 =  v.findViewById(R.id.textViewLearn14);
        learn15 =  v.findViewById(R.id.textViewLearn15);
        learn17 =  v.findViewById(R.id.textViewLearn17);
        learn16 =  v.findViewById(R.id.textViewLearn16);
        learn18 =  v.findViewById(R.id.textViewLearn18);
        learn19 =  v.findViewById(R.id.textViewLearn19);
        learn20 =  v.findViewById(R.id.textViewLearn20);
        learn21 =  v.findViewById(R.id.textViewLearn21);
        learn1_2 =  v.findViewById(R.id.textViewLearn1_2);

        ll_nildep = v.findViewById(R.id.linearLayout_NILDEP);
        ll_ageSpinner = v.findViewById(R.id.linearLayout_Age2);
        ll_numberOfPassengers = v.findViewById(R.id.linearLayout_number_of_passengers);
        ll_ageET = v.findViewById(R.id.linearLayout_Age); //For two wheeler
        ll_pubpvt = v.findViewById(R.id.linearLayout_PvtPub); //For truck
        ll_paTOpass = v.findViewById(R.id.linearLayout_PAtoPass);
        ll_paTOpass2 = v.findViewById(R.id.linearLayout_PAtoPass2);
        ll_paToAmount = v.findViewById(R.id.linearLayout_PAAmount);
        ll_cc = v.findViewById(R.id.linearLayout_CC);
        ll_towing = v.findViewById(R.id.linearLayout_Towing);
        ll_lpg_cng = v.findViewById(R.id.linearLayout_LPCNG);
        ll_elec_fit = v.findViewById(R.id.linearLayout_Tape);
        ll_built_in_lpg = v.findViewById(R.id.linearLayout_BuiltLPG);
        ll_driver = v.findViewById(R.id.linearLayout_Driver);
        ll_gvw = v.findViewById(R.id.linearLayout_GVW);
        ll_imt = v.findViewById(R.id.linearLayout_IMT);
        ll_dr_cls = v.findViewById(R.id.linearLayout_DR_CLS);
        ll_fnpp =  v.findViewById(R.id.linearLayout_NFPP);
        ll_engine_protect = v.findViewById(R.id.linearLayout_engine_protect);
        ll_ncb_protect = v.findViewById(R.id.linearLayout_ncb_protect);
        ll_invoice_protect = v.findViewById(R.id.linearLayout_invoice_protect);
        ll_passenger = v.findViewById(R.id.linearLayout_Passenger);
        ll_trailer_idv = v.findViewById(R.id.linearLayout_trailer_idv);
        ll_number_of_trailer = v.findViewById(R.id.linearLayout_number_of_trailor);
        ll_discount = v.findViewById(R.id.linearLayout_discount);
        ll_cpa = v.findViewById(R.id.linearLayout_CPA);

        tv_nilDep = v.findViewById(R.id.textView_nilDep);

        reset = v.findViewById(R.id.button_Reset);
        calculate = v.findViewById(R.id.button_Calculate);

        InMobiBanner bannerAd = (InMobiBanner) v.findViewById(R.id.banner);

        bannerAd.setListener(new BannerAdEventListener() {
            @Override
            public void onAdLoadSucceeded(InMobiBanner inMobiBanner) {
                super.onAdLoadSucceeded(inMobiBanner);
                Log.d(TAG, "onAdLoadSucceeded");
            }

            @Override
            public void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus) {
                super.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                Log.d(TAG, "Banner ad failed to load with error: " +
                        inMobiAdRequestStatus.getMessage());
            }

            @Override
            public void onAdClicked(InMobiBanner inMobiBanner, Map<Object, Object> map) {
                super.onAdClicked(inMobiBanner, map);
                Log.d(TAG, "onAdClicked");
            }

            @Override
            public void onAdDisplayed(InMobiBanner inMobiBanner) {
                super.onAdDisplayed(inMobiBanner);
                Log.d(TAG, "onAdDisplayed");
            }

            @Override
            public void onAdDismissed(InMobiBanner inMobiBanner) {
                super.onAdDismissed(inMobiBanner);
                Log.d(TAG, "onAdDismissed");
            }

            @Override
            public void onUserLeftApplication(InMobiBanner inMobiBanner) {
                super.onUserLeftApplication(inMobiBanner);
                Log.d(TAG, "onUserLeftApplication");
            }

            @Override
            public void onRewardsUnlocked(InMobiBanner inMobiBanner, Map<Object, Object> map) {
                super.onRewardsUnlocked(inMobiBanner, map);
                Log.d(TAG, "onRewardsUnlocked");
            }
        });

        bannerAd.load();

        lpg_cng.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        lpg_cng.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnClicks();
        hideView();
    }

    private void setOnClicks() {

        reset.setOnClickListener(clicks);
        calculate.setOnClickListener(clicks);


    }

    View.OnClickListener clicks = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view == reset) {
                age.getText().clear();
                numberOfTrailer.getText().clear();
                trailerIdv.getText().clear();
                numberOfPassengers.getText().clear();
                zones.setSelection(0);
                cc.setSelection(0);
                idv.getText().clear();
                ncb.setSelection(0);
                nil_dep.setSelection(0);
                cpa.setSelection(0);
                pa_toPass.setSelection(0);
                pa_amount.getText().clear();
                discount.getText().clear();
                pa_toPass2.getText().clear();
                private_public.setSelection(0);
                driver.setSelection(0);
                towing_charges.getText().clear();
                built_in_LPG.setSelection(0);
                lpg_cng.getText().clear();
                tape.getText().clear();
                gvw.getText().clear();
                nfpp.getText().clear();
                dr_cls.setSelection(0);
                imt.setSelection(0);
                engine_protect.setSelection(0);
                ncb_protect.setSelection(0);
                invoice_protect.setSelection(0);

            } else if (view == calculate)
                getFields();
        }
    };

    public abstract void getFields();
    public abstract void hideView();
}
