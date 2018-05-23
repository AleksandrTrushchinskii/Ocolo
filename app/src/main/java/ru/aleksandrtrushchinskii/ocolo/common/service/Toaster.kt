package ru.aleksandrtrushchinskii.ocolo.common.service

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast
import ru.aleksandrtrushchinskii.ocolo.R
import javax.inject.Inject

class Toaster @Inject constructor(private val context: Context) {
    var toast: Toast? = null

    private fun showToast(@StringRes stringId: Int) {
        if (toast != null) toast = null

        toast = Toast.makeText(context, stringId, Toast.LENGTH_SHORT)

        toast?.show()
    }

    fun internetNotAvailable() {
        showToast(R.string.toast_internet_not_available)
    }
}