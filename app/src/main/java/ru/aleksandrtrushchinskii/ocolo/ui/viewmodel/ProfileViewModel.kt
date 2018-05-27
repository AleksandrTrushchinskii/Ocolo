package ru.aleksandrtrushchinskii.ocolo.ui.viewmodel

import android.arch.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.UserRepository
import ru.aleksandrtrushchinskii.ocolo.ui.tool.LoadingState


class ProfileViewModel constructor(private val firebaseAuth: FirebaseAuth,
                                   private val userRepository: UserRepository) : ViewModel() {

    val isAuth: Boolean
        get() = firebaseAuth.currentUser != null


    fun exist(ifTrue: () -> Unit, ifFalse: () -> Unit) {
        LoadingState.start()
        userRepository.exist(firebaseAuth.currentUser!!.uid, {
            ifTrue()
            LoadingState.stope()
        }, {
            ifFalse()
            LoadingState.stope()
        })
    }

}


