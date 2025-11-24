package ru.vsls.korotaevahomework

import android.app.Application
import androidx.fragment.app.Fragment
import ru.vsls.details.presentation.DetailsFragment
import ru.vsls.enter.presentation.EnterFragment
import ru.vsls.form.presentation.FormFragment
import ru.vsls.history.presentation.HistoryFragment
import ru.vsls.korotaevahomework.di.DaggerAppComponent
import ru.vsls.screens.banks.BanksFragment
import ru.vsls.screens.help.HelpFragment
import ru.vsls.screens.languages.LanguagesFragment
import ru.vsls.screens.menu.presentation.MenuFragment
import ru.vsls.screens.offers.OffersFragment
import ru.vsls.screens.onboarding.OnboardingFragment
import ru.vsls.screens.result.ResultFragment
import ru.vsls.main.presentation.MainFragment
import ru.vsls.navigation.di.ComponentProvider
import ru.vsls.navigation.di.DetailsComponentProvider
import ru.vsls.navigation.di.EnterComponentProvider
import ru.vsls.navigation.di.FormComponentProvider
import ru.vsls.navigation.di.HistoryComponentProvider
import ru.vsls.navigation.di.MainComponentProvider
import ru.vsls.navigation.di.ScreensComponentProvider

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

    override fun getFormComponent(): FormComponentProvider {
        return object : FormComponentProvider {
            override fun inject(fragment: Fragment) {
                component.formComponent().inject(fragment as FormFragment)
            }
        }
    }

    override fun getScreensComponent(): ScreensComponentProvider {
        return object : ScreensComponentProvider {
            override fun inject(fragment: Fragment) {
                when (fragment) {
                    is MenuFragment -> component.screensComponent().inject(fragment)
                    is HelpFragment -> component.screensComponent().inject(fragment)
                    is OnboardingFragment -> component.screensComponent().inject(fragment)
                    is OffersFragment -> component.screensComponent().inject(fragment)
                    is ResultFragment -> component.screensComponent().inject(fragment)
                    is LanguagesFragment -> component.screensComponent().inject(fragment)
                    is BanksFragment -> component.screensComponent().inject(fragment)
                }
            }
        }
    }
}