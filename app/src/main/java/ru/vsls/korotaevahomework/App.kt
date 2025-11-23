package ru.vsls.korotaevahomework

import android.app.Application
import androidx.fragment.app.Fragment
import ru.vsls.enter.presentation.EnterFragment
import ru.vsls.history.presentation.HistoryFragment
import ru.vsls.korotaevahomework.di.DaggerAppComponent
import ru.vsls.navigation.di.ComponentProvider
import ru.vsls.navigation.di.EnterComponentProvider
import ru.vsls.navigation.di.HistoryComponentProvider

class App: Application(), ComponentProvider {
    val component by lazy {
        DaggerAppComponent.builder()
            .context(this@App)
            .build()
    }

    override fun getEnterComponent(): EnterComponentProvider {
        return object : EnterComponentProvider {
            override fun inject(fragment: Fragment) {
                component.enterComponent().inject(fragment as EnterFragment)
            }
        }
    }

    override fun getHistoryComponent(): HistoryComponentProvider {
        return object : HistoryComponentProvider {
            override fun inject(fragment: Fragment) {
                component.historyComponent().inject(fragment as HistoryFragment)
            }
        }
    }

}