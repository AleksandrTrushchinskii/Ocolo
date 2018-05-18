package ru.aleksandrtrushchinskii.ocolo.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.aleksandrtrushchinskii.ocolo.di.scope.ActivityScope
import ru.aleksandrtrushchinskii.ocolo.view.activity.MainActivity

@Module(includes = [AndroidSupportInjectionModule::class])
interface AppModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    fun mainActivityInjector(): MainActivity
}