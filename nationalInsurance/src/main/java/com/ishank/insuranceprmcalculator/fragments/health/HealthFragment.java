package com.ishank.insuranceprmcalculator.fragments.health;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ishank.insuranceprmcalculator.R;
import com.ishank.insuranceprmcalculator.activities.ResultActivity;
import com.ishank.insuranceprmcalculator.dialog.SpinnerError;

public class HealthFragment extends Fragment {

    public final static String TAG = "HealthFragment";

    private int mAge;
    private int mSum;
    private int mSpouse;
    private int mChild1;
    private int mChild2;
    private int mDiabetes;

    EditText age;
    Spinner sum;
    Spinner spouse;
    Spinner child1;
    Spinner child2;
    Spinner diabetes;

    /**
     * Buttons
     */
    Button reset;
    Button calculate;

    /**
     * Ads Object
     */
//    AdView adView;

    private String[] sumArray = new String[]{"Select value","0","200000","250000","300000","350000","400000","450000","500000"};
    private String[] yes_no = new String[]{"Select yes/no","No","Yes"};
    private String[] diabetesArray = new String[]{"Select value","No","Either","Both"};

    private boolean allClear = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        autoPopulate();
        setOnClicks();
    }

    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health, container, false);

        age = v.findViewById(R.id.editText_age);
        sum = v.findViewById(R.id.spinner_sum_insured_opted);
        spouse = v.findViewById(R.id.spinner_spouse);
        child1 = v.findViewById(R.id.spinner_child1);
        child2 = v.findViewById(R.id.spinner_child2);
        diabetes = v.findViewById(R.id.spinner_diabetes);

        reset = v.findViewById(R.id.button_Reset);
        calculate = v.findViewById(R.id.button_Calculate);

        return v;
    }

    public void getFields() {
        try {
            mAge = Integer.parseInt(age.getText().toString());
            allClear = true;
        } catch (NumberFormatException exception) {
            Log.e(TAG, exception.getMessage());
            age.setError("Age not provided ");
            allClear = false;
            age.requestFocus();
        }

        mSum = sum.getSelectedItemPosition();
        if (mSum == 0) {
            SpinnerError.setSpinnerError(sum, "Field Can't be unselected");
            allClear = false;
        }

        mSpouse = spouse.getSelectedItemPosition();

        mChild1 = child1.getSelectedItemPosition();

        mChild2 = child2.getSelectedItemPosition();

        mDiabetes = diabetes.getSelectedItemPosition();

        if (allClear) {
            age.clearFocus();
            Bundle b = new Bundle();
            b.putString("RESULT", TAG);
            b.putInt("Age", mAge);
            b.putInt("Sum", mSum);
            b.putInt("Spouse", mSpouse);
            b.putInt("Child1", mChild1);
            b.putInt("Child2", mChild2);
            b.putInt("Diabetes", mDiabetes);
            Intent i = new Intent(getContext(), ResultActivity.class);
            i.putExtras(b);
            startActivity(i);
        }
}

    private void autoPopulate() {
        ArrayAdapter<String> sumArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, sumArray);
        sumArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        sum.setAdapter(sumArrayAdapter);

        ArrayAdapter<String> yes_no_ArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, yes_no);
        yes_no_ArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        spouse.setAdapter(yes_no_ArrayAdapter);
        child1.setAdapter(yes_no_ArrayAdapter);
        child2.setAdapter(yes_no_ArrayAdapter);

        ArrayAdapter<String> diabetesArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, diabetesArray);
        diabetesArrayAdapter.setDropDownViewResource(R.layout.spinner_list);
        diabetes.setAdapter(diabetesArrayAdapter);
    }

    private void setOnClicks() {

        reset.setOnClickListener(clicks);
        calculate.setOnClickListener(clicks);
    }

    View.OnClickListener clicks = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == reset) {
                age.getText().clear();
                sum.setSelection(0);
                spouse.setSelection(0);
                child1.setSelection(0);
                child2.setSelection(0);
                diabetes.setSelection(0);
            }else if (view == calculate)
                getFields();
        }
    };


}
