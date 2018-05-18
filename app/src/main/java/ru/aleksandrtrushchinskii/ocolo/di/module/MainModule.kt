package ru.aleksandrtrushchinskii.ocolo.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.aleksandrtrushchinskii.ocolo.di.scope.FragmentScope
import ru.aleksandrtrushchinskii.ocolo.view.fragment.MainFragment

@Module
interface MainModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    fun mainFragment(): MainFragment
}