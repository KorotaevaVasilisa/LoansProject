package ru.vsls.navigation

sealed interface Screen {
    data object EnterScreen : Screen
    data object MainScreen : Screen
    data class FormScreen(val amount: Int, val percent: Double, val period: Int) : Screen
    data class ResultScreen(val success: Boolean, val amount: Int) : Screen

    data object HistoryScreen : Screen

    data class DetailsScreen(val id: Int) : Screen
    data object MenuScreen : Screen
}