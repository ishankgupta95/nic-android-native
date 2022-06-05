package com.wolborg.insuranceprmcalculator.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.wolborg.insuranceprmcalculator.R;

public class Learn extends Dialog {

    /**
     * Learn Dilaog
     */
    private static TextView learnTextView;
    private Button closeDialog;
    private int resID;

    public Learn(){
        super(null,true,null);
    }

    public Learn(@NonNull Context context, boolean cancelable,int resID) {
        super(context, cancelable, null );
        this.resID = resID;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.learn_dailoge);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        learnTextView = findViewById(R.id.textViewLearn);
        closeDialog = findViewById(R.id.buttonDialogClose);
        learnTextView.setText(resID);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
