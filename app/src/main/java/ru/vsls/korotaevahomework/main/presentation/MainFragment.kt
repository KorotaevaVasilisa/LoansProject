package ru.vsls.korotaevahomework.main.presentation

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
import ru.vsls.korotaevahomework.form.FormFragment
import ru.vsls.shared.network.theme.ShiftTheme
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

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
                        viewModel = viewModel,
                        onNavigateToForm = ::navigateToForm
                    )
                }
            }
        }
    }

    private fun navigateToForm(amount: Int, percent: Double, period: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, FormFragment.newInstance(
                amount, percent, period))
            .addToBackStack(null)
            .commit()
    }
}