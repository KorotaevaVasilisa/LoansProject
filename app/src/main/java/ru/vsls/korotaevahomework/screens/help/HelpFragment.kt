package ru.vsls.korotaevahomework.screens.help

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import ru.vsls.korotaevahomework.R
import ru.vsls.navigation.BottomBarController
import ru.vsls.navigation.Router
import ru.vsls.navigation.di.getComponentProvider
import ru.vsls.ui.theme.ShiftTheme
import javax.inject.Inject

class HelpFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ShiftTheme {
                    HelpScreen(onBack = { router.navigateBack() })
                }
            }
        }
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