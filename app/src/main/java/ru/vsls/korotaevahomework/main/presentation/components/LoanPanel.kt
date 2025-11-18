package ru.vsls.korotaevahomework.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanPanel(
    sliderValue: Float,
    getLoan: () -> String,
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

                LoanSizeText(getLoan = getLoan)

                Slider(
                    modifier = Modifier.fillMaxWidth(),
                    value = sliderValue,
                    valueRange = 0f..10000f,
                    onValueChange = onValueChange,
                    thumb = {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(
                                    MaterialTheme.colorScheme.inverseOnSurface,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.outline_visibility),
                                contentDescription = stringResource(R.string.thumb),
                                modifier = Modifier.size(ButtonDefaults.IconSize),
                                tint = MaterialTheme.colorScheme.background
                            )
                        }
                    })

                RangeText()
                HorizontalDivider(color = MaterialTheme.colorScheme.onSurfaceVariant)

                ConditionsText()

                ContinueButton(onClick = onContinueClick)
            }
        }
    }
}

@Composable
private fun ConditionsText() {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            text = stringResource(R.string.conditions),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = stringResource(R.string.week_condition)
        )
    }
}

@Composable
private fun LoanSizeText(
    getLoan: () -> String,
) {
    Row {
        Text(
            text = getLoan(),
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
    Button(onClick = onClick,
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
private fun RangeText() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            stringResource(R.string.min_loan),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            stringResource(R.string.max_loan),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    LoanPanel(20f, { return@LoanPanel "20000" }, {},{})
}