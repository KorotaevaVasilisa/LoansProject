package ru.vsls.korotaevahomework.form.di

import android.content.Context
import dagger.Component
import retrofit2.Retrofit
import ru.vsls.korotaevahomework.common.navigation.Router
import ru.vsls.korotaevahomework.form.FormFragment
import javax.inject.Inject

@Component(
    modules = [FormModule::class],
    dependencies = [FormComponent.Deps::class]
)
interface FormComponent {
    fun inject(formFragment: FormFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: Deps): Builder
        fun build(): FormComponent
    }

    class Deps @Inject constructor(
        val context: Context,
        val retrofit: Retrofit,
        val router: Router,
    )
}