package ru.aleksandrtrushchinskii.ocolo.common.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.UserRepository
import ru.aleksandrtrushchinskii.ocolo.ui.viewmodel.MainViewModel
import ru.aleksandrtrushchinskii.ocolo.ui.viewmodel.ProfileViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor(
        private val firebaseAuth: FirebaseAuth,
        private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = when (modelClass) {
        MainViewModel::class.java -> MainViewModel() as T
        ProfileViewModel::class.java -> ProfileViewModel(firebaseAuth, userRepository) as T
        else -> throw RuntimeException("Unknown view model: $modelClass")
    }

}