package ru.aleksandrtrushchinskii.ocolo.common.service

import android.arch.lifecycle.MutableLiveData


object LoadingState {
    val active by lazy {
        MutableLiveData<Boolean>().apply {
            value = false
        }
    }

    fun start() {
        active.value = true
    }

    fun stop() {
        active.value = false
    }
}