package ru.vsls.details.utils

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


fun formatDate(input: String): String {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val dateTime = OffsetDateTime.parse(input, formatter)

    val day = dateTime.dayOfMonth
    val month =dateTime.monthValue
    val year =dateTime.year

    return "$day.$month.$year"
}
