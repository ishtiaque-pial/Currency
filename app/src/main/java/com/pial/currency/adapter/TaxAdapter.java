package com.pial.currency.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pial.currency.R;
import com.pial.currency.apiManger.pojo.TaxPojo;
import com.pial.currency.listener.TaxListener;

import java.util.ArrayList;
import java.util.Map;

public class TaxAdapter extends RecyclerView.Adapter<TaxAdapter.TaxAdapterHolder> {
    ArrayList<TaxPojo> taxPojos;
    private int radioPosition=0;
    private TaxListener listener;


    public TaxAdapter(ArrayList<TaxPojo> taxPojos,TaxListener listener) {
        this.taxPojos = taxPojos;
        radioPosition =0;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaxAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tax_layout, parent, false);
        TaxAdapterHolder taxAdapterHolder = new TaxAdapterHolder(view);
        return taxAdapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaxAdapterHolder holder, final int i) {
        holder.radioButton.setText(taxPojos.get(i).getTaxName());
        holder.amount.setText(String.valueOf(taxPojos.get(i).getTaxValue()));
        holder.radioButton.setChecked(radioPosition==i);
        if (radioPosition==i) {
            listener.onTaxItemClick(taxPojos.get(i).getTaxValue());
        }
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioPosition=i;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return taxPojos.size();
    }

    public class TaxAdapterHolder extends RecyclerView.ViewHolder {

    private RadioButton radioButton;
    private TextView amount;
        public TaxAdapterHolder(View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radio);
            amount = itemView.findViewById(R.id.amount);

        }
    }

}