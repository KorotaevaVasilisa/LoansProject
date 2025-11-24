package ru.vsls.navigation.di

import android.content.Context
import androidx.fragment.app.Fragment

fun Context.getComponentProvider(): ComponentProvider {
    val app = applicationContext
    return app as ComponentProvider
}

interface ComponentProvider {
    fun getEnterComponent(): EnterComponentProvider
    fun getHistoryComponent(): HistoryComponentProvider
    fun getMainComponent(): MainComponentProvider
    fun getDetailsComponent(): DetailsComponentProvider
    fun getFormComponent(): FormComponentProvider
    fun getScreensComponent(): ScreensComponentProvider

}

interface ScreensComponentProvider {
    fun inject(fragment: Fragment)
}

interface FormComponentProvider {
    fun inject(fragment: Fragment)
}

interface EnterComponentProvider {
    fun inject(fragment: Fragment)
}

interface HistoryComponentProvider {
    fun inject(fragment: Fragment)
}

interface MainComponentProvider {
    fun inject(fragment: Fragment)
}

interface DetailsComponentProvider {
    fun inject(fragment: Fragment)
}
