package ru.vsls.korotaevahomework.common.navigation

sealed interface Screen {
    data object EnterScreen : Screen
    data object MainScreen : Screen
    data class FormScreen(val amount: Int, val percent: Double, val period: Int) : Screen
    data object ResultScreen: Screen
}