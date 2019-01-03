package com.alexander.recycler;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BaseItem> data;
    private final List<ViewHolderBinder> binders;
    private SparseArray<ViewHolderFactory> factoryMap;

    public CustomAdapter(List<BaseItem> data){
        binders = new ArrayList<>();
        setData(data);
        initFactory();

    }

    private void initFactory() {
        factoryMap = new SparseArray<>();
        factoryMap.put(ItemTypes.FIRST_ITEM.type, new FirstViewHolderFactory());
        factoryMap.put(ItemTypes.SECOND_ITEM.type, new SecondViewHolderFactory());
        factoryMap.put(ItemTypes.THIRD_ITEM.type, new ThirdViewHolderFactory());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolderFactory factory = factoryMap.get(viewType);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return factory.createViewHolder(parent, inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderBinder binder = binders.get(position);
        if (binder != null){
            binder.bindViewHolder(holder);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {

        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads);
        else {
            ViewHolderBinder binder = binders.get(position);
            if (binder != null){

                binder.bindViewHolder(holder, payloads);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (data.get(position).getType() == ItemTypes.FIRST_ITEM.type){
            return ItemTypes.FIRST_ITEM.type;
        }
        else if (data.get(position).getType() == ItemTypes.SECOND_ITEM.type){
            return ItemTypes.SECOND_ITEM.type;
        }
        else if (data.get(position).getType() == ItemTypes.THIRD_ITEM.type){
            return ItemTypes.THIRD_ITEM.type;
        }
        return -1;
    }

    private ViewHolderBinder generateBinder(BaseItem item){

        if (item.getType() == ItemTypes.FIRST_ITEM.type){
            return new FirstMyViewHolderBinder(item, ItemTypes.FIRST_ITEM.type);
        } else if (item.getType() == ItemTypes.SECOND_ITEM.type){
            return new SecondMyViewHolderBinder(item, ItemTypes.SECOND_ITEM.type);

        } else if (item.getType() == ItemTypes.THIRD_ITEM.type){
            return new ThirdMyViewHolderBinder(item, ItemTypes.THIRD_ITEM.type);
        }
        return null;
    }

    private void setData(List<BaseItem> items){
        binders.clear();
        for (BaseItem item: items){
            binders.add(generateBinder(item));
        }
        this.data = items;
    }

    public void onNewData(List<BaseItem> newData){

        List<Worker> workers = new ArrayList<>();
        for (BaseItem item: data)
            workers.add((Worker) item);
        List<Worker> newWorkers = new ArrayList<>();
        for (BaseItem item: newData)
            newWorkers.add((Worker) item);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCall(workers, newWorkers));;
        setData(newData);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class FirstViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;
        public Button button;

        public FirstViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            button = itemView.findViewById(R.id.button);
        }
    }

    public static class SecondViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public Button button;

        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            button = itemView.findViewById(R.id.button);
        }
    }

    public static class ThirdViewHolder extends RecyclerView.ViewHolder {

        public Button button;

        public ThirdViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
