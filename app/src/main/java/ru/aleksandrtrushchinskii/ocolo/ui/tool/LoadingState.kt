package ru.aleksandrtrushchinskii.ocolo.ui.tool

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

    fun stope() {
        active.value = false
    }
}