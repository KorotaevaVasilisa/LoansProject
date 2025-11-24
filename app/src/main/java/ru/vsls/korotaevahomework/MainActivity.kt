package ru.vsls.korotaevahomework

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.vsls.korotaevahomework.databinding.ActivityMainBinding
import ru.vsls.navigation.ActivityHolder
import ru.vsls.navigation.BottomBarController
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import javax.inject.Inject

class MainActivity : AppCompatActivity(), BottomBarController {

    private lateinit var bottomBar: BottomNavigationView

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var holder: ActivityHolder
    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainContainer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        holder.attachActivity(this)

        setListenerBottomBar()

        if (savedInstanceState == null) {
            router.navigateTo(Screen.EnterScreen)
        }
    }

    private fun setListenerBottomBar() {
        bottomBar = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_home -> {
                    router.clearBackStackAndNavigate(Screen.MainScreen)
                    true
                }

                R.id.nav_menu -> {
                    router.replaceFragment(Screen.MenuScreen)
                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        holder.detachActivity()
    }

    override fun setBottomBarVisible(isVisible: Boolean) {
        bottomBar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
