package ru.vsls.korotaevahomework.screens.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import ru.vsls.korotaevahomework.App
import ru.vsls.korotaevahomework.MainActivity
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.screens.onboarding.model.OnBoardModel
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import ru.vsls.ui.theme.ShiftTheme
import javax.inject.Inject

class OnboardingFragment : Fragment() {

    @Inject
    lateinit var router: Router

    private val component by lazy {
        (requireActivity().application as App).component.screensComponent()
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ShiftTheme {
                    OnboardingScreen(
                        boards = data,
                        navigateTo = { router.clearBackStackAndNavigate(Screen.MainScreen) })
                }
            }
        }
    }

    val data = listOf<OnBoardModel>(
        OnBoardModel(
            imageRes = R.drawable.illuctration_1,
            title = "Оформить займ",
            description = "Выберите сумму и нажмите «продолжить». Затем укажите свои данные.\nПосле нажатия «Оформить займ» заявка поступит в банк"
        ),
        OnBoardModel(
            imageRes = R.drawable.illuctration_2,
            title = "Получить займ",
            description = "Отслеживайте изменение статуса заявки.\nКогда займ будет одобрен, подойдите с паспортом в отделение банка для подписания договора"
        ),
        OnBoardModel(
            imageRes = R.drawable.illuctration_3,
            title = "Оформленные займы",
            description = "На главном экране приложения отображается список оформленных займов."
        )
    )

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.changeVisibleBottomBar(false)
    }

    override fun onPause() {
        (activity as? MainActivity)?.changeVisibleBottomBar(true)
        super.onPause()
    }
}