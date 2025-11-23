package ru.vsls.korotaevahomework.screens.languages

import LanguagesScreen
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
import ru.vsls.ui.theme.ShiftTheme
import javax.inject.Inject

class LanguagesFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_languages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ShiftTheme {
                    LanguagesScreen(
                        currentLang = getAppLanguage(),
                        onBack = { router.navigateBack() },
                        onApplyLanguage = ::setAppLanguage
                    )
                }
            }
        }
    }


    fun getAppLanguage(): AppLanguage {
        return AppLanguage.Russian
    }

    fun setAppLanguage(language: AppLanguage) {
        //TODO
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