package org.devio.hi.library.app

import android.app.Application
import org.devio.hi.library.log.HiLogConfig
import org.devio.hi.library.log.HiLogManager

class MApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        HiLogManager.getInstance().init(object :HiLogConfig() {
            override fun getGlobalTag(): String {
                return "MApplication"
            }

            override fun enable(): Boolean {
                return true
            }
        })
    }
}