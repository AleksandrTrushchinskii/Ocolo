package ru.aleksandrtrushchinskii.ocolo.common.util

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.CompletableEmitter
import io.reactivex.ObservableEmitter
import io.reactivex.SingleEmitter
import ru.aleksandrtrushchinskii.ocolo.ui.MainActivity


fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)
}

fun <T : ViewDataBinding> ViewGroup.inflateBinding(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): T {
    return DataBindingUtil.inflate(LayoutInflater.from(this.context), layoutId, this, attachToRoot)
}

fun Fragment.finish() {
    (this.activity as MainActivity).finishFragment(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}


fun Any.logDebug(message: String) {
    Log.d(this::class.java.simpleName, message)
}

fun Any.logError(message: String) {
    Log.e(this::class.java.simpleName, message)
}


fun <T> ObservableEmitter<T>.next(t: T) {
    if (!this.isDisposed) {
        this.onNext(t)
    }
}

fun <T> ObservableEmitter<T>.error(e: Throwable) {
    if (!this.isDisposed) {
        this.onError(e)
    }
}

fun <T> ObservableEmitter<T>.complete() {
    if (!this.isDisposed) {
        this.onComplete()
    }
}

fun <T> ObservableEmitter<T>.nextAndComplete(t: T) {
    if (!this.isDisposed) {
        this.onNext(t)
        this.onComplete()
    }
}

fun <T> SingleEmitter<T>.success(t: T) {
    if (!isDisposed) {
        onSuccess(t)
    }
}

fun <T> SingleEmitter<T>.error(e: Throwable) {
    if (!isDisposed) {
        onError(e)
    }
}

fun CompletableEmitter.complete() {
    if (!isDisposed) {
        onComplete()
    }
}

fun CompletableEmitter.error(e: Throwable) {
    if (!isDisposed) {
        onError(e)
    }
}