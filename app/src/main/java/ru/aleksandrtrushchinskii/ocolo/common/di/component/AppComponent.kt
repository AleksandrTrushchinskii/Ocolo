package ru.aleksandrtrushchinskii.ocolo.common.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.aleksandrtrushchinskii.ocolo.common.app.App
import ru.aleksandrtrushchinskii.ocolo.common.di.module.AppModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
@Suppress("UNCHECKED_CAST")
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }


    fun inject(app: App)

}