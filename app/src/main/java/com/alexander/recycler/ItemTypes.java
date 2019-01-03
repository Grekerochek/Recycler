package com.alexander.recycler;

public enum ItemTypes {
    FIRST_ITEM(0),
    SECOND_ITEM(1),
    THIRD_ITEM(2),
    FOURTH_ITEM(3);

    int type;

    ItemTypes(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }


}
