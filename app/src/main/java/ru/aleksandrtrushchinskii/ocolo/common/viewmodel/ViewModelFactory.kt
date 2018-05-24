package ru.aleksandrtrushchinskii.ocolo.common.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ru.aleksandrtrushchinskii.ocolo.ui.main.MainViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = when (modelClass) {
        MainViewModel::class.java -> MainViewModel() as T
        else -> throw RuntimeException("Unknown view model: $modelClass")

    }
}