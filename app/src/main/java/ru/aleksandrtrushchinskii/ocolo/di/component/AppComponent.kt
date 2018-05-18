package ru.aleksandrtrushchinskii.ocolo.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.aleksandrtrushchinskii.ocolo.common.app.App
import ru.aleksandrtrushchinskii.ocolo.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}