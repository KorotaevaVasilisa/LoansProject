package ru.vsls.korotaevahomework.main.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.vsls.korotaevahomework.common.presentation.LoadingScreen
import ru.vsls.korotaevahomework.main.presentation.components.MainContent
import ru.vsls.korotaevahomework.main.presentation.model.MainState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()

    val currentState = state
    when (currentState) {
        is MainState.Content -> MainContent(
            sliderValue = currentState.valueSlider,
            onContinueClick = {},
            onSliderValueChange = viewModel::onSliderValueChange
        )

        MainState.Initial,
        MainState.Loading,
            -> LoadingScreen()
    }
}
