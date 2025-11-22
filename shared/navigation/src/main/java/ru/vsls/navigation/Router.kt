package ru.vsls.navigation

import androidx.fragment.app.FragmentActivity

interface Router {
    fun navigateTo(screen: Screen)
    fun navigateBack()
    fun replaceFragment(screen: Screen)
    fun clearBackStackAndNavigate(screen: Screen)
}

interface ActivityHolder {
    fun attachActivity(activity: FragmentActivity)
    fun detachActivity()
}