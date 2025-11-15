package ru.vsls.korotaevahomework.enter.presentation

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import ru.vsls.korotaevahomework.enter.presentation.common.LoadingScreen
import ru.vsls.korotaevahomework.enter.presentation.components.EnterContent
import ru.vsls.korotaevahomework.enter.presentation.model.EnterState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterScreen(
    viewModel: EnterViewModel,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.initForm() }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.errors.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    when (state) {
        EnterState.Initial,
        EnterState.Loading,
            -> LoadingScreen()

        is EnterState.Login,
        is EnterState.Registration,
            -> EnterContent(
            state = state,
            switchToLogin = viewModel::switchToLogin,
            switchToRegistration = viewModel::switchToRegistration,
            loginUser = viewModel::loginUser,
            registerUser = viewModel::registrationUser,
            onFieldChange = viewModel::handleFieldChanged
        )
    }
}