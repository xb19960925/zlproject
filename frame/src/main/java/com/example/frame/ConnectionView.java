package com.example.frame;

public interface ConnectionView<D> {
    void success(int whichApi,D... d);
    void error(int whichApi,Throwable pThrowable);
}
