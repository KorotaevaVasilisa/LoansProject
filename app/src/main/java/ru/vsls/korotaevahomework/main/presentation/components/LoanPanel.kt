package ru.vsls.korotaevahomework.main.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R

@Composable
fun LoanPanel(
    loanValue: Int,
    sliderValue: Float,
    maxAmount: Int,
    period: Int,
    percent: Int,
    onContinueClick: () -> Unit,
    onValueChange: (Float) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            stringResource(R.string.title_loan_panel),
            style = MaterialTheme.typography.titleLarge,
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                LoanSizeText(value = loanValue)

                LoanSlider(
                    maxAmount = maxAmount,
                    sliderValue = sliderValue,
                    onValueChange = onValueChange
                )

                RangeText(maxAmount = maxAmount)
                HorizontalDivider(color = MaterialTheme.colorScheme.secondary)

                ConditionsText(
                    percent = percent,
                    period = period
                )

                ContinueButton(onClick = onContinueClick)
            }
        }
    }
}

@Composable
private fun ConditionsText(
    percent: Int,
    period: Int,
) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            text = stringResource(R.string.conditions),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = stringResource(R.string.condition, percent, period),
        )
    }
}

@Composable
private fun LoanSizeText(
    value: Int,
) {
    Row {
        Text(
            text = stringResource(R.string.price_form, value),
            style = MaterialTheme.typography.titleLarge
        )
        Icon(
            painter = painterResource(R.drawable.edit_icon),
            contentDescription = stringResource(R.string.edit_loan),
            modifier = Modifier
                .size(24.dp)
                .padding(horizontal = 16.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ContinueButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = stringResource(R.string.continue_click),
        )
    }
}

@Composable
private fun RangeText(
    maxAmount: Int,
    minAmount: Int = 0,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.price_form, minAmount),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = stringResource(R.string.price_form, maxAmount),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LoanPanel(2000, 2000f, 15000, 20, 44, {}, {})
}