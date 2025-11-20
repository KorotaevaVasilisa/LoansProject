package ru.vsls.korotaevahomework.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.main.domain.model.Condition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainContent(
    loanValue: Int,
    sliderValue: Float,
    condition: Condition?,
    onSliderValueChange: (Float) -> Unit,
    onNavigateToForm: (Int, Double, Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.main_title)) })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            LoanCard()

            if (condition != null)
                LoanPanel(
                    loanValue = loanValue,
                    sliderValue = sliderValue,
                    maxAmount = condition.maxAmount,
                    period = condition.period,
                    percent = condition.percent.toInt(),
                    onContinueClick = {
                        onNavigateToForm(
                            loanValue,
                            condition.percent,
                            condition.period
                        )
                    },
                    onValueChange = onSliderValueChange
                )
            UserLoans()
        }
    }
}