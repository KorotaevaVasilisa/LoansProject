package ru.vsls.form.presentation.model

import ru.vsls.utils.ErrorType

data class FormState(
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val nameError: ErrorType? = null,
    val surnameError: ErrorType? = null,
    val phoneError: ErrorType? = null,
    val isLoading: Boolean = false,
)