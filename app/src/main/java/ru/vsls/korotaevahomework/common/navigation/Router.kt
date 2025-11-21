package ru.vsls.korotaevahomework.common.navigation

import ru.vsls.korotaevahomework.MainActivity

interface Router {
    fun navigateTo(screen: Screen)
    fun navigateBack()
    fun replaceFragment(screen: Screen)
    fun clearBackStackAndNavigate(screen: Screen)
}

interface ActivityHolder {
    fun attachActivity(activity: MainActivity)
    fun detachActivity()
}
