package ru.aleksandrtrushchinskii.ocolo.common.service

import com.google.firebase.firestore.FirebaseFirestoreException
import ru.aleksandrtrushchinskii.ocolo.common.util.logError
import javax.inject.Inject


class ExceptionHandler @Inject constructor(private val toaster: Toaster) {

    fun handle(exception: Throwable) {
        if (exception is FirebaseFirestoreException) {
            if (exception.code == FirebaseFirestoreException.Code.UNAVAILABLE) {
                toaster.internetNotAvailable()
            }
        } else {
            if (exception.message != null) {
                logError(exception.message!!)
            }
            logError(exception.stackTrace.toString())
        }
    }

}