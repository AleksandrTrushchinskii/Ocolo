package ru.aleksandrtrushchinskii.ocolo.model.data.cache

import android.arch.persistence.room.*
import io.reactivex.Maybe
import ru.aleksandrtrushchinskii.ocolo.model.User


@Dao
interface UserDao {

    @Insert
    fun insert(vararg users: User)

    @Update
    fun update(vararg users: User)

    @Delete
    fun delete(vararg users: User)

    @Query("SELECT * FROM ${User.TABLE_NAME}")
    fun getAll(): Maybe<List<User>>

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE id = :id")
    fun get(id: String): Maybe<User>

}