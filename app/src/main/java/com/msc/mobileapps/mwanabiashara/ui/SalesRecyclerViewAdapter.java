/*
 * Copyright (c) 2019.
 * Daniel M. Musila
 *
 * @author Daniel Mutiso
 * @email dan.musila@gmail.com
 * All rights reserved.
 *
 * Redistribution and use in any forms, with or without
 * modification, are not permitted without express written permission or contractual
 * agreement with the author.
 */
package com.msc.mobileapps.mwanabiashara.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.msc.mobileapps.mwanabiashara.R;
import com.msc.mobileapps.mwanabiashara.db.Expense;
import com.msc.mobileapps.mwanabiashara.db.Sale;

import java.text.SimpleDateFormat;
import java.util.List;

public class SalesRecyclerViewAdapter extends RecyclerView.Adapter<SalesRecyclerViewAdapter.ViewHolder> {
    private final List<Sale> mValues;
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public SalesRecyclerViewAdapter(
            List<Sale> items
    ) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_tx_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        // todo: try catch null for anything from the API calls
        holder.mItem = mValues.get(position);
        holder.tDate.setText(simpleDateFormat.format(holder.mItem.createdAt));
        holder.tName.setText(holder.mItem.title);
        holder.tQty.setText(holder.mItem.quantity);
        holder.tCost.setText(String.format("KES %,.2f", (double) holder.mItem.amount));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        final TextView tDate;
        final TextView tName;
        final TextView tQty;
        final TextView tCost;
        public Sale mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tDate = view.findViewById(R.id.itemDate);
            tName = view.findViewById(R.id.itemName);
            tQty = view.findViewById(R.id.itemQty);
            tCost = view.findViewById(R.id.itemCost);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + tName.getText() + "'";
        }
    }
}
