package com.alexander.recycler;

import android.support.v7.widget.RecyclerView;

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
    public BaseItem getItem() {
        return item;
    }
}