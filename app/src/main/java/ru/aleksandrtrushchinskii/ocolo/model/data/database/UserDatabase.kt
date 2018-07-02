package ru.aleksandrtrushchinskii.ocolo.model.data.database

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.aleksandrtrushchinskii.ocolo.common.util.complete
import ru.aleksandrtrushchinskii.ocolo.common.util.error
import ru.aleksandrtrushchinskii.ocolo.common.util.logError
import ru.aleksandrtrushchinskii.ocolo.common.util.success
import ru.aleksandrtrushchinskii.ocolo.model.User
import javax.inject.Inject


class UserDatabase @Inject constructor(firestore: FirebaseFirestore) {

    private val db = firestore.collection("users")


    fun get(id: String): Single<User> = Single.create<User> { emitter ->
        db.document(id).get().addOnSuccessListener {
            val user = it.toObject(User::class.java) ?: User.EMPTY
            if (user != User.EMPTY) user.id = it.id
            emitter.success(user)
        }.addOnFailureListener {
            logError("UserDatabase.get($id) : $it")
            emitter.error(it)
        }
    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun exist(id: String): Single<Boolean> = Single.create<Boolean> { emitter ->
        db.document(id).get().addOnCompleteListener {
            if (it.isSuccessful) {
                if (it.result.exists()) {
                    emitter.success(true)
                } else {
                    emitter.success(false)
                }
            } else {
                if (it.exception != null) {
                    logError("UsersDatabase.exist($id) : $it")
                    emitter.error(it.exception!!)
                } else {
                    logError("UsersDatabase.exist($id) : is not successful without error")
                }
            }
        }
    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun save(user: User): Completable = Completable.create { emitter ->
        db.document(user.id).set(user).addOnSuccessListener {
            emitter.complete()
        }.addOnFailureListener {
            emitter.error(it)
        }
    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}