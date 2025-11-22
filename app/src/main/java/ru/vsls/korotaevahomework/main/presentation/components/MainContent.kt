package ru.vsls.korotaevahomework.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse
import ru.vsls.korotaevahomework.main.domain.model.Condition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainContent(
    loanValue: Int,
    sliderValue: Float,
    condition: Condition?,
    loans: List<LoanResponse>,
    onSliderValueChange: (Float) -> Unit,
    onNavigateToForm: () -> Unit,
    onNavigateToHistory: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(0),
                title = { Text(text = stringResource(R.string.main_title)) },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier.size(ButtonDefaults.IconSize),
                            painter = painterResource(R.drawable.answer),
                            contentDescription = stringResource(R.string.open_onbording)
                        )
                    }
                })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
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