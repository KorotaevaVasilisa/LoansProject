package ru.vsls.korotaevahomework.screens.languages

import ru.vsls.korotaevahomework.R

sealed class AppLanguage(val titleRes: Int, val code: String) {
    object Russian : AppLanguage(R.string.russian, "ru")
    object English : AppLanguage(R.string.english, "en")
}