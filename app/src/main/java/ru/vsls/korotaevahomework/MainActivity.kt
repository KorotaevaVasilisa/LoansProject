package ru.vsls.korotaevahomework

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.vsls.korotaevahomework.databinding.ActivityMainBinding
import ru.vsls.main.presentation.MainFragment
import ru.vsls.navigation.ActivityHolder
import ru.vsls.navigation.BottomBarController
import ru.vsls.navigation.Router
import ru.vsls.navigation.Screen
import javax.inject.Inject

class MainActivity : AppCompatActivity(), BottomBarController {

    private lateinit var binding: ActivityMainBinding

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

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainContainer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        holder.attachActivity(this)

        setListenerBottomBar()
        setFragmentCallbacks()

        if (savedInstanceState == null) {
            router.navigateTo(Screen.EnterScreen)
        }
    }

    private fun setFragmentCallbacks() {
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
    }

    private val fragmentLifecycleCallbacks =
        object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                if (f is MainFragment) {
                    binding.bottomNavigation.menu.findItem(R.id.nav_home)?.isChecked = true
                }
            }
        }


    private fun setListenerBottomBar() {

        binding.bottomNavigation.setOnItemSelectedListener { item ->
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
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }

    override fun setBottomBarVisible(isVisible: Boolean) {
        binding.bottomNavigation.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
