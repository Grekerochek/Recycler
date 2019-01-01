package com.alexander.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class FirstMyViewHolderBinder extends ViewHolderBinder {

    private final Worker item;

    public FirstMyViewHolderBinder(BaseItem item, int viewType) {
        super(viewType);
        this.item = (Worker) item;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        CustomAdapter.FirstViewHolder viewHolder = (CustomAdapter.FirstViewHolder) holder;
        viewHolder.button.setText(item.getName());
        viewHolder.textView.setText(item.getPosition());
        viewHolder.imageView.setImageResource(item.getPhoto());
    }

    @Override
    public BaseItem getItem() {
        return item;
    }
}
