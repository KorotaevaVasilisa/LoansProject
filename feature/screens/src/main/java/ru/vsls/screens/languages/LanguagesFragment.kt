package ru.vsls.screens.languages

import LanguagesScreen
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import ru.vsls.navigation.BottomBarController
import ru.vsls.navigation.Router
import ru.vsls.navigation.di.getComponentProvider
import ru.vsls.screens.R
import ru.vsls.ui.theme.ShiftTheme
import javax.inject.Inject

class LanguagesFragment : Fragment() {

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