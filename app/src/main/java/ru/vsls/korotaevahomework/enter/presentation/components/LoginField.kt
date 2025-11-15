package ru.vsls.korotaevahomework.enter.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.enter.presentation.model.FieldEvent

@Composable
internal fun LoginField(
    login: String,
    isError: Boolean,
    onValueChange: (FieldEvent) -> Unit,
) {
    OutlinedTextField(
        value = login,
        onValueChange = { onValueChange(FieldEvent.LoginChanged(it)) },
        label = { Text(stringResource(R.string.login)) },
        modifier = Modifier
            .fillMaxWidth(),
        isError = isError,
        supportingText = { GetSupportingText(isError) },
        singleLine = true
    )
}

@Composable
private fun GetSupportingText(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.error_login))
    }
}