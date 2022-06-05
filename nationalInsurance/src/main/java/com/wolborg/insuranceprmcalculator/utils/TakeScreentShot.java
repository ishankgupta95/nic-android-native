package com.wolborg.insuranceprmcalculator.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.core.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TakeScreentShot {

    private static final String TAG = "TakeScreentShot";
    private static File file;


    public static void captureView(View view, Activity activity){
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        try {
            String fileName = java.util.UUID.randomUUID().toString();

            file = new File(activity.getExternalFilesDir(null),fileName + ".jpg") ;
            FileOutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream);
            stream.flush();
            stream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG,"FileNotFoundException" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"IOException" + e.getMessage());
        }

        shareImage(file,activity.getApplicationContext());
    }

    private static void shareImage(File file,Context context){

        Uri uri = FileProvider.getUriForFile(context, "com.mydomain.fileprovider", file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            Intent chooserIntent = Intent.createChooser(intent, "Share Screenshot");
            chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(chooserIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }
}
