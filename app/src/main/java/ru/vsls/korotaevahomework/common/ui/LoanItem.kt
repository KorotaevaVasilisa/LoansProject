package ru.vsls.korotaevahomework.common.ui

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
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.form.domain.model.LoanState
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse
import ru.vsls.shared.network.theme.LocalStatusColors

@Composable
fun LoanItem(loan: LoanResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LoanTitleText(id = loan.id, amount = loan.amount)

        LoanDescriptionText(state = loan.state, date = loan.date)
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
private fun LoanDescriptionText(state: LoanState, date: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = getStateText(state),
            style = MaterialTheme.typography.labelMedium,
            color = getStateColor(state)
        )
        DescriptionText(text = date)
    }
}

@Composable
private fun getStateText(state: LoanState): String {
    return when (state) {
        LoanState.APPROVED -> stringResource(R.string.approved_state)
        LoanState.REGISTERED -> stringResource(R.string.registered_state)
        LoanState.REJECTED -> stringResource(R.string.rejected_state)
    }
}

@Composable
private fun getStateColor(state: LoanState): Color {
    return when (state) {
        LoanState.APPROVED -> LocalStatusColors.current.positive
        LoanState.REGISTERED -> LocalStatusColors.current.attention
        LoanState.REJECTED -> LocalStatusColors.current.error
    }
}