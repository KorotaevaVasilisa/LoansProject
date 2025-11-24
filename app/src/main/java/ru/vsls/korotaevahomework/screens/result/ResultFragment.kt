package ru.vsls.korotaevahomework.screens.result

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
import ru.vsls.navigation.Screen
import ru.vsls.navigation.di.getComponentProvider
import ru.vsls.ui.theme.ShiftTheme
import javax.inject.Inject

class ResultFragment : Fragment() {
    private var paramSuccess: Boolean? = null
    private var paramAmount: Int? = null

    @Inject
    lateinit var router: Router

    override fun onAttach(context: Context) {
        context.getComponentProvider().getScreensComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramSuccess = it.getBoolean(ARG_SUCCESS)
            paramAmount = it.getInt(ARG_AMOUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ShiftTheme {
                    ResultScreen(
                        success = paramSuccess,
                        amount = paramAmount,
                        onBack = ::navigateToBack,
                        navigateTo = ::navigateToBanks
                    )
                }
            }
        }
    }

    private fun navigateToBack() {
        router.navigateBack()
    }

    private fun navigateToBanks() {
        router.clearBackStackAndNavigate(Screen.BanksScreen)
    }

    override fun onResume() {
        super.onResume()
        (activity as? BottomBarController)?.setBottomBarVisible(false)
    }

    override fun onPause() {
        (activity as? BottomBarController)?.setBottomBarVisible(true)
        super.onPause()
    }

    companion object {
        const val ARG_SUCCESS = "success"
        const val ARG_AMOUNT = "amount"

        @JvmStatic
        fun newInstance(success: Boolean, amount: Int) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_SUCCESS, success)
                    putInt(ARG_AMOUNT, amount)
                }
            }
    }
}