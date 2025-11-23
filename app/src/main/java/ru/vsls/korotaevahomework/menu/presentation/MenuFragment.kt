package ru.vsls.korotaevahomework.menu.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import ru.vsls.korotaevahomework.App
import ru.vsls.korotaevahomework.R
import ru.vsls.ui.theme.ShiftTheme
import javax.inject.Inject

class MenuFragment : Fragment() {
    @Inject
    lateinit var viewModel: MenuViewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ShiftTheme {
                    MenuScreen(
                        onExit = viewModel::onExit,
                        onNavigate = viewModel::navigateTo,
                        onNavigateToOnboarding = viewModel::navigateToOnboarding
                    )
                }
            }
        }
    }
}