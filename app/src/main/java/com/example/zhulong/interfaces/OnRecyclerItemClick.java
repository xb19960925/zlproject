package com.example.zhulong.interfaces;

public interface OnRecyclerItemClick<T> {
    void onItemClick(int pos, T... pTS);
}
