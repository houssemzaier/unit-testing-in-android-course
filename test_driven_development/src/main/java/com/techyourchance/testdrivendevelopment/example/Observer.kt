package com.techyourchance.testdrivendevelopment.example

class Observer(private val observable: Observable) : Observable.Subscriber {
    override fun onDataPublished(data: Int) {

    }

}
