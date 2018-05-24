package ru.aleksandrtrushchinskii.ocolo.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.aleksandrtrushchinskii.ocolo.di.scope.FragmentScope
import ru.aleksandrtrushchinskii.ocolo.ui.main.MainFragment
import ru.aleksandrtrushchinskii.ocolo.ui.signin.SignInFragment


@Module
interface MainModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    fun mainFragment(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SignInModule::class])
    fun signInFragment(): SignInFragment

}