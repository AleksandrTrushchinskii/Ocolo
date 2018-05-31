package ru.aleksandrtrushchinskii.ocolo.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import ru.aleksandrtrushchinskii.ocolo.common.service.Toaster
import ru.aleksandrtrushchinskii.ocolo.model.User
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.UserRepository
import ru.aleksandrtrushchinskii.ocolo.ui.tool.LoadingState


class ProfileViewModel constructor(private val firebaseAuth: FirebaseAuth,
                                   private val userRepository: UserRepository,
                                   private val toaster: Toaster) : ViewModel() {

    val isAuth: Boolean
        get() = firebaseAuth.currentUser != null

    val user = MutableLiveData<User>().apply {
        LoadingState.start()
        if(isAuth) {
            userRepository.get(firebaseAuth.currentUser!!.uid){
                value = it
            }
        }
        LoadingState.stope()
    }


    fun exist(success: (isExist: Boolean) -> Unit) {
        LoadingState.start()
        userRepository.exist(firebaseAuth.currentUser!!.uid, {
            success(it)
            LoadingState.stope()
        })
    }

    fun createNewUser() {
        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null) {
            user.value = User(id = firebaseUser.uid,
                    name = firebaseUser.displayName!!,
                    photo = firebaseUser.photoUrl.toString()
            )
        }
    }

    fun save() {
        userRepository.save(user.value!!) {
            toaster.saved()
        }
    }

}