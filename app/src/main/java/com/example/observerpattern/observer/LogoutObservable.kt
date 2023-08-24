package com.example.observerpattern.observer

import androidx.annotation.MainThread
import java.util.Observable

object LogoutObservable : Observable() {
    @MainThread
    @JvmStatic
    fun notify(obj: Any) {
        notifyObservers(obj)
    }

    override fun notifyObservers(arg: Any?) {
        setChanged()
        super.notifyObservers(arg)
    }
}