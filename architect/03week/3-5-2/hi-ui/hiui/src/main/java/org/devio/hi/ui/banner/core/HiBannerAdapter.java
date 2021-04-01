package org.devio.hi.ui.banner.core;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class HiBannerAdapter extends PagerAdapter {

    private Context mContext;
    private SparseArray<HiBannerViewHolder> mCachedViews = new SparseArray<>();
    private IHiBanner.OnBannerClickListener mBannerClickListener;
    private IBindAdapter mBinderAdapter;
    private List<? extends HiBannerMo> models;

    /**
     * 是否开启自动轮播
     */
    private boolean mAutoPlay = true;
    /**
     * 非自动轮播状态下是否可以循环切换
     */
    private boolean mLoop = false;

    private int mLayoutResId = -1;

    public HiBannerAdapter(Context context) {
        this.mContext = context;
    }

    public void setBannerData(@NonNull List<? extends HiBannerMo> models) {
        this.models = models;
        //初始化数据
        initCachedView();
        notifyDataSetChanged();
    }


    public void setOnBannerClickListener(IHiBanner.OnBannerClickListener mBannerClickListener) {
        this.mBannerClickListener = mBannerClickListener;
    }

    public void setBinderAdapter(IBindAdapter mBinderAdapter) {
        this.mBinderAdapter = mBinderAdapter;
    }

    public void setAutoPlay(boolean mAutoPlay) {
        this.mAutoPlay = mAutoPlay;
    }

    public void setLoop(boolean mLoop) {
        this.mLoop = mLoop;
    }

    public void setLayoutResId(int mLayoutResId) {
        this.mLayoutResId = mLayoutResId;
    }

    @Override
    public int getCount() {
        //无限轮播关键点
        return mAutoPlay ? Integer.MAX_VALUE : (mLoop ? Integer.MAX_VALUE : getRealCount());
    }

    /**
     * 获取Banner页面数量
     *
     * @return
     */
    public int getRealCount() {
        return models == null ? 0 : models.size();
    }

    /**
     * 获取初始展示的item位置
     */
    public int getFirstItemIndex() {
        return Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % getRealCount();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = position;
        if (getRealCount() > 0) {
            realPosition = position % getRealCount();
        }
        HiBannerViewHolder viewHolder = mCachedViews.get(realPosition);
        //如果已经添加到容器，要先移除
        if (container.equals(viewHolder.rootView.getParent())) {
            container.removeView(viewHolder.rootView);
        }
        //数据绑定
        onBind(viewHolder, models.get(realPosition), realPosition);

        if (viewHolder.rootView.getParent() != null) {
            ((ViewGroup) viewHolder.rootView.getParent()).removeView(viewHolder.rootView);
        }
        container.addView(viewHolder.rootView);
        return viewHolder.rootView;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        //让item每次都会刷新
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }

    protected void onBind(@NonNull final HiBannerViewHolder viewHolder, @NonNull final HiBannerMo bannerMo, final int position) {
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBannerClickListener != null) {
                    mBannerClickListener.onBannerClick(viewHolder, bannerMo, position);
                }
            }
        });
        if (mBinderAdapter != null) {
            mBinderAdapter.onBind(viewHolder,bannerMo,position);
        }
    }

    private void initCachedView() {
        mCachedViews = new SparseArray<>();
        int modelsSize = models.size();
        for (int i = 0; i < modelsSize; i++) {
            HiBannerViewHolder viewHolder = new HiBannerViewHolder(createView(LayoutInflater.from(mContext), null));
            mCachedViews.put(i, viewHolder);
        }
    }

    private View createView(LayoutInflater layoutInflater, ViewGroup parent) {
        if (mLayoutResId == -1) {
            throw new IllegalArgumentException("you must be set setLayoutResId first");
        }
        return layoutInflater.inflate(mLayoutResId, parent, false);
    }

    public static class HiBannerViewHolder {
        private SparseArray<View> viewSparseArray;
        View rootView;

        public HiBannerViewHolder(View rootView) {
            this.rootView = rootView;
        }

        public View getRootView() {
            return rootView;
        }

        public <V extends View> V findViewById(int id){
            if (!(rootView instanceof ViewGroup)) {
                return (V) rootView;
            }
            if (this.viewSparseArray == null) {
                this.viewSparseArray = new SparseArray<>(1);
            }
            V childView = (V) viewSparseArray.get(id);
            if (childView == null) {
                childView = rootView.findViewById(id);
                this.viewSparseArray.put(id, childView);
            }
            return childView;
        }
    }
}
