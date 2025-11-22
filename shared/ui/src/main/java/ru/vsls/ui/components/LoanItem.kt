package ru.vsls.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.ui.R

@Composable
fun LoanItem(
    id: Int,
    amount: Int,
    date: String,
    stateText: String,
    stateColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LoanTitleText(id = id, amount = amount)

        LoanDescriptionText(
            state = stateText,
            color = stateColor,
            date = date
        )
    }
}

@Composable
private fun LoanTitleText(id: Int, amount: Int) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = stringResource(R.string.number_loan, id))
        Text(text = stringResource(R.string.price_form, amount))
    }
}

@Composable
private fun LoanDescriptionText(state: String, color: Color, date: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = state,
            style = MaterialTheme.typography.labelMedium,
            color = color
        )
        DescriptionText(text = date)
    }
}
