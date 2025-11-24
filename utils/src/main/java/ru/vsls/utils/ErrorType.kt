package ru.vsls.utils

import android.content.Context

enum class ErrorType {
    USER_NOT_FOUND,
    UNKNOWN,
    NO_INTERNET,
    UNAUTHORIZED,
    BAD_REQUEST,
    SERVER,
    NON_VALID,
    FAILED_STATE,
    UNKNOWN_LOAN,
    NON_RUSSIAN_SYMBOLS,
    NON_VALID_PHONE,
    EMPTY_FIELD
}

fun getErrorMessage(errorType: ErrorType, context: Context): String =
    when (errorType) {
        ErrorType.USER_NOT_FOUND -> context.getString(R.string.not_found_exception)
        ErrorType.UNKNOWN -> context.getString(R.string.unknown_exception)
        ErrorType.NO_INTERNET -> context.getString(R.string.no_internet_exception)
        ErrorType.UNAUTHORIZED -> context.getString(R.string.unauthorized_exception)
        ErrorType.BAD_REQUEST -> context.getString(R.string.bad_request_exception)
        ErrorType.SERVER -> context.getString(R.string.server_exception)
        ErrorType.NON_VALID -> context.getString(R.string.non_valid_fields)
        ErrorType.FAILED_STATE -> context.getString(R.string.failed_state)
        ErrorType.UNKNOWN_LOAN -> context.getString(R.string.unknowing_loan)
        ErrorType.NON_RUSSIAN_SYMBOLS -> context.getString(R.string.non_russian_symbols)
        ErrorType.NON_VALID_PHONE -> context.getString(R.string.non_valid_phone)
        ErrorType.EMPTY_FIELD -> context.getString(R.string.empty_field)
    }