package ru.aleksandrtrushchinskii.ocolo.common.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ru.aleksandrtrushchinskii.ocolo.model.data.repository.UserRepository

class Authentication(private val firebaseAuth: FirebaseAuth,
                     private val userRepository: UserRepository) {
    val isAuth: Boolean
        get() = firebaseAuth.currentUser != null

    val uid: String
        get() = firebaseAuth.currentUser!!.uid

    val user: FirebaseUser
        get() = firebaseAuth.currentUser!!

    fun exist(success: (isExist: Boolean) -> Unit) {
        userRepository.exist(uid, {
            success(it)
        })
    }
}