package ru.vsls.history.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.vsls.history.R
import ru.vsls.navigation.di.ViewModelFactory
import ru.vsls.navigation.di.getComponentProvider
import ru.vsls.ui.theme.ShiftTheme
import javax.inject.Inject

class HistoryFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: HistoryViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        context.getComponentProvider().getHistoryComponent().inject(this)
        super.onAttach(context)
    }

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
                    HistoryScreen(viewModel = viewModel)
                }
            }
        }
    }
}