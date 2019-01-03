package com.alexander.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

public class DiffCall extends DiffUtil.Callback {

    private List<Worker> oldList;
    private List<Worker> newList;

    public DiffCall(List<Worker> oldList, List<Worker> newList){
        this.oldList = oldList;
        this.newList = newList;
    }
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Worker newWorker = newList.get(newItemPosition);
        Worker oldWorker = oldList.get(oldItemPosition);
        Bundle diff = new Bundle();
        if(!newWorker.getName().equals(oldWorker.getName())){
            diff.putString("name", newWorker.getName());
        }
        if(!newWorker.getAge().equals(oldWorker.getAge())){
            diff.putString("age", newWorker.getAge());
        }
        if(!newWorker.getPhoto().equals(oldWorker.getPhoto())){
            diff.putInt("photo", newWorker.getPhoto());
        }
        if(!newWorker.getPosition().equals(oldWorker.getPosition())){
            diff.putString("position", newWorker.getPosition());
        }
        if(!(newWorker.getId()==(oldWorker.getId()))){
            diff.putInt("id", newWorker.getId());
        }
        return diff;
    }
}
