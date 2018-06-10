package ru.aleksandrtrushchinskii.ocolo.ui.support

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.aleksandrtrushchinskii.ocolo.common.service.Authentication
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.MeetupRepository
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.UserRepository
import ru.aleksandrtrushchinskii.ocolo.ui.cteatemeetup.CreateMeetupViewModel
import ru.aleksandrtrushchinskii.ocolo.ui.meetupsline.MeetupsLineViewModel
import ru.aleksandrtrushchinskii.ocolo.ui.profile.ProfileViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor(
        private val auth: Authentication,
        private val userRepository: UserRepository,
        private val meetupRepository: MeetupRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = when (modelClass) {
        MeetupsLineViewModel::class.java -> MeetupsLineViewModel() as T
        ProfileViewModel::class.java -> ProfileViewModel(auth, userRepository) as T
        CreateMeetupViewModel::class.java -> CreateMeetupViewModel(auth, meetupRepository) as T
        else -> throw RuntimeException("Unknown view model: $modelClass")
    }

}