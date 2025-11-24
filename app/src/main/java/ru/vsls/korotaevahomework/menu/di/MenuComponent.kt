package ru.vsls.korotaevahomework.menu.di

import android.content.Context
import dagger.Component
import ru.vsls.korotaevahomework.menu.presentation.MenuFragment
import ru.vsls.navigation.Router
import ru.vsls.shared.network.domain.TokenRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MenuModule::class],
    dependencies = [MenuComponent.Deps::class]
)
interface MenuComponent {
    fun inject(enterFragment: MenuFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: Deps): Builder

        fun build(): MenuComponent
    }

    class Deps @Inject constructor(
        val context: Context,
        val tokenRepository: TokenRepository,
        val router: Router
    )
}