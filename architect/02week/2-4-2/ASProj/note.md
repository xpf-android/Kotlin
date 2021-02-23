需求分析

能够提供通用的API
支持透明度和底部透出
支持Tab中间高度超过，凸起布局效果
支持iconfont与bitmap

疑难点

透明度和底部透出，列表壳渲染高度问题

中间高度超过，凸起布局

<img src="https://github.com/xpf-android/Kotlin/raw/master/architect/02week/2-4-2/ASProj/images/HiTabBottomLayout封装与应用03.jpg" width="380" height="700"/>


<div align=center><img width="500" height="780" src="https://github.com/xpf-android/Kotlin/raw/master/architect/02week/2-4-2/ASProj/images/底部导航.png"/></div>

在开发人员选项中设置“不保留活动“选项，即用户离开后即消耗每个活动，导致应用切换到后台，再次切回前台时，会重新创建活动，执行的逻辑会显示默认的fragment，但是切换到后台时的fragment并没有销毁，导致发生重叠，效果如下。解决思路就是在onSaveInstanceState方法中保存当前fragment的信息(比如index)，在正常的初始化逻辑中，将默认显示fragment改为之前保存的fragment。具体逻辑实现参考项目代码。



![](I:\imooc\code\architect\backup\ASProj\images\fragment问题解决.gif)

