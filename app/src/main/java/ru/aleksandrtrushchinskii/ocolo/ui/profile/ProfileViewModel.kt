package ru.aleksandrtrushchinskii.ocolo.ui.profile

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.aleksandrtrushchinskii.ocolo.common.service.Authentication
import ru.aleksandrtrushchinskii.ocolo.model.User
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.UserRepository
import ru.aleksandrtrushchinskii.ocolo.common.service.LoadingState


class ProfileViewModel constructor(private val auth: Authentication,
                                   private val repository: UserRepository) : ViewModel() {

    val user = MutableLiveData<User>().apply {
        LoadingState.startForeground()
        repository.get(auth.uid) {
            if (it == User.EMPTY) {
                postValue(User(id = auth.uid, email = auth.user.email!!))
            } else {
                postValue(it)
                LoadingState.stopForeground()
            }
        }

    }


    fun save(success: () -> Unit) {
        LoadingState.startBackground()
        repository.save(user.value!!) {
            LoadingState.stopBackground()
            success()
        }
    }

}