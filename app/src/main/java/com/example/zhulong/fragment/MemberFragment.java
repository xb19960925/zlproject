package com.example.zhulong.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhulong.R;
import com.example.zhulong.base.BaseMvpFragment;
import com.example.zhulong.model.MemberModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends BaseMvpFragment<MemberModel> {


    @Override
    public MemberModel setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_member;
    }

    @Override
    public void setUpView() {

    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
