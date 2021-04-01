package org.devio.hi.ui.tab.top;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import org.devio.hi.ui.R;
import org.devio.hi.ui.tab.common.IHiTab;

/**
 * 自定义顶部tab控件
 */
public class HiTabTop extends RelativeLayout implements IHiTab<HiTabTopInfo<?>> {

    private HiTabTopInfo<?> tabInfo;
    private ImageView tabImageView;
    private TextView tabNameView;
    private View indicator;//指示器

    public HiTabTop(Context context) {
        this(context, null);
    }

    public HiTabTop(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HiTabTop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        init();
    }

    private void init() {
        //填充/装载布局
        LayoutInflater.from(getContext()).inflate(R.layout.hi_tab_top, this);
        tabImageView = findViewById(R.id.iv_image);
        tabNameView = findViewById(R.id.tv_name);
        indicator = findViewById(R.id.tab_top_indicator);
    }


    @Override
    public void setHiTabInfo(@NonNull HiTabTopInfo<?> data) {
        this.tabInfo = data;
        inflateInfo(false, true);
    }

    public HiTabTopInfo<?> getHiTabInfo() {
        return tabInfo;
    }

    /**
     *
     * @param selected 是否选中
     * @param init 是否第一次初始化
     */
    private void inflateInfo(boolean selected, boolean init) {

        if (tabInfo.tabType == HiTabTopInfo.TabType.TEXT) {//icon类型的数据绑定
            if (init) {// 初始化
                tabImageView.setVisibility(GONE);//因此bitmap类型的ImageView
                tabNameView.setVisibility(VISIBLE);
                if (!TextUtils.isEmpty(tabInfo.name)) {
                    tabNameView.setText(tabInfo.name);
                }
            }
            // 被点击选中
            if (selected) {
                indicator.setVisibility(VISIBLE);
                tabNameView.setTextColor(getTextColor(tabInfo.tintColor));
            } else {
                indicator.setVisibility(GONE);
                tabNameView.setTextColor(getTextColor(tabInfo.defaultColor));
            }
        } else if (tabInfo.tabType == HiTabTopInfo.TabType.BITMAP) {// bitmap类型的数据绑定
            if (init) {
                tabImageView.setVisibility(VISIBLE);
                tabNameView.setVisibility(GONE);
            }

            if (selected) {
                tabImageView.setImageBitmap(tabInfo.selectedBitmap);
            } else {
                tabImageView.setImageBitmap(tabInfo.defaultBitmap);
            }
        }
    }

    public ImageView getTabImageView() {
        return tabImageView;
    }



    public TextView getTabNameView() {
        return tabNameView;
    }

    /**
     * 改变某个tab的高度
     * @param height
     */
    @Override
    public void resetHeight(int height) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        //隐藏tab的name
//        getTabNameView().setVisibility(View.GONE);
    }

    /**
     * 底部tab选中状态改变监听
     * @param index
     * @param prevInfo
     * @param nextInfo
     */
    @Override
    public void onTabSelectedChange(int index, @Nullable HiTabTopInfo<?> prevInfo, @NonNull HiTabTopInfo<?> nextInfo) {
        // prevInfo != tabInfo && nextInfo != tabInfo 表示没有选中，下一个选中的也不是
        // prevInfo == nextInfo 表示重复点击选中的情况
        if (prevInfo != tabInfo && nextInfo != tabInfo || prevInfo == nextInfo) {
            return;
        }

        if (prevInfo == tabInfo) {// 由选中切换为没选中
            inflateInfo(false, false);
        } else {// 由没选中切换为选中
            inflateInfo(true, false);
        }
    }

    @ColorInt
    private int getTextColor(Object color) {
        if (color instanceof String) {
            return Color.parseColor((String) color);
        } else {
            return (int) color;
        }
    }
}
