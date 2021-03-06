package ru.aleksandrtrushchinskii.ocolo.common.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.aleksandrtrushchinskii.ocolo.common.di.scope.ActivityScope
import ru.aleksandrtrushchinskii.ocolo.ui.MainActivity


@Module(includes = [AndroidSupportInjectionModule::class, SingletonsModule::class])
interface AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    fun mainActivityInjector(): MainActivity

}