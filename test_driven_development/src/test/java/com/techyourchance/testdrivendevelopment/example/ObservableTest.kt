package com.techyourchance.testdrivendevelopment.example

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*


class ObservableTest {

    private lateinit var SUT: Observable
    private lateinit var observer1Mock: Observable.Subscriber
    private lateinit var observer2Mock: Observable.Subscriber

    @Before
    fun setUp() {
        SUT = Observable()
        observer1Mock = mock(Observable.Subscriber::class.java)
        observer2Mock = mock(Observable.Subscriber::class.java)
    }

    @Test
    fun `observable should be able to add observers`() {
        SUT.subscribe(observer1Mock)
        SUT.subscribe(observer2Mock)
        assertThat(SUT.observers.count(), `is`(2))
    }

    @Test
    fun `observable should be able to remove observers`() {
        SUT.subscribe(observer1Mock)
        SUT.subscribe(observer2Mock)
        assertThat(SUT.observers.count(), `is`(2))

        SUT.unsubscribe(observer1Mock)
        assertThat(SUT.observers.count(), `is`(1))
    }

    @Test
    fun `observable should be able to notify observers`() {
        SUT.subscribe(observer1Mock)
        SUT.subscribe(observer2Mock)

        val data = "some data".count()
        SUT.notify(data)
        val ac = ArgumentCaptor.forClass(Int::class.java)
        verify(observer1Mock).onDataPublished(ac.capture())
        verify(observer2Mock, times(1)).onDataPublished(ac.capture())

        assertThat(ac.value, `is`(data))
        assertThat(ac.allValues[0], `is`(ac.allValues[1]))
    }
}

/**********************
testing an Observable
verify that:
//[observer] normal case: passing correct args
,correct arg is passed to the dependency (EndPoint) from public method of SUT
//[observable] success in the dependency and effect on the observer (another dependency)
when success, all observers notified with the correct data (onSuccess(Data)) [positive vs negative cases]
//cancel [observer]
when a subscriber cancel subscription, the emissions is canceled * 
//unsubscribe [observable]
when success, all unsubscribed observers are not notified
//[observable]  onError notification
when a general/network error, all observers are notified with failure (onError(ErrorTypeException))
 **********************/
