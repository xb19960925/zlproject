package com.example.zhulong;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
;

public class NoScrollVIewPager extends ViewPager {
    //设置禁止切换viewpager
    private boolean noScroll=false;

    public NoScrollVIewPager(@NonNull Context context) {
        super(context);
    }

    public NoScrollVIewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoScroll(boolean noScroll){
        this.noScroll=noScroll;
    }
    //滑动到指定位置
    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }
    //触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (noScroll){
            return super.onTouchEvent(ev);
        }else {
            return false;
        }
    }
    //拦截触摸事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (noScroll){
            return super.onInterceptTouchEvent(ev);
        }else {
            return false;
        }
    }
    //设置当前布局，并定义滑动方式
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
    //设置当前布局（取消Tablayout点击滑动效果）
    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,false);
    }
}
