package com.devs270.oiccalculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.devs270.oiccalculator.R;

public class Splash extends AppCompatActivity {

    private final String TAG = "Splash";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*File file = new File(String.valueOf(getExternalFilesDir(null))  + TWOWHEELER_FILE_LOCATION);
        if(!file.exists()){
            Log.i(TAG,TWOWHEELER_FILE_LOCATION + " NOT Present");
            copyAssets();
        }
        else{
            EXTERNAL_FILE_LOCATION = getExternalFilesDir(null).getAbsolutePath();
            Log.i(TAG,TWOWHEELER_FILE_LOCATION + " Already Present");
        }*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
                finish();
            }
        },1000);
    }

   /* private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
        Log.i(TAG,"File created");
    }*/


    /*private void copyFromAssets(){
        AssetManager assetManager = getAssets();
        try {
            InputStream fileIN = assetManager.open(XML_MOTOR_NAME);
            File outFile = new File(getExternalFilesDir(null), XML_MOTOR_NAME);
            EXTERNAL_FILE_LOCATION = outFile.getAbsolutePath();
            OutputStream fileOUT = new FileOutputStream(outFile);
            copyFile(fileIN, fileOUT);
        } catch (IOException e) {
            Log.e(TAG, "Failed to copy asset file: " + XML_MOTOR_NAME, e);
        }
    }

    private void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        for(String filename : files) {
            if(filename.contains("xml")){
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetManager.open(filename);

                    String outDir = getExternalFilesDir(null).getAbsolutePath();

                    EXTERNAL_FILE_LOCATION = outDir;

                    File outFile = new File(outDir, filename);

                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;
                } catch(IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + filename, e);
                }
            }

        }
    }*/
}
