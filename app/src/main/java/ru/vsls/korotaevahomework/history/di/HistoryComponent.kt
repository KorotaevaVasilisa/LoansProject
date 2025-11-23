package ru.vsls.korotaevahomework.history.di

import android.content.Context
import dagger.Component
import retrofit2.Retrofit
import ru.vsls.korotaevahomework.history.presentation.HistoryFragment
import ru.vsls.navigation.Router
import javax.inject.Inject

@Component(
    modules = [HistoryModule::class],
    dependencies = [HistoryComponent.Deps::class]
)
interface HistoryComponent {
    fun inject(historyFragment: HistoryFragment)
    @Component.Builder
    interface Builder {
        fun deps(deps: Deps): Builder
        fun build(): HistoryComponent
    }

    class Deps @Inject constructor(
        val context: Context,
        val retrofit: Retrofit,
        val router: Router,
    )
}