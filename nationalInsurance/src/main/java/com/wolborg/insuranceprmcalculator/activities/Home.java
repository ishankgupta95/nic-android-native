package com.wolborg.insuranceprmcalculator.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.LinearLayout;
import com.wolborg.insuranceprmcalculator.R;


public class Home extends AppCompatActivity {

    private final String TAG = "Home";

    /**
     * LinearLayouts initilization
     * @param ( home , fire health , misc )
     */

    private LinearLayout home;
    private LinearLayout health;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /**
         * setting FindViewByIds for all linear layout items
         */
        home = (LinearLayout) findViewById(R.id.linearLayout_Motor);
        health = (LinearLayout) findViewById(R.id.linearLayout_Health);

        /**
         * Setting setOnClickListener methods
         */
        home.setOnClickListener(selections);
        health.setOnClickListener(selections);
    }

    public View.OnClickListener selections = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i;

            if (view == home){

                i = new Intent(getApplicationContext(),MotorInsurance1.class);
                startActivity(i);
            }

            if (view == health){
                i = new Intent(getApplicationContext(),HealthInsurance.class);
                startActivity(i);
            }

        }
    };

}
