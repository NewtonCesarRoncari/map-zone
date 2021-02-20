package com.newton.zone

import android.app.Application
import com.newton.zone.extension.TypefaceUtil

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val typefaceUtil = TypefaceUtil()
        typefaceUtil.overrideFonts(
            this,
            "SERIF",
            "fonts/OpenSans-Semibold.ttf"
        )
    }
}