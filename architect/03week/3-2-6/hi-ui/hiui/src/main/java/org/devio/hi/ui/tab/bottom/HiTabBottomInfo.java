package org.devio.hi.ui.tab.bottom;

import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;

/**
 * 底部tab信息类
 */
public class HiTabBottomInfo<Color> {
    /**
     * tab类型，支持bitmap和icon
     */
    public enum TabType {
        BITMAP,ICON
    }

    //点击tab，需要切换fragment页面，持有fragment的class便于动态
    //创建fragment的实例
    public Class<? extends Fragment> fragment;
    public String name;//对应tab的名字，首页、收藏、推荐、我的等等
    public Bitmap defaultBitmap;//默认状态下的图标
    public Bitmap selectedBitmap;//选中状态下的图标
    public String iconFont;

    /**
     * Tips: 在java代码中直接设置iconfont字符串无效，需要定义在string.xml
     */

    public String defaultIconName;
    public String selectedIconName;
    public Color defaultColor;
    public Color tintColor;// 选中后颜色
    public TabType tabType;

    /**
     * 支持bitmap
     * @param name
     * @param defaultBitmap
     * @param selectedBitmap
     */
    public HiTabBottomInfo(String name, Bitmap defaultBitmap, Bitmap selectedBitmap) {
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.tabType = TabType.BITMAP;
    }

    /**
     * 支持ICON，这两个不同的构造方法，是为了拓展性，因为tab中的图片可以通过bitmap和icon两种
     * 不同的形式引入
     * @param name
     * @param iconFont
     * @param defaultIconName
     * @param selectedIconName
     * @param defaultColor
     * @param tintColor
     */
    public HiTabBottomInfo(String name, String iconFont, String defaultIconName, String selectedIconName, Color defaultColor, Color tintColor) {
        this.name = name;
        this.iconFont = iconFont;
        this.defaultIconName = defaultIconName;
        this.selectedIconName = selectedIconName;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.ICON;
    }

}
