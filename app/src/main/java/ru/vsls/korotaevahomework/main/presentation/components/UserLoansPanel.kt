package ru.vsls.korotaevahomework.main.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.vsls.korotaevahomework.R

@Composable
internal fun UserLoans(loans: List<String> = emptyList()){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            stringResource(R.string.title_user_loans_panel),
            style = MaterialTheme.typography.titleLarge,
        )
        if(loans.isEmpty())
            Text(
                stringResource(R.string.description_empty_loans),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Preview(showBackground = true)
@Composable
private fun UserLoansPreview() {
    UserLoans(emptyList())
}