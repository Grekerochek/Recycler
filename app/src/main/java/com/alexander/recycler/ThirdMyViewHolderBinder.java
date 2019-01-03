package com.alexander.recycler;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ThirdMyViewHolderBinder extends ViewHolderBinder {

    private final Worker item;

    public ThirdMyViewHolderBinder(BaseItem item, int viewType) {
        super(viewType);
        this.item = (Worker) item;
    }


    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        CustomAdapter.ThirdViewHolder viewHolder = (CustomAdapter.ThirdViewHolder) holder;
        viewHolder.button.setText(item.getName());
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, List<Object> payloads) {
        ;
        CustomAdapter.ThirdViewHolder viewHolder = (CustomAdapter.ThirdViewHolder) holder;
        Bundle o = (Bundle) payloads.get(0);
        for (String key: o.keySet()){
            if (key.equals("name"))
                viewHolder.button.setText(item.getName());
        }
    }

    @Override
    public BaseItem getItem() {
        return item;
    }
}