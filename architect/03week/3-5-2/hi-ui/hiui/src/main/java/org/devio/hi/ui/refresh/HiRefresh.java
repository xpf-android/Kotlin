package org.devio.hi.ui.refresh;

public interface HiRefresh {
    /**
     * 刷新时是否禁止滚动
     *
     * @param disableRefreshScroll
     */
    void setDisableRefreshScroll(boolean disableRefreshScroll);

    /**
     * 刷新完成
     */
    void refreshFinished();

    /**
     * 设置下拉刷新的监听器
     *
     * @param hiRefreshListener
     */
    void setRefreshListener(HiRefreshListener hiRefreshListener);

    /**
     * 设置下拉刷新的视图
     *
     * @param hiOverView 下拉刷新的视图
     */
    void setRefreshOverView(HiOverView hiOverView);

    interface HiRefreshListener {
        void onRefresh();

        boolean enableRefresh();
    }
}
