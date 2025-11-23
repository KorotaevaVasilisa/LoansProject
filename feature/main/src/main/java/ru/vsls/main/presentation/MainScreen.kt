package ru.vsls.main.presentation

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import ru.vsls.main.presentation.components.MainContent
import ru.vsls.main.presentation.model.MainState
import ru.vsls.ui.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadData() }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.errors.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    val currentState = state
    when (currentState) {
        is MainState.Content -> MainContent(
            loanValue = currentState.valueLoan,
            sliderValue = currentState.valueSlider,
            condition = currentState.condition,
            loans = currentState.userLoans,
            onSliderValueChange = viewModel::onSliderValueChange,
            onNavigateToForm = viewModel::navigateToForm,
            onNavigateToHistory = viewModel::navigateToHistory,
            onNavigateToOnboarding = viewModel::navigateToOnboarding
        )

        MainState.Initial,
        MainState.Loading,
            -> LoadingScreen()
    }
}
