package ru.aleksandrtrushchinskii.ocolo.ui.meetupsline

import android.arch.lifecycle.ViewModel
import ru.aleksandrtrushchinskii.ocolo.model.Meetup
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.MeetupRepository


class MeetupsLineViewModel(private val repository: MeetupRepository) : ViewModel() {

    fun get(complete: (List<Meetup>) -> Unit) {
        repository.get(complete)
    }

}