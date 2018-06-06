package ru.aleksandrtrushchinskii.ocolo.model.data.repository

import ru.aleksandrtrushchinskii.ocolo.model.Meetup
import ru.aleksandrtrushchinskii.ocolo.model.data.database.MeetupDatabase
import javax.inject.Inject


class MeetupRepository @Inject constructor(private val db: MeetupDatabase) {

    fun create(meetup: Meetup, complete: () -> Unit = {}){
        db.create(meetup).subscribe {
            complete()
        }
    }

}