package ru.vsls.korotaevahomework.common.navigation

import androidx.fragment.app.Fragment
import ru.vsls.enter.presentation.EnterFragment
import ru.vsls.history.presentation.HistoryFragment
import ru.vsls.korotaevahomework.details.presentation.DetailsFragment
import ru.vsls.korotaevahomework.form.FormFragment
import ru.vsls.korotaevahomework.menu.presentation.MenuFragment
import ru.vsls.korotaevahomework.screens.banks.BanksFragment
import ru.vsls.korotaevahomework.screens.help.HelpFragment
import ru.vsls.korotaevahomework.screens.languages.LanguagesFragment
import ru.vsls.korotaevahomework.screens.offers.OffersFragment
import ru.vsls.korotaevahomework.screens.onboarding.OnboardingFragment
import ru.vsls.korotaevahomework.screens.result.ResultFragment
import ru.vsls.main.presentation.MainFragment
import ru.vsls.navigation.Screen
import javax.inject.Inject

class ScreenFactory @Inject constructor() {
    fun createFragment(screen: Screen): Fragment {
        return when (screen) {
            is Screen.DetailsScreen -> DetailsFragment.newInstance(paramId = screen.id)
            Screen.EnterScreen -> EnterFragment()

            is Screen.FormScreen -> FormFragment.newInstance(
                amount = screen.amount,
                percent = screen.percent,
                period = screen.period
            )

            Screen.HistoryScreen -> HistoryFragment()
            Screen.MainScreen -> MainFragment()
            is Screen.ResultScreen -> ResultFragment.newInstance(
                success = screen.success,
                amount = screen.amount
            )

            Screen.MenuScreen -> MenuFragment()
            Screen.BanksScreen -> BanksFragment()
            Screen.HelpScreen -> HelpFragment()
            Screen.LanguageScreen -> LanguagesFragment()
            Screen.OffersScreen -> OffersFragment()
            Screen.OnboardingScreen -> OnboardingFragment()
        }
    }
}