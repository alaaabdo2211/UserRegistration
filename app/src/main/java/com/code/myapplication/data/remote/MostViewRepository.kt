package com.code.myapplication.data.remote

import android.util.Log
import com.code.myapplication.R
import com.code.myapplication.util.ApiState
import javax.inject.Inject

interface MostViewRepository {
    suspend fun getMostView(): ApiState<List<MostViewData>>
}

class MostViewRepositoryImpl @Inject constructor(
    private val service: AppService
) : MostViewRepository {
    override suspend fun getMostView(): ApiState<List<MostViewData>> = try {
        val response = service.getMostView()
        val body = response.body()

        if (response.isSuccessful && body != null) {
            ApiState.Success(body.toMostViewData())
        } else {
            ApiState.Error(R.string.something_went_wrong_try_again)
        }
    } catch (e: Exception) {
        Log.e("Error Repo", e.message.toString())
        ApiState.Error(R.string.something_went_wrong_try_again)

    }
}