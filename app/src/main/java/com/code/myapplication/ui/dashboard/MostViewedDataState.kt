package com.code.myapplication.ui.dashboard

import com.code.myapplication.data.remote.MostViewData


data class MostViewedDataState(
    val isLoading: Boolean = false,
    val errorMessage: Int? = null,
    val mostViewedDataList: List<MostViewData> = emptyList()
)