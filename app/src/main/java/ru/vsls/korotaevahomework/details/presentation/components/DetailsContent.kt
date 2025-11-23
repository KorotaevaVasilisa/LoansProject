package ru.vsls.korotaevahomework.details.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.shared.network.domain.model.LoanResponse
import ru.vsls.shared.network.domain.model.LoanState
import ru.vsls.ui.components.DescriptionText
import ru.vsls.ui.theme.LocalStatusColors

@Composable
internal fun DetailsContent(loan: LoanResponse, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        UserPanel(name = loan.firstName, surname = loan.lastName, number = loan.phoneNumber)

        UserLoanPanel(
            id = loan.id,
            date = loan.date,
            period = loan.period,
            percent = loan.percent.toInt(),
            amount = loan.amount
        )

        StatusPanel(status = loan.state)

        DescriptionText(text = stringResource(R.string.description_details))
    }
}

@Composable
private fun StatusPanel(status: LoanState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DescriptionText(text = stringResource(R.string.status))
            Text(
                text = getStateText(status),
                fontWeight = FontWeight.Bold,
                color = getStateColor(status)
            )
        }
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

