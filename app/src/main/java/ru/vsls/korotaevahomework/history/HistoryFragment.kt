package ru.vsls.korotaevahomework.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.history.presentation.HistoryScreen
import ru.vsls.korotaevahomework.history.presentation.HistoryViewModel
import ru.vsls.shared.network.theme.ShiftTheme

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ShiftTheme {
//                    HistoryScreen(viewModel = HistoryViewModel())
                }
            }
        }
    }
}