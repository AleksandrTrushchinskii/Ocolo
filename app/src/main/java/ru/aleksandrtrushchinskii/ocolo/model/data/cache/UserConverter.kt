package ru.aleksandrtrushchinskii.ocolo.model.data.cache

import android.arch.persistence.room.TypeConverter
import java.util.*


class UserConverter {

    @TypeConverter
    fun dateToLong(date: Date) = date.time

    @TypeConverter
    fun longToDate(long: Long) = Date(long)

}