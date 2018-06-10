package ru.aleksandrtrushchinskii.ocolo.common.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.aleksandrtrushchinskii.ocolo.common.di.module.ui.CreateMeetupModule
import ru.aleksandrtrushchinskii.ocolo.common.di.module.ui.MeetupsLineModule
import ru.aleksandrtrushchinskii.ocolo.common.di.module.ui.ProfileModule
import ru.aleksandrtrushchinskii.ocolo.common.di.module.ui.SignInModule
import ru.aleksandrtrushchinskii.ocolo.common.di.scope.FragmentScope
import ru.aleksandrtrushchinskii.ocolo.ui.cteatemeetup.CreateMeetupFragment
import ru.aleksandrtrushchinskii.ocolo.ui.meetupsline.MeetupsLineFragment
import ru.aleksandrtrushchinskii.ocolo.ui.profile.ProfileFragment
import ru.aleksandrtrushchinskii.ocolo.ui.signin.SignInFragment


@Module
interface MainModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MeetupsLineModule::class])
    fun meetupsLineFragment(): MeetupsLineFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SignInModule::class])
    fun signInFragment(): SignInFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ProfileModule::class])
    fun profileFragment(): ProfileFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [CreateMeetupModule::class])
    fun createMeetupFragment(): CreateMeetupFragment

}