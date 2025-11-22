package ru.vsls.korotaevahomework.details.presentation

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

class DetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: DetailsViewModel.Factory

    private lateinit var viewModel: DetailsViewModel
    private var loanId: Int? = null
    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            loanId = it.getInt(ARG_ID)
        }
        viewModel = viewModelFactory.create(loanId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ShiftTheme {
                    DetailsScreen()
                }
            }
        }
    }

    companion object {
        const val ARG_ID = "loanId"

        @JvmStatic
        fun newInstance(paramId: Int) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, paramId)
                }
            }
    }
}