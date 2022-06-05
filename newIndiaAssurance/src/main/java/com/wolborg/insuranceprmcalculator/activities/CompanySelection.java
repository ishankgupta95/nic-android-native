package com.wolborg.insuranceprmcalculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.wolborg.insuranceprmcalculator.R;

import static com.wolborg.insuranceprmcalculator.utils.Constants.COMPANY1;
import static com.wolborg.insuranceprmcalculator.utils.Constants.INTENT_EXTRA;

public class CompanySelection extends AppCompatActivity {

    private final String TAG = "CompanySelection";
    Boolean exit = false;

    private LinearLayout NIC;
    private LinearLayout NIA;
    private LinearLayout OIC;
    private LinearLayout UII;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_selection);

        final AdView adView = (AdView) findViewById(R.id.adView);

        NIC = (LinearLayout) findViewById(R.id.linearLayout_NIC);
        NIA = (LinearLayout) findViewById(R.id.linearLayout_NIA);
        OIC = (LinearLayout) findViewById(R.id.linearLayout_OIC);
        UII = (LinearLayout) findViewById(R.id.linearLayout_UII);

        NIC.setOnClickListener(selections);
        NIA.setOnClickListener(selections);
        OIC.setOnClickListener(selections);
        UII.setOnClickListener(selections);


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

    /**
     * Press Back two times repeatedly withing 3 sec to exit app
     */
    @Override
    public void onBackPressed() {

        if(exit)
        {
            super.onBackPressed();
        }

        exit = true;
        Toast.makeText(getApplicationContext(),R.string.exit,Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = false;
            }
        },3000);
    }


    public View.OnClickListener selections = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i;

            if (view == NIC){

                i = new Intent(getApplicationContext(),Home.class);
                i.putExtra(INTENT_EXTRA,COMPANY1);
                startActivity(i);
            }

            if (view == NIA){
                Toast.makeText(getApplicationContext(),R.string.under_process,Toast.LENGTH_SHORT).show();
            }

            if (view == OIC){
                Toast.makeText(getApplicationContext(),R.string.under_process,Toast.LENGTH_SHORT).show();
            }

            if (view == UII){
                Toast.makeText(getApplicationContext(),R.string.under_process,Toast.LENGTH_SHORT).show();
            }


        }
    };
}
