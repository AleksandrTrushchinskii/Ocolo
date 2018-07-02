package ru.aleksandrtrushchinskii.ocolo.model.data.repository

import kotlinx.coroutines.experimental.async
import ru.aleksandrtrushchinskii.ocolo.model.Meetup
import ru.aleksandrtrushchinskii.ocolo.model.data.database.MeetupDatabase
import javax.inject.Inject


class MeetupRepository @Inject constructor(private val db: MeetupDatabase) {

    fun create(meetup: Meetup) = async {
        db.create(meetup)
    }

    fun load() = async {
        db.load()
    }

}