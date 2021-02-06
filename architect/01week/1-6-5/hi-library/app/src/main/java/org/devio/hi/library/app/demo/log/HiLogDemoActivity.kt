package org.devio.hi.library.app.demo.log

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hi_log_demo.*
import org.devio.hi.library.app.R
import org.devio.hi.library.log.*


class HiLogDemoActivity : AppCompatActivity() {

    var viewPrinter: HiViewPrinter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_log_demo)
        viewPrinter = HiViewPrinter(this);
        btn_log.setOnClickListener {
            printLog()
        }
        viewPrinter!!.viewProvider.showFloatingView()
    }

    private fun printLog(){
        HiLogManager.getInstance().addPrinter(viewPrinter)
        //自定义Log配置
        HiLog.log(object : HiLogConfig() {
            override fun includeTread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        }, HiLogType.E, "------", "5566")

        HiLog.a("9900")
    }
}
