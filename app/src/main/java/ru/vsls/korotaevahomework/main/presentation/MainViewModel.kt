package ru.vsls.korotaevahomework.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vsls.korotaevahomework.main.domain.usecases.GetConditionsUseCase
import ru.vsls.korotaevahomework.main.domain.usecases.GetSomeUserLoanUseCase
import ru.vsls.korotaevahomework.main.presentation.model.MainState
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getConditionsUseCase: GetConditionsUseCase,
    private val getSomeUserLoanUseCase: GetSomeUserLoanUseCase,
    private val router: Router,
) : ViewModel() {
    private var _state = MutableStateFlow<MainState>(MainState.Loading)
    val state = _state.asStateFlow()

    private val _errors = MutableSharedFlow<String>()
    val errors = _errors.asSharedFlow()

    fun loadData() {
        //if (_state.value is MainState.Content) return

        viewModelScope.launch {
            try {
                _state.update { MainState.Content(condition = null, valueSlider = 0f) }

                val conditionsDeferred = async { getConditionsUseCase() }
                val loansDeferred = async { getSomeUserLoanUseCase() }

                val condition = conditionsDeferred.await()
                val loans = loansDeferred.await()

                _state.update {
                    MainState.Content(
                        condition = condition,
                        userLoans = loans
                    )
                }

            } catch (ex: Exception) {
                _errors.emit("Error: ${ex.message}")
            }
        }
    }

    fun onSliderValueChange(value: Float) {
        val state = _state.value as? MainState.Content ?: return

        _state.value = state.copy(valueSlider = value, valueLoan = value.toInt())
    }

    fun navigateToForm() {
        val state = _state.value as? MainState.Content ?: return
        if (state.condition == null) return

        router.navigateTo(
            Screen.FormScreen(
                amount = state.valueLoan,
                period = state.condition.period,
                percent = state.condition.percent
            )
        )
    }

    fun navigateToHistory(){
        router.navigateTo(Screen.HistoryScreen)
    }
}