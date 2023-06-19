package com.code.myapplication.data.remote

import android.util.Log
import com.code.myapplication.R
import com.code.myapplication.domain.entities.MostViewData
import com.code.myapplication.util.ApiState
import javax.inject.Inject

interface MostViewRepository {
    suspend fun getMostView(): ApiState<List<MostViewData>>
}

