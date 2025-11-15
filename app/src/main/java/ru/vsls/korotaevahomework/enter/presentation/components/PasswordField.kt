package ru.vsls.korotaevahomework.enter.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.enter.presentation.model.FieldEvent

@Composable
internal fun PasswordField(
    password: String,
    onValueChange: (FieldEvent) -> Unit,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = { onValueChange(FieldEvent.PasswordChanged(it)) },
        label = { Text(stringResource(R.string.password)) },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        supportingText = {},
        visualTransformation = if (passwordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = getVisibilityIcon(passwordVisible),
                    contentDescription = getIconVisibilityDescription(passwordVisible),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    )
}

@Composable
internal fun RepeatPasswordField(
    password: String,
    isError: Boolean,
    onValueChange: (FieldEvent) -> Unit,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = { onValueChange(FieldEvent.RepeatPasswordChanged(it)) },
        label = { Text(stringResource(R.string.replay_password)) },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        isError = isError,
        supportingText = { GetSupportingText(isError) },
        visualTransformation = if (passwordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = getVisibilityIcon(passwordVisible),
                    contentDescription = getIconVisibilityDescription(passwordVisible),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    )
}

@Composable
internal fun getVisibilityIcon(passwordVisible: Boolean): Painter {
    return if (passwordVisible)
        painterResource(R.drawable.outline_visibility)
    else
        painterResource(R.drawable.outline_visibility_off)
}

@Composable
internal fun getIconVisibilityDescription(passwordVisible: Boolean): String {
    return if (passwordVisible)
        stringResource(R.string.hide_password)
    else
        stringResource(R.string.show_password)
}

@Composable
private fun GetSupportingText(isError: Boolean) {
    if (isError)
        Text(stringResource(R.string.error_password))
}