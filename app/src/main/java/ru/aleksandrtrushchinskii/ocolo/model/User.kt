package ru.aleksandrtrushchinskii.ocolo.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*


data class User(@get:Exclude var id: String = "",
                var name: String = "",
                var email: String = "",
                var photo: String = "",
                @ServerTimestamp var createdDate: Date? = null
) {

    companion object {
        val EMPTY = User()
    }

}