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
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R

@Composable
internal fun UserLoanPanel(
    id: Int,
    date: String,
    period: Int,
    percent: Int,
    amount: Int,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ContentText(
                value = stringResource(R.string.number_loan, id),
                label = stringResource(R.string.id_loan)
            )

            ContentText(
                value = date,
                label = stringResource(R.string.date_loan)
            )

            ContentText(
                value = "$period",
                label = stringResource(R.string.period_loan)
            )

            ContentText(
                value = "$percent %",
                label = stringResource(R.string.percent)
            )

            ContentText(
                value = stringResource(R.string.price_form, amount),
                label = stringResource(R.string.sum_loan)
            )
        }
    }
}