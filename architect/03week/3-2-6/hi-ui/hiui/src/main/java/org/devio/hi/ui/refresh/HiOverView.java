package org.devio.hi.ui.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.devio.hi.library.util.HiDisplayUtil;

/**
 * 下拉刷新的Overlay视图，可以重载这个类来定义自己的Overlay
 */
public abstract class HiOverView extends FrameLayout {
    public enum HiRefreshState{
        /**
         * 初始态
         */
        STATE_INIT,
        /**
         * Header展示的状态
         */
        STATE_VISIBLE,
        /**
         * 刷新中状态
         */
        STATE_REFRESH,
        /**
         * 超出可刷新距离的状态
         */
        STATE_OVER,
        /**
         * 超出刷新位置松开手后的状态
         */
        STATE_OVER_RELEASE
    }

    protected HiRefreshState mState = HiRefreshState.STATE_INIT;

    /**
     * 触发下拉刷新的最小距离
     */
    public int mPullRefreshHeight;

    /**
     * 最小阻尼
     */
    public float minDamp = 1.6f;//下拉距离和滚动距离的比例系数
    /**
     * 最大阻尼
     */
    public float maxDamp = 2.2f;

    public HiOverView(@NonNull Context context) {
        this(context, null);
    }

    public HiOverView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HiOverView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        preInit();
    }

    protected void preInit() {
        mPullRefreshHeight = HiDisplayUtil.dp2px(66, getResources());
        init();
    }

    /**
     * 初始化
     */
    public abstract void init();

    protected abstract void onScroll(int scrollY, int pullRefreshHeight);

    /**
     * 显示Overlay
     */
    protected abstract void onVisible();

    /**
     * 超过Overlay，释放就会加载
     */
    public abstract void onOver();

    /**
     * 开始刷新
     */
    public abstract void onRefresh();

    /**
     * 加载完成
     */
    public abstract void onFinish();

    /**
     * 设置状态
     * @param state 状态
     */
    public void setState(HiRefreshState state) {
        this.mState = state;
    }

    /**
     * 获取状态
     * @return
     */
    public HiRefreshState getState() {
        return mState;
    }
}
