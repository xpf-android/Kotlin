package org.devio.hi.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.devio.hi.app.demo.refresh.HiRefreshDemoActivity
import org.devio.hi.app.demo.tab.HiTabBottomDemoActivity
import org.devio.hi.ui.tab.bottom.HiTabBottom
import org.devio.hi.ui.tab.bottom.HiTabBottomInfo

/**
 * Kotlin和Java进行混编，混合开发
 */
class MainActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val tabBottom = findViewById<HiTabBottom>(R.id.tab_bottom)
        val homeInfo = HiTabBottomInfo(
            "首页",
            "fonts/iconfont.ttf",
            getString(R.string.if_home),
            null,
            "#ff656667",
            "#ffd44949"
        )
        tabBottom.setHiTabInfo(homeInfo)*/
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tv_hi_refresh -> {
                startActivity(Intent(this, HiRefreshDemoActivity::class.java))
            }
            R.id.tv_tab_bottom -> {
                startActivity(Intent(this, HiTabBottomDemoActivity::class.java))
            }
        }
    }
}
