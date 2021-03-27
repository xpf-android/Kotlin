package org.devio.hi.ui.tab.top;

import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;

/**
 * 顶部tab信息类
 */
public class HiTabTopInfo<Color> {
    /**
     * tab类型，支持bitmap和text
     */
    public enum TabType {
        BITMAP,TEXT
    }

    //点击tab，需要切换fragment页面，持有fragment的class便于动态
    //创建fragment的实例
    public Class<? extends Fragment> fragment;
    public String name;//对应tab的名字
    public Bitmap defaultBitmap;//默认状态下的图标
    public Bitmap selectedBitmap;//选中状态下的图标


    public Color defaultColor;
    public Color tintColor;// 选中后颜色
    public TabType tabType;

    /**
     * 支持bitmap
     * @param name
     * @param defaultBitmap
     * @param selectedBitmap
     */
    public HiTabTopInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.tabType = TabType.BITMAP;
    }

    /**
     * 这两个不同的构造方法，是为了拓展性，因为tab中的内容可以通过bitmap和text两种
     * 不同的形式引入
     * @param name
     * @param defaultColor
     * @param tintColor
     */
    public HiTabTopInfo(String name, Color defaultColor, Color tintColor) {
        this.name = name;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.TEXT;
    }

}
