package ru.vsls.korotaevahomework.common.navigation

interface Router {
    fun navigateTo(screen: Screen)
    fun navigateBack()
    fun replaceFragment(screen: Screen)
    fun clearBackStackAndNavigate(screen: Screen)
}
