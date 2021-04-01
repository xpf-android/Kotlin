package org.devio.hi.ui.banner.core;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

/**
 * 实现了自动滚动(翻页)的ViewPager
 */
public class HiViewPager extends ViewPager {


    private int mIntervalTime;//滚动的时间间隔
    private boolean mAutoPlay;//是否开启自动轮播
    private boolean isLayout;//标志位，是否调用过onLayout方法
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            //切换到下一个
            next();
            mHandler.postDelayed(this, mIntervalTime);
        }
    };


    public HiViewPager(@NonNull Context context) {
        super(context);
    }

    public HiViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAutoPlay(boolean autoPlay) {
        this.mAutoPlay = autoPlay;
        if (!mAutoPlay) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    /**
     * 设置ViewPager的滚动速度
     * @param duration page切换的时间长度
     */
    public void setScrollDuration(int duration) {
        try {
            Field scrollerField = ViewPager.class.getField("mScroller");
            scrollerField.setAccessible(true);
            scrollerField.set(this, new HiBannerScroller(getContext(), duration));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIntervalTime(int intervalTime) {
        this.mIntervalTime = intervalTime;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                start();
                break;
            default:
                stop();
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        isLayout = true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isLayout && getAdapter() != null && getAdapter().getCount() > 0) {
            try {
                //fix 使用RecyclerView + ViewPager bug
                Field mScroller = ViewPager.class.getDeclaredField("mFirstLayout");
                mScroller.setAccessible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (((Activity) getContext()).isFinishing()) {
            super.onDetachedFromWindow();
        }
        stop();
    }

    /**
     * 开启滚动
     */
    public void start() {
        mHandler.removeCallbacksAndMessages(null);
        if (mAutoPlay) {
            mHandler.postDelayed(mRunnable, mIntervalTime);
        }
    }


    public void stop() {
        mHandler.removeCallbacksAndMessages(null);
    }


    /**
     * 设置下一个要显示的item，并返回item的position
     * public方法放在前面，private方法放在后面
     *
     * @return 下一个要显示的itemPosition
     */
    private int next() {
        int nextPosition = -1;
        if (getAdapter() == null || getAdapter().getCount() <= 1) {
            stop();//停止自动播放
            return nextPosition;
        }
        nextPosition = getCurrentItem() + 1;
        //下一个索引大于adapter的view的最大数量时重新开始
        if (nextPosition >= getAdapter().getCount()) {
            //获取第一个item的索引
            nextPosition = ((HiBannerAdapter) getAdapter()).getFirstItemIndex();
        }
        setCurrentItem(nextPosition, true);
        return nextPosition;
    }


}
