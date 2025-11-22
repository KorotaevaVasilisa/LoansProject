package ru.vsls.korotaevahomework.details.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R

@Composable
fun UserPanel(name: String, surname: String, number: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ContentText(
                value = name,
                label = stringResource(R.string.name)
            )

            ContentText(
                value = surname,
                label = stringResource(R.string.surname)
            )

            ContentText(
                value = number,
                label = stringResource(R.string.number)
            )
        }
    }
}

@Preview
@Composable
fun DetailsContentPreview() {
    UserPanel(
        name = "John",
        surname = "Doe",
        number = "1234567890"
    )
}