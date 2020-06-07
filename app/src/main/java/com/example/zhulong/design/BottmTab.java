package com.example.zhulong.design;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.zhulong.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottmTab extends RelativeLayout {
    @BindView(R.id.main_page_tab)
    TextView mainPageTab;
    @BindView(R.id.course_tab)
    TextView courseTab;
    @BindView(R.id.vip_tab)
    TextView vipTab;
    @BindView(R.id.data_tab)
    TextView dataTab;
    @BindView(R.id.mine_tab)
    TextView mineTab;
    private Context mContext;
    private final TypedArray typedArray;
    private final int mTabNum;
    private final int mSelectedColor;
    private final int mUnSelectedColor;
    //根据自定义属性中tab数量的设置，过滤掉不用的view，剩下接下来使用的view
    private List<TextView> usedTabView = new ArrayList<>();
    private List<Integer> normalIcon;//为选中的icon
    private List<Integer> selectedIcon;// 选中的icon
    private List<String> tabContent;//tab对应的内容
    private int defaultTab = 1;//默认显示第几个tab

    public BottmTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.commont_bottom_tab, this);
        ButterKnife.bind(this, inflate);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottmTab, 0, 0);
        //自定义属性：
        //设置有多少个tab，默认是4个。布局使用该属性就按照布局的值来，否则
        mTabNum = typedArray.getInt(R.styleable.BottmTab_tabNum, 4);
        //设置选中的tab的颜色
        mSelectedColor = typedArray.getColor(R.styleable.BottmTab_selectedColor, Color.RED);
        //设置未选中的tab的颜色
        mUnSelectedColor = typedArray.getColor(R.styleable.BottmTab_unSelectedColor, Color.BLACK);


        Collections.addAll(usedTabView, mainPageTab, courseTab, vipTab, dataTab, mineTab);
        if (mTabNum < 5) {
            //倒着隐藏tab
            for (int i = 5; i > mTabNum; i--) {
                usedTabView.get(i - 1).setVisibility(GONE);
                usedTabView.remove(i - 1);
            }
        }
        typedArray.recycle();
    }

    /**
     * 给自定义tab设置数据
     *
     * @param normalIcon   未选中的icon集合
     * @param selectedIcon 选中的icon集合
     * @param tabContent   tab内容描述
     * @param pDefaultTab  默认选中哪个tab
     */
    public void setResource(List<Integer> normalIcon, List<Integer> selectedIcon, List<String> tabContent, int pDefaultTab) {
        if (pDefaultTab <= 0) {
            Log.e(this.getClass().getSimpleName(), "setResource: " + "0个tab，你玩lz呢");
        }
        if (usedTabView.size() != normalIcon.size() || usedTabView.size() != selectedIcon.size() || usedTabView.size() != tabContent.size()) {
            Log.e(this.getClass().getName(), "---------" + "自定义属性的数量和传递的资源数量不匹配");
            return;
        }
        this.normalIcon = normalIcon;
        this.selectedIcon = selectedIcon;
        this.tabContent = tabContent;
        defaultTab = pDefaultTab;
        setContent();
        setStyle();
    }

    private void setContent() {
        for (int i = 0; i < tabContent.size(); i++) {
            usedTabView.get(i).setText(tabContent.get(i));
        }
    }

    private void setStyle() {
        for (int i = 0; i < usedTabView.size(); i++) {
            if (i == defaultTab - 1) {
                usedTabView.get(i).setTextColor(mSelectedColor);
                usedTabView.get(i).setCompoundDrawablesWithIntrinsicBounds(0, selectedIcon.get(i), 0, 0);
            } else {
                usedTabView.get(i).setTextColor(mUnSelectedColor);
                usedTabView.get(i).setCompoundDrawablesWithIntrinsicBounds(0, normalIcon.get(i), 0, 0);
            }
        }
    }

    @OnClick({R.id.main_page_tab, R.id.course_tab, R.id.vip_tab, R.id.data_tab, R.id.mine_tab})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.main_page_tab) {
            defaultTab = 1;
        } else if (id == R.id.course_tab) {
            defaultTab = 2;
        } else if (id == R.id.vip_tab) {
            defaultTab = 3;
        } else if (id == R.id.data_tab) {
            defaultTab = 4;
        } else if (id == R.id.mine_tab) {
            defaultTab = 5;
        }
        setStyle();
        if (mOnBottomTabClickCallBack != null) mOnBottomTabClickCallBack.clickTab(defaultTab);
    }

    private OnBottomTabClickCallBack mOnBottomTabClickCallBack;

    public void setOnBottomTabClickCallBack(OnBottomTabClickCallBack pOnBottomTabClickCallBack){
        mOnBottomTabClickCallBack = pOnBottomTabClickCallBack;
    }

    public interface OnBottomTabClickCallBack{
        void clickTab(int tab);
    }
}
