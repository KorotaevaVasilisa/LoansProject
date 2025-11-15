package ru.vsls.korotaevahomework.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.presentation.model.EnterState
import ru.vsls.korotaevahomework.presentation.model.FieldEvent

@Composable
internal fun RegistrationForm(
    state: EnterState.Registration,
    onFieldChange: (FieldEvent) -> Unit,
    onClick: () -> Unit,
) {
    LoginField(
        state.login,
        isError = state.isLoginError,
        onValueChange = onFieldChange
    )

    PasswordField(
        password = state.password,
        onValueChange = onFieldChange
    )

    RepeatPasswordField(
        password = state.password,
        isError = state.isPasswordError,
        onValueChange = onFieldChange
    )

    EnterButton(
        label = stringResource(R.string.regist_in),
        onClick = onClick
    )
}