package ru.vsls.korotaevahomework.main.di

import android.content.Context
import dagger.Component
import retrofit2.Retrofit
import ru.vsls.korotaevahomework.main.presentation.MainFragment
import ru.vsls.navigation.Router
import javax.inject.Inject

@Component(
    modules = [MainModule::class],
    dependencies = [MainComponent.Deps::class]
)
interface MainComponent {
    fun inject(mainFragment: MainFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: Deps): Builder
        fun build(): MainComponent
    }

    class Deps @Inject constructor(
        val context: Context,
        val retrofit: Retrofit,
        val router: Router,
    )
}