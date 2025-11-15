package ru.vsls.korotaevahomework

import android.app.Application
import ru.vsls.korotaevahomework.di.DaggerAppComponent

class App: Application(){
    val component by lazy {
        DaggerAppComponent.builder()
            .context(this@App)
            .build()
    }
}