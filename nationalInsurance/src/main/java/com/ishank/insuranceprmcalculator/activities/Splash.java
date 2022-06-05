package com.ishank.insuranceprmcalculator.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import com.ishank.insuranceprmcalculator.R;

public class Splash extends AppCompatActivity {

    private final String TAG = "Splash";
    private TextView version_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        version_number = findViewById(R.id.version_number);

        try {
            version_number.setText("Version: " + getPackageManager().getPackageInfo(getPackageName(),0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i(TAG,e.getLocalizedMessage());
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
                finish();
            }
        },1000);
    }
}
