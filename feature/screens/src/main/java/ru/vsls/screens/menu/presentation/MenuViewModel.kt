package ru.vsls.screens.menu.presentation

import androidx.lifecycle.ViewModel
import ru.vsls.screens.menu.domain.DeleteTokenUseCase
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val deleteTokenUseCase: DeleteTokenUseCase,
    private val router: Router,
) : ViewModel() {

    fun onExit() {
        deleteTokenUseCase()
        router.clearBackStackAndNavigate(Screen.EnterScreen)
    }

    fun navigateTo(screen: Screen) {
        router.navigateTo(screen)
    }

    fun navigateToOnboarding(){
        router.navigateTo(Screen.OnboardingScreen)
    }
}