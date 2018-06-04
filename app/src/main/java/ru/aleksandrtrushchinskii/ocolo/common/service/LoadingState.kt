package ru.aleksandrtrushchinskii.ocolo.common.service

import android.arch.lifecycle.MutableLiveData


object LoadingState {
    val foregroundIndicator by lazy {
        MutableLiveData<Boolean>().apply {
            value = false
        }
    }

    val backgroundIndicator by lazy {
        MutableLiveData<Boolean>().apply {
            value = false
        }
    }


    fun startForeground() {
        foregroundIndicator.value = true
    }

    fun stopForeground() {
        foregroundIndicator.value = false
    }

    fun startBackground() {
        backgroundIndicator.value = true
    }

    fun stopBackground() {
        backgroundIndicator.value = false
    }
}