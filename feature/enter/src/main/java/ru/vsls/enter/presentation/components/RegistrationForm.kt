package ru.vsls.enter.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.vsls.enter.R
import ru.vsls.enter.presentation.model.EnterState
import ru.vsls.enter.presentation.model.FieldEvent

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
        password = state.passwordRepeat,
        isError = state.isPasswordError,
        onValueChange = onFieldChange
    )

    EnterButton(
        label = stringResource(R.string.regist_in),
        onClick = onClick
    )
}