package ru.aleksandrtrushchinskii.ocolo.model.data.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import ru.aleksandrtrushchinskii.ocolo.common.service.Toaster
import ru.aleksandrtrushchinskii.ocolo.model.User
import ru.aleksandrtrushchinskii.ocolo.model.data.database.UserDatabase
import javax.inject.Inject


class UserRepository @Inject constructor(private val db: UserDatabase,
                                         private val toaster: Toaster) {

    fun get(id: String, action: (user: User) -> Unit) {
        db.get(id).subscribe {
            if (!it.equals(User.EMPTY)) {
                action(it)
            } else {
                println("User with id $id doesn't exist")
            }
        }.dispose()
    }

    fun exist(id: String, ifTrue: () -> Unit, ifFalse: () -> Unit) {
        db.exist(id).subscribe({
            if (it) {
                ifTrue()
            } else {
                ifFalse()
            }
        }, {
            if (it is FirebaseFirestoreException) {
                if (it.code == FirebaseFirestoreException.Code.UNAVAILABLE) {
                    toaster.internetNotAvailable()
                }
            }

        })

    }

}