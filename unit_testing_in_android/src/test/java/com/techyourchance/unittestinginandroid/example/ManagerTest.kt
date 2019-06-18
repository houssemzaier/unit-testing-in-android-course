package com.techyourchance.unittestinginandroid.example

import android.app.Activity
import android.content.Context
import android.view.View
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.StringStartsWith.startsWith
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*

class ManagerTest {
//    Test Mocking Android Dependency is possible ;)

    private lateinit var SUT: Manager
    private val activityMock = mock(Activity::class.java)
    private val contextMock = mock(Context::class.java)
    @Before
    fun setUp() {
        SUT = Manager(activityMock, contextMock)
    }

    @Test
    fun `doIt should return "OK OLD" when age is greater than 80 and the activity isDestroyed`() {
        `when`(activityMock.isDestroyed).thenReturn(true)
        val result = SUT.doIt(81)
        assertThat(result, `is`("OK OLD"))
    }

    @Test
    fun `doIt should return "the activity is still alive" when age is not greater than 80 and the activity isDestroyed`() {
        `when`(activityMock.isDestroyed).thenReturn(true)
        val result = SUT.doIt(80)
        assertThat(result, `is`("the activity is still alive"))
    }

    @Test
    fun `doIt should return "not old" when  the activity isNotDestroyed`() {
        `when`(activityMock.isDestroyed).thenReturn(false)
        //this will not work as it is creating a view
        //`when`(activityMock.findViewById<View>(anyInt())).thenReturn(View(contextMock))

        val viewMock = mock(View::class.java)
        `when`(activityMock.findViewById<View>(anyInt())).thenReturn(viewMock)//this one works
        `when`(contextMock.getString(anyInt())).thenReturn("some String")
        `when`(viewMock.toString()).thenReturn("#ViewToSting")


        val result = SUT.doIt(5)
        val ac: ArgumentCaptor<Int> = ArgumentCaptor.forClass(Int::class.java)

        verify(activityMock).findViewById<View>(ac.capture())
        verify(contextMock).getString(ac.capture())
        val allValues = ac.allValues

        assertThat(result, startsWith("not old"))
        assertThat(result, `is`("not old => #ViewToSting some String"))

        assertThat(allValues[0], `is`(5))
        assertThat(allValues[1], `is`(5))
    }
}
