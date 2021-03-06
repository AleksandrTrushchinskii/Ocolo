package ru.aleksandrtrushchinskii.ocolo.model.data.database

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.experimental.launch
import ru.aleksandrtrushchinskii.ocolo.model.Meetup
import javax.inject.Inject
import kotlin.coroutines.experimental.suspendCoroutine


class MeetupDatabase @Inject constructor(firestore: FirebaseFirestore) {

    private val db = firestore.collection("meetups")


    suspend fun create(meetup: Meetup) = suspendCoroutine<Boolean> {continuation ->
        db.add(meetup).addOnSuccessListener {
            continuation.resume(true)
        }.addOnFailureListener {
            continuation.resume(false)
        }
    }

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