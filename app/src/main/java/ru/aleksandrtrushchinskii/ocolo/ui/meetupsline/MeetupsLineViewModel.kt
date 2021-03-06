package ru.aleksandrtrushchinskii.ocolo.ui.meetupsline

import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ru.aleksandrtrushchinskii.ocolo.common.service.LoadingState
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.MeetupRepository
import ru.aleksandrtrushchinskii.ocolo.ui.tools.adapter.MeetupAdapter


class MeetupsLineViewModel(private val repository: MeetupRepository) : ViewModel() {

    fun load() {
        launch(UI) {
            LoadingState.startForeground()

            val meetups = repository.load().await()
            MeetupAdapter.setData(meetups)

            LoadingState.stopForeground()
        }
    }

}