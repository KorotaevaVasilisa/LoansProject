package ru.vsls.korotaevahomework

import android.app.Application
import androidx.fragment.app.Fragment
import ru.vsls.enter.presentation.EnterFragment
import ru.vsls.history.presentation.HistoryFragment
import ru.vsls.korotaevahomework.details.presentation.DetailsFragment
import ru.vsls.korotaevahomework.di.DaggerAppComponent
import ru.vsls.main.presentation.MainFragment
import ru.vsls.navigation.di.ComponentProvider
import ru.vsls.navigation.di.DetailsComponentProvider
import ru.vsls.navigation.di.EnterComponentProvider
import ru.vsls.navigation.di.HistoryComponentProvider
import ru.vsls.navigation.di.MainComponentProvider

class App : Application(), ComponentProvider {
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

    override fun getMainComponent(): MainComponentProvider {
        return object : MainComponentProvider {
            override fun inject(fragment: Fragment) {
                component.mainComponent().inject(fragment as MainFragment)
            }

        }
    }

    override fun getDetailsComponent(): DetailsComponentProvider {
        return object : DetailsComponentProvider {
            override fun inject(fragment: Fragment) {
                component.detailsComponent().inject(fragment as DetailsFragment)
            }
        }
    }

}