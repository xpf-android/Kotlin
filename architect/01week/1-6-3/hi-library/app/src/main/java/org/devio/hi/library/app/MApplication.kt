package org.devio.hi.library.app

import android.app.Application
import com.google.gson.Gson
import org.devio.hi.library.log.HiConsolePrinter
import org.devio.hi.library.log.HiLogConfig
import org.devio.hi.library.log.HiLogManager

class MApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        HiLogManager.getInstance().init(object :HiLogConfig() {

            override fun injectJsonParser(): JsonParser {
                return JsonParser {src -> Gson().toJson(src) }
            }

            override fun getGlobalTag(): String {
                return "MApplication"
            }

            override fun enable(): Boolean {
                return true
            }
        }, HiConsolePrinter())
    }
}