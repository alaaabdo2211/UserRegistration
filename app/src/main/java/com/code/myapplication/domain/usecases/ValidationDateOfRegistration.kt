package com.code.myapplication.domain.usecases

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class ValidationDateOfRegistration {
    fun getCurrentTime(): String = try {

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

        val date = Date(System.currentTimeMillis())

        sdf.format(date)

    }
    catch (e: Exception) {
        Log.e("Error","getCurrentTime: $e")
        ""
    }
}