package org.devio.hi.ui.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.devio.hi.library.util.HiDisplayUtil;
import org.devio.hi.library.util.HiViewUtil;
import org.devio.hi.ui.R;
import org.devio.hi.ui.tab.common.IHiTabLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 自定义(底部)导航栏
 */
public class HiTabBottomLayout extends FrameLayout implements IHiTabLayout<HiTabBottom, HiTabBottomInfo<?>> {

    private List<OnTabSelectedListener<HiTabBottomInfo<?>>> tabSelectedChangeListeners = new ArrayList<>();
    //保存当前选中的tab对应的info
    private HiTabBottomInfo<?> selectedInfo;
    private float bottomAlpha = 1f;// 默认不透明
    //TabBottom高度
    private float tabBottomHeight = 50;
    //TabBottom的头部线条高度
    private float bottomLineHeight = 0.5f;
    //TabBottom的头部线条颜色
    private String bottomLineColor = "#dfe0e1";
    private List<HiTabBottomInfo<?>> infoList;






    public HiTabBottomLayout(@NonNull Context context) {
        this(context,null);
    }

    public HiTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HiTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public HiTabBottom findTab(@NonNull HiTabBottomInfo<?> info) {
        ViewGroup fl = findViewWithTag(TAG_TAB_BOTTOM);
        int childCount = fl.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = fl.getChildAt(i);
            if (child instanceof HiTabBottom) {
                HiTabBottom tab = (HiTabBottom) child;
                if (tab.getHiTabInfo() == info) {
                    return tab;
                }
            }

        }
        return null;
    }

    @Override
    public void addTabSelectedChangeListener(OnTabSelectedListener<HiTabBottomInfo<?>> listener) {
        tabSelectedChangeListeners.add(listener);
    }

    @Override
    public void defaultSelected(@NonNull HiTabBottomInfo<?> defaultInfo) {
        onSelected(defaultInfo);
    }

    private static final String TAG_TAB_BOTTOM = "TAG_TAB_BOTTOM";

    @Override
    public void inflateInfo(@NonNull List<HiTabBottomInfo<?>> infoList) {
        if (infoList.isEmpty()) {
            return;
        }
        this.infoList = infoList;
        //移除之前已经添加的tab
        //1.如果业务需求，刷新后底部导航tab数量有变动，需要重新调用inflateInfo方法初始化底部导航
        //2.移除tab时，注意是i--，i要大于0，i=0表示显示内容区域的ContentView不能被移除
        for (int i = getChildCount() - 1; i > 0; i--) {
            removeViewAt(i);
        }
        selectedInfo = null;
        addBackground();
        //移除之前添加的HiTabBottom listener Tips：Java foreach remove 问题
        //方案1：for循环i--倒着删除
        //方案2：迭代器的方式删除
        Iterator<OnTabSelectedListener<HiTabBottomInfo<?>>> iterator = tabSelectedChangeListeners.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof HiTabBottom) {
                iterator.remove();
            }
        }

        int height = HiDisplayUtil.dp2px(tabBottomHeight, getResources());
        //Tips:为何不用LinearLayout：当动态改变child大小后Gravity.BOTTOM会失效
        //这样中间tab高度凸起，会引入其他问题
        FrameLayout fl = new FrameLayout(getContext());
        fl.setTag(TAG_TAB_BOTTOM);
        //单个tab宽度等于屏幕宽度/tab数量
        int width = HiDisplayUtil.getDisplayWidthInPx(getContext()) / infoList.size();
        int size = infoList.size();
        for (int i = 0; i < size; i++) {
            final HiTabBottomInfo<?> info = infoList.get(i);
            LayoutParams params = new LayoutParams(width, height);
            params.gravity = Gravity.BOTTOM;
            //依次向右排列
            params.leftMargin = i * width;
            HiTabBottom tabBottom = new HiTabBottom(getContext());
            tabSelectedChangeListeners.add(tabBottom);
            tabBottom.setHiTabInfo(info);
            fl.addView(tabBottom,params);
            tabBottom.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSelected(info);
                }
            });
        }
        LayoutParams flParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        flParams.gravity = Gravity.BOTTOM;
        addBottomLine();
        addView(fl,flParams);
        fixContentView();
    }


    public void setTabAlpha(float alpha) {
        this.bottomAlpha = alpha;
    }

    public void setTabHeight(float tabHeight) {
        this.tabBottomHeight = tabHeight;
    }

    public void setBottomLineHeight(float bottomLineHeight) {
        this.bottomLineHeight = bottomLineHeight;
    }

    public void setBottomLineColor(String bottomLineColor) {
        this.bottomLineColor = bottomLineColor;
    }

    private void addBottomLine() {
        View bottomLine = new View(getContext());
        bottomLine.setBackgroundColor(Color.parseColor(bottomLineColor));

        LayoutParams bottomLineParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HiDisplayUtil.dp2px(bottomLineHeight, getResources()));
        bottomLineParams.gravity = Gravity.BOTTOM;
        bottomLineParams.bottomMargin = HiDisplayUtil.dp2px(tabBottomHeight - bottomLineHeight, getResources());
        addView(bottomLine,bottomLineParams);
        bottomLine.setAlpha(bottomAlpha);
    }

    private void onSelected(@NonNull HiTabBottomInfo<?> nextInfo) {
        for (OnTabSelectedListener<HiTabBottomInfo<?>> listener : tabSelectedChangeListeners) {
            listener.onTabSelectedChange(infoList.indexOf(nextInfo), selectedInfo, nextInfo);
        }
        this.selectedInfo = nextInfo;
    }

    /**
     * 添加背景色
     */
    private void addBackground() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.hi_bottom_layout_bg, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HiDisplayUtil.dp2px(tabBottomHeight, getResources()));
        params.gravity = Gravity.BOTTOM;
        addView(view, params);
        view.setAlpha(bottomAlpha);
    }

    /**
     * 修复内容区域的底部Padding
     */
    private void fixContentView() {
        if (!(getChildAt(0) instanceof ViewGroup)) {
            return;
        }

        ViewGroup rootView = (ViewGroup) getChildAt(0);
        ViewGroup targetView = HiViewUtil.findTypeView(rootView, RecyclerView.class);
        if (targetView == null) {
            targetView = HiViewUtil.findTypeView(rootView, ScrollView.class);
        }
        if (targetView == null) {
            targetView = HiViewUtil.findTypeView(rootView, AbsListView.class);
        }
        if (targetView != null) {
            targetView.setPadding(0,0,0,HiDisplayUtil.dp2px(tabBottomHeight, getResources()));
            targetView.setClipToPadding(false);
        }
    }
}
