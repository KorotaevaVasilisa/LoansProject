package ru.vsls.navigation.di

import android.content.Context
import androidx.fragment.app.Fragment

interface ComponentProvider {
    fun getEnterComponent(): EnterComponentProvider

}

interface EnterComponentProvider {
    fun inject(fragment: Fragment)
}


fun Context.getComponentProvider(): ComponentProvider {
    val app = applicationContext
    return app as ComponentProvider
}