package ru.vsls.korotaevahomework.details.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ru.vsls.korotaevahomework.details.presentation.components.DetailsContent
import ru.vsls.korotaevahomework.details.presentation.model.DetailsState
import ru.vsls.ui.components.LoadingBlock
import ru.vsls.ui.components.topbars.BackTopAppBar

@Composable
internal fun DetailsScreen(viewModel: DetailsViewModel) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadDetails() }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.errors.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.secondary,
        topBar = {
            BackTopAppBar(
                title = "№ ${viewModel.loanId}",
                navigateTo = viewModel::onBackPressed
            )
        }
    ) { paddingValues ->
        when (state) {
            is DetailsState.Content -> {
                val content = state as DetailsState.Content
                DetailsContent(content.loan, paddingValues)
            }

            DetailsState.Initial,
            DetailsState.Loading,
                -> LoadingBlock(Modifier.padding(paddingValues))
        }

    }
}