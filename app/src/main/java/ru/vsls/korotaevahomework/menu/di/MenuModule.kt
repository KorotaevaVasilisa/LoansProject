package ru.vsls.korotaevahomework.menu.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.vsls.korotaevahomework.menu.presentation.MenuViewModel
import ru.vsls.navigation.di.ViewModelKey

@Module
interface MenuModule {

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    fun bindMenuViewModel(menuViewModel: MenuViewModel): ViewModel
}