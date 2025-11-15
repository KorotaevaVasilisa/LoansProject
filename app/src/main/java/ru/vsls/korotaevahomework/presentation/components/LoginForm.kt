package ru.vsls.korotaevahomework.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.presentation.EnterState

@Composable
internal fun LoginForm(
    state: EnterState.Login,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
) {

    LoginField(
        login = state.login,
        isError = state.isLoginError,
        onValueChange = onValueChange
    )

    PasswordField(
        password = state.password,
        onValueChange = onValueChange
    )

    EnterButton(label = stringResource(R.string.enter), onClick = onClick)
}

