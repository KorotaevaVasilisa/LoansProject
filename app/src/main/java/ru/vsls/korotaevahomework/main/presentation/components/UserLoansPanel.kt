package ru.vsls.korotaevahomework.main.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.common.utils.formatDateShort
import ru.vsls.ui.components.LoanItem
import ru.vsls.shared.network.domain.LoanResponse
import ru.vsls.shared.network.domain.LoanState
import ru.vsls.ui.components.DescriptionText
import ru.vsls.ui.components.TitleText
import ru.vsls.ui.theme.LocalStatusColors

@Composable
internal fun UserLoans(
    loans: List<LoanResponse>,
    onNavigateToHistory: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        TitleText(text = stringResource(R.string.title_user_loans_panel))

        if (loans.isEmpty())
            EmptyDescriptionText()
        else
            LoansCard(
                loans = loans,
                onNavigateToHistory = onNavigateToHistory
            )
    }
}

@Composable
private fun LoansCard(
    loans: List<LoanResponse>,
    onNavigateToHistory: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        loans.forEach { it ->
            LoanItem(
                id = it.id,
                amount = it.amount,
                date = formatDateShort(it.date),
                stateText = getStateText(it.state),
                stateColor = getStateColor(it.state)
            )
        }
        ShowLoansButton(onClick = onNavigateToHistory)
    }
}

@Composable
private fun ShowLoansButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(48.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Text(stringResource(R.string.show_all_loans))
    }
}

@Composable
private fun EmptyDescriptionText() {
    DescriptionText(stringResource(R.string.description_empty_loans))
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