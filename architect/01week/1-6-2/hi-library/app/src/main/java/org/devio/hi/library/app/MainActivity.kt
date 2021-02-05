package org.devio.hi.library.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.devio.hi.library.app.demo.log.HiLogDemoActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View?) {
        when(view!!.id) {
            R.id.tv_hi_log -> {
                startActivity(Intent(this, HiLogDemoActivity::class.java))
            }
        }
    }

}
