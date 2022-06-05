package com.wolborg.insuranceprmcalculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.wolborg.insuranceprmcalculator.R;

public class Home extends AppCompatActivity {

    private final String TAG = "Home";

    /**
     * LinearLayouts initilization
     * @param ( home , fire health , misc )
     */

    private LinearLayout home;
    private LinearLayout fire;
    private LinearLayout health;
    private LinearLayout misc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /**
         * setting FindViewByIds for all linear layout items
         */
        home = (LinearLayout) findViewById(R.id.linearLayout_Motor);
        fire = (LinearLayout) findViewById(R.id.linearLayout_Fire);
        health = (LinearLayout) findViewById(R.id.linearLayout_Health);
        misc = (LinearLayout) findViewById(R.id.linearLayout_Misc);



        /**
         * Setting setOnClickListener methods
         */
        home.setOnClickListener(selections);
        fire.setOnClickListener(selections);
        health.setOnClickListener(selections);
        misc.setOnClickListener(selections);



        // Load an ad into the AdMob banner view.
        final AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").addTestDevice("57BB49040935CE0320C9C01E93003C87").build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                super.onAdOpened();
                adView.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Log.e(TAG,"onAdFailedToLoad");
            }
        });

    }

    public View.OnClickListener selections = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i;

            if (view == home){

                i = new Intent(getApplicationContext(),ChooseInsuranceType.class);
                startActivity(i);
            }

            if (view == fire){
                Toast.makeText(getApplicationContext(),R.string.under_process,Toast.LENGTH_SHORT).show();
            }

            if (view == health){
                Toast.makeText(getApplicationContext(),R.string.under_process,Toast.LENGTH_SHORT).show();
            }

            if (view == misc){
                Toast.makeText(getApplicationContext(),R.string.under_process,Toast.LENGTH_SHORT).show();
            }

        }
    };

}
