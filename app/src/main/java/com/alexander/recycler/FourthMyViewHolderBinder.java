package com.alexander.recycler;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FourthMyViewHolderBinder extends ViewHolderBinder {

    private final Worker item;
    private Adapter adapter;

    public FourthMyViewHolderBinder(BaseItem item, int viewType) {
        super(viewType);
        this.item = (Worker) item;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        CustomAdapter.FourthViewHolder viewHolder = (CustomAdapter.FourthViewHolder) holder;
        viewHolder.recyclerViewIn.setLayoutManager(new LinearLayoutManager(viewHolder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new Adapter(item.getStrings());
        viewHolder.recyclerViewIn.setAdapter(adapter);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, List<Object> payloads) {
        CustomAdapter.FourthViewHolder viewHolder = (CustomAdapter.FourthViewHolder) holder;
        Bundle o = (Bundle) payloads.get(0);
        for (String key: o.keySet()){
            if (key.equals("strings")){
                viewHolder.recyclerViewIn.setAdapter(adapter);
            }
        }
    }


    @Override
    public BaseItem getItem() {
        return item;
    }
}

