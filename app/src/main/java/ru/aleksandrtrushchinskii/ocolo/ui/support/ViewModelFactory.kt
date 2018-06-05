package ru.aleksandrtrushchinskii.ocolo.ui.support

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.aleksandrtrushchinskii.ocolo.common.service.Authentication
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.UserRepository
import ru.aleksandrtrushchinskii.ocolo.ui.main.MainViewModel
import ru.aleksandrtrushchinskii.ocolo.ui.profile.ProfileViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor(
        private val auth: Authentication,
        private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = when (modelClass) {
        MainViewModel::class.java -> MainViewModel() as T
        ProfileViewModel::class.java -> ProfileViewModel(auth, userRepository) as T
        else -> throw RuntimeException("Unknown view model: $modelClass")
    }

}