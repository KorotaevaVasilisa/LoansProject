package ru.vsls.korotaevahomework.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.presentation.EnterState

@Composable
internal fun RegistrationForm(
    state: EnterState.Registration,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
) {
    LoginField(
        state.login,
        isError = state.isLoginError,
        onValueChange = onValueChange
    )

    PasswordField(
        password = state.password,
        onValueChange = onValueChange
    )

    RepeatPasswordField(
        password = state.password,
        isError = state.isPasswordError,
        onValueChange = onValueChange
    )

    EnterButton(
        label = stringResource(R.string.regist_in),
        onClick = onClick
    )
}