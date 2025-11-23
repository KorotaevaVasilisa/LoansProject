package ru.vsls.korotaevahomework.screens.banks

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
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import ru.vsls.ui.theme.ShiftTheme
import javax.inject.Inject

class BanksFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_banks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ShiftTheme {
                    BanksScreen(
                        onBack = { router.navigateBack() },
                        navigateTo = { router.clearBackStackAndNavigate(Screen.MainScreen) })
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.changeVisibleBottomBar(false)
    }

    override fun onPause() {
        (activity as? MainActivity)?.changeVisibleBottomBar(true)
        super.onPause()
    }
}