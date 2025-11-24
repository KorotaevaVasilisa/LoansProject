package ru.vsls.korotaevahomework.screens.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.screens.onboarding.model.OnBoardModel
import ru.vsls.navigation.BottomBarController
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import ru.vsls.navigation.di.getComponentProvider
import ru.vsls.ui.theme.ShiftTheme
import javax.inject.Inject

class OnboardingFragment : Fragment() {

    @Inject
    lateinit var router: Router

    override fun onAttach(context: Context) {
        context.getComponentProvider().getScreensComponent().inject(this)
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
                        boards = getBoards(),
                        navigateTo = { router.replaceFragment(Screen.MainScreen) })
                }
            }
        }
    }

    private fun getBoards(): List<OnBoardModel> {
        return listOf<OnBoardModel>(
            OnBoardModel(
                imageRes = R.drawable.illuctration_1,
                title = getString(R.string.board_title_1),
                description = getString(R.string.board_description_1)
            ),
            OnBoardModel(
                imageRes = R.drawable.illuctration_2,
                title = getString(R.string.board_title_2),
                description = getString(R.string.board_description_2)
            ),
            OnBoardModel(
                imageRes = R.drawable.illuctration_3,
                title = getString(R.string.board_title_3),
                description = getString(R.string.board_description_3)
            )
        )
    }

    override fun onResume() {
        super.onResume()
        (activity as? BottomBarController)?.setBottomBarVisible(false)
    }

    override fun onPause() {
        (activity as? BottomBarController)?.setBottomBarVisible(true)
        super.onPause()
    }
}