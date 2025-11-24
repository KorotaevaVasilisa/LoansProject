package ru.vsls.korotaevahomework.common

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.vsls.korotaevahomework.MainActivity
import ru.vsls.korotaevahomework.R
import ru.vsls.navigation.ActivityHolder
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRouter @Inject constructor(private val factory: ScreenFactory) : Router, ActivityHolder {

    private var activity: MainActivity? = null

    override fun attachActivity(activity: FragmentActivity) {
        this.activity = activity as MainActivity?
    }

    override fun detachActivity() {
        this.activity = null
    }

    override fun navigateTo(screen: Screen) {
        val fragment = factory.createFragment(screen)

        activity?.let { safeActivity ->
            safeActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }


    override fun replaceFragment(screen: Screen) {
        val fragment = factory.createFragment(screen)

        activity?.let { safeActivity ->
            safeActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit()
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