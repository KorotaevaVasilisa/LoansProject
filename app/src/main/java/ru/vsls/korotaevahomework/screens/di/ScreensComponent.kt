package ru.vsls.korotaevahomework.screens.di

import android.content.Context
import dagger.Component
import ru.vsls.korotaevahomework.screens.banks.BanksFragment
import ru.vsls.korotaevahomework.screens.help.HelpFragment
import ru.vsls.korotaevahomework.screens.languages.LanguagesFragment
import ru.vsls.korotaevahomework.screens.menu.presentation.MenuFragment
import ru.vsls.korotaevahomework.screens.offers.OffersFragment
import ru.vsls.korotaevahomework.screens.onboarding.OnboardingFragment
import ru.vsls.korotaevahomework.screens.result.ResultFragment
import ru.vsls.navigation.Router
import ru.vsls.shared.network.domain.TokenRepository
import javax.inject.Inject

@Component(modules = [MenuModule::class],
    dependencies = [ScreensComponent.Deps::class])
interface ScreensComponent {
    fun inject(resultFragment: ResultFragment)
    fun inject(offersFragment: OffersFragment)
    fun inject(languagesFragment: LanguagesFragment)
    fun inject(helpFragment: HelpFragment)
    fun inject(banksFragment: BanksFragment)
    fun inject(onboardingFragment: OnboardingFragment)
    fun inject(menuFragment: MenuFragment)


    @Component.Builder
    interface Builder {
        fun deps(deps: Deps): Builder
        fun build(): ScreensComponent
    }

    class Deps @Inject constructor(
        val context: Context,
        val tokenRepository: TokenRepository,
        val router: Router,
    )
}