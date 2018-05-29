package ru.aleksandrtrushchinskii.ocolo.model.data.repository

import ru.aleksandrtrushchinskii.ocolo.common.service.ExceptionHandler
import ru.aleksandrtrushchinskii.ocolo.model.User
import ru.aleksandrtrushchinskii.ocolo.model.data.database.UserDatabase
import javax.inject.Inject


class UserRepository @Inject constructor(private val db: UserDatabase,
                                         private val exceptionHandler: ExceptionHandler) {

    fun get(id: String, success: (user: User) -> Unit) {
        db.get(id).subscribe { user ->
            success(user)
        }
    }

    fun exist(id: String, success: (isExist: Boolean) -> Unit) {
        db.exist(id).subscribe({
            success(it)
        }, { exceptionHandler.handle(it) })
    }

    fun save(user: User, complete: () -> Unit = {}) {
        db.save(user).subscribe {
            complete()
        }
    }

}