package com.techyourchance.testdrivendevelopment.example

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class ObserverTest {
    private lateinit var SUT: Observer
    private lateinit var observervableMock: Observable

    @Before
    fun setUp() {
        observervableMock = mock(Observable::class.java)
        SUT = Observer(observervableMock)
    }


}
