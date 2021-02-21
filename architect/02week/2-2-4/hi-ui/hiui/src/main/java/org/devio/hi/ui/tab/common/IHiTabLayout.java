package org.devio.hi.ui.tab.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * 最外层容器接口
 * Tab 可以是底部导航tab，也可以是顶部导航tab
 * D 要显示的数据
 */
public interface IHiTabLayout<Tab extends ViewGroup, D> {
    Tab findTab(@NonNull D data);

    /**
     * 给tab添加监听器
     * @param listener
     */
    void addTabSelectedChangeListener(OnTabSelectedListener<D> listener);

    /**
     * 默认选中
     * @param defaultInfo
     */
    void defaultSelected(@NonNull D defaultInfo);

    /**
     * (数据)初始化
     * @param infoList
     */
    void inflateInfo(@NonNull List<D> infoList);

    interface OnTabSelectedListener<D> {
        /**
         * tab选中状态改变监听
         * 即选中的tab发生改变触发的监听器执行的方法
         * @param index 选中的tab的索引
         * @param prevInfo 上一个tab的数据(变为不选中状态)
         * @param nextInfo 下一个tab的数据(变为选中状态)
         */
        void onTabSelectedChange(int index, @Nullable D prevInfo, @NonNull D nextInfo);
    }



}
