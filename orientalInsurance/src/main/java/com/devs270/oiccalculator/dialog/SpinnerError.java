package com.devs270.oiccalculator.dialog;

import android.graphics.Color;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Abhishek on 19-10-2017.
 */

public class SpinnerError {
    public static void setSpinnerError(Spinner spinner, String error){
        View selectedView = spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("Error"); // any name of the error will do
            selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView.setText(error); // actual error message
            spinner.performClick(); // to open the spinner list if error is found.

        }
    }
}
