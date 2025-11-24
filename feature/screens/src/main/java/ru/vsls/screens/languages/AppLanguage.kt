package ru.vsls.screens.languages

import ru.vsls.screens.R

sealed class AppLanguage(val titleRes: Int, val code: String) {
    object Russian : AppLanguage(R.string.russian, "ru")
    object English : AppLanguage(R.string.english, "en")
}