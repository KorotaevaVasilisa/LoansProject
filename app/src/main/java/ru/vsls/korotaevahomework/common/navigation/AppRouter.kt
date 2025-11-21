package ru.vsls.korotaevahomework.common.navigation

import androidx.fragment.app.FragmentManager
import ru.vsls.korotaevahomework.MainActivity
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.enter.presentation.EnterFragment
import ru.vsls.korotaevahomework.form.FormFragment
import ru.vsls.korotaevahomework.main.presentation.MainFragment
import javax.inject.Inject

class AppRouter @Inject constructor() : Router, ActivityHolder {

    private var activity: MainActivity? = null

    override fun attachActivity(activity: MainActivity) {
        this.activity = activity
    }

    override fun detachActivity() {
        this.activity = null
    }

    override fun navigateTo(screen: Screen) {
        activity?.let { safeActivity ->
            when (screen) {
                is Screen.EnterScreen -> {
                    val fragment = EnterFragment()
                    safeActivity.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                is Screen.FormScreen -> {
                    val fragment =
                        FormFragment.newInstance(screen.amount, screen.percent, screen.period)
                    safeActivity.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                Screen.MainScreen -> {
                    val fragment = MainFragment()
                    safeActivity.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    override fun replaceFragment(screen: Screen) {
        activity?.let { safeActivity ->
            when (screen) {

                Screen.EnterScreen -> {
                    val fragment = EnterFragment()
                    safeActivity.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment)
                        .commit()
                }

                is Screen.FormScreen -> {
                    val fragment =
                        FormFragment.newInstance(screen.amount, screen.percent, screen.period)
                    safeActivity.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment)
                        .commit()
                }

                Screen.MainScreen -> {
                    val fragment = MainFragment()
                    safeActivity.supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment)
                        .commit()
                }
            }
        }
    }

    override fun navigateBack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun clearBackStackAndNavigate(screen: Screen) {
        activity?.let { safeActivity ->
            safeActivity.supportFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            navigateTo(screen)
        }
    }
}