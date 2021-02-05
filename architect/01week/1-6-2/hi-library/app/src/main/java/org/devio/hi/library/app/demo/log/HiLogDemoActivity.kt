package org.devio.hi.library.app.demo.log

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hi_log_demo.*
import org.devio.hi.library.app.R
import org.devio.hi.library.log.HiLog
import org.devio.hi.library.log.HiLogConfig
import org.devio.hi.library.log.HiLogManager
import org.devio.hi.library.log.HiLogType


class HiLogDemoActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_log_demo)

        btn_log.setOnClickListener {
            printLog()
        }
    }

    private fun printLog(){
        HiLog.a("9900")
    }
}
