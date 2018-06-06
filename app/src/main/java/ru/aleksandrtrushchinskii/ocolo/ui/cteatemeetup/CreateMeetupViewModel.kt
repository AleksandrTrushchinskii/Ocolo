package ru.aleksandrtrushchinskii.ocolo.ui.cteatemeetup

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.aleksandrtrushchinskii.ocolo.common.service.Authentication
import ru.aleksandrtrushchinskii.ocolo.model.Meetup
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.MeetupRepository


class CreateMeetupViewModel constructor(
        private val auth: Authentication,
        private val repository: MeetupRepository
): ViewModel(){

    val meetup = MutableLiveData<Meetup>().apply {
        value = Meetup()
    }


    fun create(success: () -> Unit){
        meetup.value?.userId = auth.uid
        repository.create(meetup.value!!){
            success()
        }
    }

}