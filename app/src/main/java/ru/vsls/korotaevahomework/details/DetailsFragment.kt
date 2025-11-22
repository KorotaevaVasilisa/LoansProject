package ru.vsls.korotaevahomework.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import ru.vsls.korotaevahomework.R
import ru.vsls.korotaevahomework.details.presentation.DetailsScreen
import ru.vsls.ui.theme.ShiftTheme

class DetailsFragment : Fragment() {

    private var loanId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            loanId = it.getInt(ARG_ID)
        }
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