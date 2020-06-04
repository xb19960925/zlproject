package com.example.zhulong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zhulong.R;
import com.example.zhulong.design.BottmTab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {



    private List<Integer> normalIcon = new ArrayList<>();//为选中的icon
    private List<Integer> selectedIcon= new ArrayList<>();// 选中的icon
    private List<String> tabContent= new ArrayList<>();//tab对应的内容

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottmTab tabView = getView().findViewById(R.id.bottom_tab);
        Collections.addAll(normalIcon,R.drawable.main_page_view,R.drawable.course_view,R.drawable.vip_view,R.drawable.data_view,R.drawable.mine_view);
        Collections.addAll(selectedIcon,R.drawable.main_selected,R.drawable.course_selected,R.drawable.vip_selected,R.drawable.data_selected,R.drawable.mine_selected);
        Collections.addAll(tabContent,"主页","课程","VIP","资料","我的");
        tabView.setResource(normalIcon,selectedIcon,tabContent,1);
    }
}
