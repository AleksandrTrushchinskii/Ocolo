package ru.aleksandrtrushchinskii.ocolo.common.util

import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.aleksandrtrushchinskii.ocolo.ui.MainActivity

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)
}

//fun <T : ViewDataBinding> ViewGroup.inflateBinding(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): T {
//    return DataBindingUtil.inflate(LayoutInflater.from(this.context), layoutId, this, attachToRoot)
//}

val Fragment.mainActivity: MainActivity
    get() = this.activity as MainActivity

fun Fragment.finish() {
    mainActivity.finishFragment(this)
}