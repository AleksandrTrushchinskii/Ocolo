package ru.aleksandrtrushchinskii.ocolo.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.aleksandrtrushchinskii.ocolo.di.module.ui.MainFragmentModule
import ru.aleksandrtrushchinskii.ocolo.di.module.ui.ProfileModule
import ru.aleksandrtrushchinskii.ocolo.di.module.ui.SignInModule
import ru.aleksandrtrushchinskii.ocolo.di.scope.FragmentScope
import ru.aleksandrtrushchinskii.ocolo.ui.view.MainFragment
import ru.aleksandrtrushchinskii.ocolo.ui.view.ProfileFragment
import ru.aleksandrtrushchinskii.ocolo.ui.view.SignInFragment


@Module
interface MainModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    fun mainFragment(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SignInModule::class])
    fun signInFragment(): SignInFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ProfileModule::class])
    fun profileFragment(): ProfileFragment

}