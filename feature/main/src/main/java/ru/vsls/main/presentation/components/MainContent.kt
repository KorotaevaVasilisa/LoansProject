package ru.vsls.main.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.main.R
import ru.vsls.shared.network.domain.model.LoanResponse
import ru.vsls.main.domain.model.Condition
import ru.vsls.ui.components.topbars.MainTopAppBar

@Composable
internal fun MainContent(
    loanValue: Int,
    sliderValue: Float,
    condition: Condition?,
    loans: List<LoanResponse>,
    onSliderValueChange: (Float) -> Unit,
    onNavigateToForm: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.secondary,
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.main_title),
                navigateTo = onNavigateToOnboarding
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LoanCard()

            if (condition != null)
                LoanPanel(
                    loanValue = loanValue,
                    sliderValue = sliderValue,
                    maxAmount = condition.maxAmount,
                    period = condition.period,
                    percent = condition.percent.toInt(),
                    onContinueClick = onNavigateToForm,
                    onValueChange = onSliderValueChange
                )

            UserLoans(
                loans = loans,
                onNavigateToHistory = onNavigateToHistory
            )
        }
    }
}