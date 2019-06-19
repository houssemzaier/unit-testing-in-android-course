package com.techyourchance.testdrivendevelopment.example

class Observable {
    val observers = mutableListOf<Subscriber>()

    fun subscribe(observer: Subscriber) {
        observers.add(observer)
    }

    fun unsubscribe(observer: Subscriber) {
        observers.remove(observer)
    }

    fun notify(data: Int) {
        observers.forEach {
            it.onDataPublished(data)
        }
    }

    interface Subscriber {
        fun onDataPublished(data: Int)
    }

}
