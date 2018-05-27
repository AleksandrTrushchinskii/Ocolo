package ru.aleksandrtrushchinskii.ocolo.model.data.database

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.aleksandrtrushchinskii.ocolo.common.util.error
import ru.aleksandrtrushchinskii.ocolo.common.util.logDebug
import ru.aleksandrtrushchinskii.ocolo.common.util.logError
import ru.aleksandrtrushchinskii.ocolo.common.util.nextAndComplete
import ru.aleksandrtrushchinskii.ocolo.model.User
import javax.inject.Inject


class UserDatabase @Inject constructor(db: FirebaseFirestore) {

    private val users = db.collection("users")


    fun get(id: String): Observable<User> = Observable.create<User> { emitter ->
        users.document(id).get().addOnSuccessListener {
            val user = it.toObject(User::class.java) ?: User.EMPTY
            if (user != User.EMPTY) user.id = it.id
            emitter.nextAndComplete(user)
        }.addOnFailureListener {
            logError("UserDatabase.get($id) : $it")
            emitter.error(it)
        }
    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun exist(id: String): Observable<Boolean> = Observable.create<Boolean> { emitter ->
        users.document(id).get().addOnCompleteListener {
            if (it.isSuccessful) {
                if (it.result.exists()) {
                    emitter.nextAndComplete(true)
                } else {
                    emitter.nextAndComplete(false)
                }
            } else if (!it.isSuccessful && it.exception != null) {
                logDebug("UsersDatabase.exist($id) : $it")

                emitter.error(it.exception!!)
            } else {
                logError("UsersDatabase.exist($id) : is not successful without error")
            }
        }
    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

}