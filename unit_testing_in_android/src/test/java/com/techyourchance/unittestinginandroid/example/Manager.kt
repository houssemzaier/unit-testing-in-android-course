package com.techyourchance.unittestinginandroid.example

import android.app.Activity
import android.content.Context
import android.view.View

class Manager(private val activity: Activity, private val context: Context) {
    fun doIt(age: Int): String {
        if (activity.isDestroyed) {
            if (age > 80) return "OK OLD"
        } else {
            val viewById = activity.findViewById<View>(age)
            val string = context.getString(age)
            return "not old => $viewById $string"
        }
        return "the activity is still alive"
    }
}
