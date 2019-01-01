package com.alexander.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class SecondMyViewHolderBinder extends ViewHolderBinder {

    private final Worker item;

    public SecondMyViewHolderBinder(BaseItem item, int viewType) {
        super(viewType);
        this.item = (Worker) item;
    }


    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        CustomAdapter.SecondViewHolder viewHolder = (CustomAdapter.SecondViewHolder) holder;
        viewHolder.button.setText(item.getName());
        viewHolder.textView.setText(item.getPosition());
    }

    @Override
    public BaseItem getItem() {
        return item;
    }
}
