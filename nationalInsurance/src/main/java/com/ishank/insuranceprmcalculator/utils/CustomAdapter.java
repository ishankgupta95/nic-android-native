package com.ishank.insuranceprmcalculator.utils;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ishank.insuranceprmcalculator.R;

import java.util.ArrayList;

/**
 * Created by ishank on 10-Jan-18.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    final static private String TAG = "CustomAdapter";
    ArrayList<String> list;
    private OnItemClicked onClick;

    public CustomAdapter(String[] strings) {
        list = new ArrayList<>();
        for (String s: strings){
            list.add(s);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv;
        public CardView cv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
            cv = itemView.findViewById(R.id.btc_cardview);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position) );
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.onItemClick(position);
            }
        });
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClicked {
        void onItemClick(int position);
    }

}


