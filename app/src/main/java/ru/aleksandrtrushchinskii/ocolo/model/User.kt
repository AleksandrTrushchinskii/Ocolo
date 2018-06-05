package ru.aleksandrtrushchinskii.ocolo.model

import android.arch.persistence.room.*
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import ru.aleksandrtrushchinskii.ocolo.model.data.cache.UserConverter
import java.util.*


@Entity(tableName = User.TABLE_NAME)
@TypeConverters(UserConverter::class)
data class User(
        @PrimaryKey @get:Exclude var
        id: String = "",

        var name: String = "",
        var email: String = "",
        var photo: String = "",

        @ColumnInfo(name = "created_date")
        @ServerTimestamp
        var createdDate: Date? = null
) {

    @Ignore constructor():this("")

    companion object {
        val EMPTY = User()
        const val TABLE_NAME = "users"
    }

}