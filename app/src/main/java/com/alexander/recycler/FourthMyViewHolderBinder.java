package com.alexander.recycler;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FourthMyViewHolderBinder extends ViewHolderBinder {

    private final Worker item;

    public FourthMyViewHolderBinder(BaseItem item, int viewType) {
        super(viewType);
        this.item = (Worker) item;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        CustomAdapter.FourthViewHolder viewHolder = (CustomAdapter.FourthViewHolder) holder;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, List<Object> payloads) {
        CustomAdapter.FourthViewHolder viewHolder = (CustomAdapter.FourthViewHolder) holder;
        Bundle o = (Bundle) payloads.get(0);
        for (String key: o.keySet()){
            if (key.equals("photo")){
                //viewHolder.text.setText("Hello!");
            }
        }
    }


    @Override
    public BaseItem getItem() {
        return item;
    }
}

