package com.bearya.intelliscreen

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.mmkv.MMKV

class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(object : AndroidLogAdapter(
            PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(5)
                .tag("LOGGER")
                .build()
        ) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return true
            }
        })

        MMKV.initialize(this)
        CrashReport.initCrashReport(this, "ce4dea3e47", true)
    }

}