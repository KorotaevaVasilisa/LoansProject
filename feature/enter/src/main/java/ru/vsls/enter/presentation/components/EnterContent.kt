package ru.vsls.enter.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.vsls.enter.presentation.model.EnterState
import ru.vsls.enter.presentation.model.FieldEvent
import ru.vsls.ui.LogoBlock

@Composable
internal fun EnterContent(
    state: EnterState,
    switchToLogin: () -> Unit,
    switchToRegistration: () -> Unit,
    loginUser: () -> Unit,
    registerUser: () -> Unit,
    onFieldChange: (FieldEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogoBlock(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )

        CardBlock(
            state = state,
            switchToLogin = switchToLogin,
            switchToRegistration = switchToRegistration,
            loginUser = loginUser,
            registrationUser = registerUser,
            onFieldChange = onFieldChange
        )
    }
}

