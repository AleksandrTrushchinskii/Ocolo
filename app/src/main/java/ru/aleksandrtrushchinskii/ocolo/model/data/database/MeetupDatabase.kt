package ru.aleksandrtrushchinskii.ocolo.model.data.database

import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.aleksandrtrushchinskii.ocolo.common.util.complete
import ru.aleksandrtrushchinskii.ocolo.common.util.error
import ru.aleksandrtrushchinskii.ocolo.common.util.success
import ru.aleksandrtrushchinskii.ocolo.model.Meetup
import javax.inject.Inject


class MeetupDatabase @Inject constructor(firestore: FirebaseFirestore) {

    private val db = firestore.collection("meetups")


    fun create(meetup: Meetup): Completable = Completable.create { emitter ->
        db.add(meetup).addOnSuccessListener {
            emitter.complete()
        }.addOnFailureListener {
            emitter.error(it)
        }
    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun get(limit: Long = 10, orderedBy: String = "createdDate"): Single<List<Meetup>> {
        return Single.create<List<Meetup>> { emitter ->
            db.orderBy(orderedBy).limit(limit).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    val meetups = ArrayList<Meetup>()

                    for (document in it.result) {
                        val meetup = document.toObject(Meetup::class.java)
                        meetup.id = document.id
                        meetups.add(meetup)
                    }

                    emitter.success(meetups)
                } else {
                    if (it.exception != null) {
                        emitter.error(it.exception!!)
                    }
                }

            }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}