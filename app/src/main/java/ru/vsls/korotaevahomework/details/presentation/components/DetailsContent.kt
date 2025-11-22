package ru.vsls.korotaevahomework.details.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.vsls.korotaevahomework.form.domain.model.LoanResponse

@Composable
internal fun DetailsContent(loan: LoanResponse, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        UserPanel(name = loan.firstName, surname = loan.lastName, number = loan.phoneNumber)
    }
}

