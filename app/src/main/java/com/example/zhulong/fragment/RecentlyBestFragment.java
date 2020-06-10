package com.example.zhulong.fragment;

import com.example.zhulong.R;
import com.example.zhulong.base.BaseMvpFragment;
import com.example.zhulong.model.DatumModel;

public class RecentlyBestFragment extends BaseMvpFragment<DatumModel> {

    public static RecentlyBestFragment newInstance() {
        RecentlyBestFragment fragment = new RecentlyBestFragment();
        return fragment;
    }


    @Override
    public DatumModel setModel() {
        return new DatumModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_recently_best;
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
