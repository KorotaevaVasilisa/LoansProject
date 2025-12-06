package ru.vsls.main.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.vsls.main.R
import ru.vsls.navigation.di.ViewModelFactory
import ru.vsls.navigation.di.getComponentProvider
import ru.vsls.ui.theme.ShiftTheme
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        context.getComponentProvider().getMainComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ShiftTheme {
                    MainScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}