package ru.vsls.korotaevahomework.common.utils

import java.time.DayOfWeek
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun formatDate(input: String): String {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val dateTime = OffsetDateTime.parse(input, formatter)

    val months = listOf(
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    )

    val days = mapOf(
        DayOfWeek.MONDAY to "пн",
        DayOfWeek.TUESDAY to "вт",
        DayOfWeek.WEDNESDAY to "ср",
        DayOfWeek.THURSDAY to "чт",
        DayOfWeek.FRIDAY to "пт",
        DayOfWeek.SATURDAY to "сб",
        DayOfWeek.SUNDAY to "вс"
    )

    val day = dateTime.dayOfMonth
    val month = months[dateTime.monthValue - 1]
    val weekDay = days[dateTime.dayOfWeek] ?: ""

    return "$day $month, $weekDay"
}
