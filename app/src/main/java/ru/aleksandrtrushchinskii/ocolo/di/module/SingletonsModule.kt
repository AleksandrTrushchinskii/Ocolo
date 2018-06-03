package ru.aleksandrtrushchinskii.ocolo.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.arch.persistence.room.Room
import android.content.Context
import ru.aleksandrtrushchinskii.ocolo.common.service.Authentication
import ru.aleksandrtrushchinskii.ocolo.common.service.ExceptionHandler
import ru.aleksandrtrushchinskii.ocolo.common.service.Toaster
import ru.aleksandrtrushchinskii.ocolo.model.data.cache.AppCache
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.UserRepository


@Module
class SingletonsModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesAppCache(context: Context) = Room.databaseBuilder(
            context,
            AppCache::class.java,
            "database-name"
    ).build()

    @Provides
    @Singleton
    fun providesExceptionHandler(toaster: Toaster) = ExceptionHandler(toaster)

    @Provides
    @Singleton
    fun providesAuthentication(firebaseAuth: FirebaseAuth,
                               userRepository: UserRepository
    ) = Authentication(firebaseAuth, userRepository)

}