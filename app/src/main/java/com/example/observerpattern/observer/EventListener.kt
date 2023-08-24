package com.example.observerpattern.observer

interface BellEventListener {
    fun onRingBell(actor: String)
}

interface PrimeCallBack {
    fun onPrimeCatched(primeNumber: Int)
}