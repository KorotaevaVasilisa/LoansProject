package ru.vsls.korotaevahomework.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.presentation.EnterState

@Composable
internal fun LoginForm(state: EnterState.Login){
    OutlinedTextField(
        value = state.login,
        onValueChange = { },
        label = { Text(stringResource(R.string.lodin)) },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(12.dp))


    OutlinedTextField(
        value = state.password,
        onValueChange = { },
        label = { Text(stringResource(R.string.password)) },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(24.dp))

    Button(
        onClick = { },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
    ) {
        Text(
            stringResource(R.string.log_in),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
    }
}