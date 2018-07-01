package ru.aleksandrtrushchinskii.ocolo.ui.tools

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.widget.TextView
import java.util.*
import java.text.SimpleDateFormat


@SuppressLint("SimpleDateFormat")
@BindingAdapter("android:text")
fun setText(view: TextView, date: Date) {
    view.text = SimpleDateFormat("dd-MM-yyyy").format(date)
}