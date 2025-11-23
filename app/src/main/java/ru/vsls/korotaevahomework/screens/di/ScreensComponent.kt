package ru.vsls.korotaevahomework.screens.di

import dagger.Component
import ru.vsls.korotaevahomework.screens.banks.BanksFragment
import ru.vsls.korotaevahomework.screens.help.HelpFragment
import ru.vsls.korotaevahomework.screens.languages.LanguagesFragment
import ru.vsls.korotaevahomework.screens.offers.OffersFragment
import ru.vsls.korotaevahomework.screens.result.ResultFragment
import ru.vsls.navigation.Router
import ru.vsls.shared.network.domain.TokenRepository
import javax.inject.Inject

@Component(dependencies = [ScreensComponent.Deps::class])
interface ScreensComponent {
    fun inject(resultFragment: ResultFragment)
    fun inject(offersFragment: OffersFragment)
    fun inject(languagesFragment: LanguagesFragment)
    fun inject(helpFragment: HelpFragment)
    fun inject(banksFragment: BanksFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: ScreensComponent.Deps): Builder
        fun build(): ScreensComponent
    }

    class Deps @Inject constructor(
        val tokenRepository: TokenRepository,
        val router: Router,
    )
}