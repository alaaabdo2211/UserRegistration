package com.code.myapplication.util

import androidx.annotation.StringRes

sealed class ApiState<T> {
    class Success<T>(val data: T) : ApiState<T>()
    class Error<T>(@StringRes val message: Int) : ApiState<T>()
}