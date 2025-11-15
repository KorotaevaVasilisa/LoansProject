package ru.vsls.korotaevahomework.enter.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.enter.presentation.model.EnterState
import ru.vsls.korotaevahomework.enter.presentation.model.FieldEvent

@Composable
internal fun CardBlock(
    state: EnterState,
    switchToLogin: () -> Unit,
    switchToRegistration: () -> Unit,
    loginUser: () -> Unit,
    registrationUser: () -> Unit,
    onFieldChange: (FieldEvent) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                LoginTextButton(
                    modifier = Modifier.weight(1f),
                    switchToLogin = switchToLogin,
                    enabled = state is EnterState.Registration
                )

                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight(),
                    thickness = 2.dp
                )

                RegistrationTextButton(
                    modifier = Modifier.weight(1f),
                    switchToRegistration = { switchToRegistration() },
                    enabled = state is EnterState.Login,
                )
            }
            when (state) {
                is EnterState.Login -> LoginForm(
                    state = state,
                    onFieldChange = onFieldChange,
                    onClick = loginUser
                )

                is EnterState.Registration -> RegistrationForm(
                    state = state,
                    onFieldChange = onFieldChange,
                    onClick = registrationUser
                )

                else -> {}
            }
        }
    }
}

@Composable
private fun LoginTextButton(
    modifier: Modifier,
    switchToLogin: () -> Unit,
    enabled: Boolean,
) {
    TextButton(
        modifier = modifier,
        onClick = { switchToLogin() },
        enabled = enabled,
    ) {
        Text(
            text = stringResource(R.string.enter)
        )
    }
}

@Composable
private fun RegistrationTextButton(
    modifier: Modifier,
    switchToRegistration: () -> Unit,
    enabled: Boolean,
) {
    TextButton(
        modifier = modifier,
        onClick = { switchToRegistration() },
        enabled = enabled,
    ) {
        Text(
            text = stringResource(R.string.registration)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginFormPreview() {
    CardBlock(state = EnterState.Registration(), {}, {}, {}, {}, {})
}