package ru.aleksandrtrushchinskii.ocolo.model.data.database

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.launch
import ru.aleksandrtrushchinskii.ocolo.common.util.complete
import ru.aleksandrtrushchinskii.ocolo.common.util.error
import ru.aleksandrtrushchinskii.ocolo.model.Meetup
import javax.inject.Inject
import kotlin.coroutines.experimental.suspendCoroutine


class MeetupDatabase @Inject constructor(firestore: FirebaseFirestore) {

    private val db = firestore.collection("meetups")


    fun create(meetup: Meetup): Completable = Completable.create { emitter ->
        db.add(meetup).addOnSuccessListener {
            emitter.complete()
        }.addOnFailureListener {
            emitter.error(it)
        }
    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    suspend fun load(): List<Meetup> = suspendCoroutine { continuation ->
        db.get().addOnCompleteListener {
            launch {
                if (it.isSuccessful) {
                    val meetups = arrayListOf<Meetup>()

                    it.result.forEach { document ->
                        meetups.add(
                                document.toObject(Meetup::class.java).apply { id = document.id }
                        )
                    }

                    continuation.resume(meetups)
                }
            }
        }

    }

}