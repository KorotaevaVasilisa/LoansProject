package ru.vsls.korotaevahomework.enter.di

import android.content.Context
import dagger.Component
import retrofit2.Retrofit
import ru.vsls.korotaevahomework.common.navigation.Router
import ru.vsls.korotaevahomework.enter.presentation.EnterFragment
import ru.vsls.shared.network.domain.TokenRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(
    modules = [EnterModule::class],
    dependencies = [EnterComponent.Deps::class]
)
interface EnterComponent {
    fun inject(enterFragment: EnterFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: Deps): Builder

        fun build(): EnterComponent
    }

    class Deps @Inject constructor(
        val context: Context,
        val tokenRepository: TokenRepository,
        val retrofit: Retrofit,
        val router: Router
    )
}