package ru.aleksandrtrushchinskii.ocolo.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*


@Entity(tableName = User.TABLE_NAME)
data class User(@PrimaryKey
                @get:Exclude
                var id: String = "",

                var name: String = "",

                var photo: String = "",

                var age: Int = 0,

                @ColumnInfo(name = "created_date")
                @ServerTimestamp
                var createdDate: Date? = null) {

    companion object {
        val EMPTY = User()
        const val TABLE_NAME = "users"
    }

}