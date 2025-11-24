package ru.vsls.details.di

import android.content.Context
import dagger.Component
import retrofit2.Retrofit
import ru.vsls.details.presentation.DetailsFragment
import ru.vsls.navigation.Router
import javax.inject.Inject

@Component(
    modules = [DetailsModule::class],
    dependencies = [DetailsComponent.Deps::class]
)
interface DetailsComponent {
    fun inject(detailsFragment: DetailsFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: Deps): Builder

        fun build(): DetailsComponent
    }

    class Deps @Inject constructor(
        val context: Context,
        val retrofit: Retrofit,
        val router: Router,
    )
}