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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.common.ui.DescriptionText
import ru.vsls.korotaevahomework.common.ui.LoanItem
import ru.vsls.korotaevahomework.form.domain.model.LoanState
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse

@Composable
internal fun UserLoans(loans: List<LoanResponse>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            stringResource(R.string.title_user_loans_panel),
            style = MaterialTheme.typography.titleLarge,
        )
        if (loans.isEmpty())
            EmptyDescriptionText()
        else
            LoansCard(loans = loans)
    }
}

@Composable
private fun LoansCard(loans: List<LoanResponse>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        loans.forEach { it ->
            LoanItem(loan = it)
        }
        ShowLoansButton()
    }
}

@Composable
private fun ShowLoansButton() {
    Button(
        {},
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

@Preview(showBackground = true)
@Composable
private fun UserLoansPreview() {
    UserLoans(
        listOf(
            LoanResponse(
                10000,
                "21.11.2025",
                "Егор",
                9885769,
                "Егоров",
                12.4,
                15,
                "8888888888888",
                LoanState.APPROVED
            )
        )
    )
}