package com.alexander.recycler;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

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
    public void bindViewHolder(RecyclerView.ViewHolder holder, List<Object> payloads) {
        CustomAdapter.FirstViewHolder viewHolder = (CustomAdapter.FirstViewHolder) holder;
        Bundle o = (Bundle) payloads.get(0);
        for (String key: o.keySet()){
            if (key.equals("name")) {
                viewHolder.button.setText(item.getName());
            }
            if (key.equals("position")) {
                viewHolder.textView.setText(item.getPosition());
            }
            if (key.equals("photo")) {
                viewHolder.imageView.setImageResource(item.getPhoto());
            }
        }
    }

    @Override
    public BaseItem getItem() {
        return item;
    }
}
