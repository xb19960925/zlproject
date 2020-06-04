package com.example.frame;

public interface ConnectionModel<T> {
    void getNetData(ConnectionPersenter connectionPersenter,int whichApi,T... t);
}
