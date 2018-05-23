package ru.aleksandrtrushchinskii.ocolo.common.service

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class Authentication @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    val signIn: Boolean
        get() = firebaseAuth.currentUser != null
}