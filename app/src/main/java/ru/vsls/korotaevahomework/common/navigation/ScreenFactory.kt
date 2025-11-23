package ru.vsls.korotaevahomework.common.navigation

import androidx.fragment.app.Fragment
import ru.vsls.enter.presentation.EnterFragment
import ru.vsls.korotaevahomework.details.presentation.DetailsFragment
import ru.vsls.korotaevahomework.form.FormFragment
import ru.vsls.korotaevahomework.history.presentation.HistoryFragment
import ru.vsls.korotaevahomework.main.presentation.MainFragment
import ru.vsls.korotaevahomework.menu.presentation.MenuFragment
import ru.vsls.korotaevahomework.result.ResultFragment
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
            Screen.ResultScreen -> ResultFragment()
            Screen.MenuScreen -> MenuFragment()
        }
    }
}