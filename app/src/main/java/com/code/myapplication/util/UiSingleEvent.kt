package com.code.myapplication.util

import androidx.annotation.StringRes

sealed class UiSingleEvent {
    object NavigateToNextPage : UiSingleEvent()
    data class ShowSnackBar(@StringRes val messageResource: Int) : UiSingleEvent()
}