package com.alexander.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondViewHolderFactory implements ViewHolderFactory {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View itemView = inflater.inflate(R.layout.recycler, parent,false);
        RecyclerView.ViewHolder holder = new CustomAdapter.SecondViewHolder(itemView);
        return holder;
    }
}
