package ru.aleksandrtrushchinskii.ocolo.model.data.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.aleksandrtrushchinskii.ocolo.model.User


@Database(entities = [User::class], version = 1)
abstract class AppCache : RoomDatabase() {

    abstract fun userDao(): UserDao

}